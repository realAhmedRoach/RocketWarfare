package dev.thetechnokid.rw.states;

import java.util.*;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.*;
import dev.thetechnokid.rw.logic.Parts;
import dev.thetechnokid.rw.maths.Position;
import dev.thetechnokid.rw.utils.Grid;
import dev.thetechnokid.rw.utils.Language;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class BuildingState extends State {
	private HashMap<Point2D, RocketPart> partLocs = new HashMap<>();

	private String[] currPartString;
	private Rocket rocket;
	private TextField name;
	private ImageView currPart;
	
	private HashMap<String, HBox> boxes;

	public BuildingState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		MainGameController.getRight().clear();
		MainGameController.getLeft().clear();
		
		boxes = new HashMap<>();
		
		for (String part : RocketPart.allParts()) {
			String type = part.split("_")[0];
			String _class = part.split("_")[1];
			
			if(!boxes.containsKey(type))
				boxes.put(type, new HBox(10));
			
			Button b = new Button();
			b.setId("parts");
			b.setGraphic(new ImageView(RocketPartData.image(type, _class, false)));
			b.setTooltip(new Tooltip(_class + " " + type));
			b.setOnAction((event) -> {
				currPartString = new String[] { type, _class, "false" };
				currPart.setImage(RocketPartData.image(currPartString[0], currPartString[1],
						currPartString[2].equals("true") ? true : false));
			});
			boxes.get(type).getChildren().add(b);
			if (Arrays.asList(RocketPart.FLIPPABLE_PARTS).contains(type)) {
				Button flipped = new Button();
				flipped.setId("parts");
				flipped.setGraphic(new ImageView(RocketPartData.image(type, _class, true)));
				flipped.setTooltip(new Tooltip(_class + " " + type));
				flipped.setOnAction((event) -> currPartString = new String[] { type, _class, "true" });
				boxes.get(type).getChildren().add(flipped);
			}
		}

		MainGameController.getRight().addAll(boxes.values());
		
		currPart = new ImageView();

		Button finish = new Button(Language.get("complete") + "!");
		finish.setOnAction(event -> {
			if (createRocket())
				State.setCurrentState(new MissionControlState(g, rocket));
		});
		finish.setFocusTraversable(false);

		name = new TextField();
		name.setPromptText(Language.get("rocket name"));
		name.setFocusTraversable(false);

		Button save = new Button(Language.get("save rocket"));
		save.setOnAction(event -> saveRocket());
		save.setFocusTraversable(false);

		Button load = new Button(Language.get("load rocket"));
		load.setOnAction(event -> loadRocket());
		load.setFocusTraversable(false);

		MainGameController.getLeft().addAll(currPart, new Separator(), finish, new Separator(), name, save, load);
	}

	private boolean createRocket() {
		if (partLocs.isEmpty())
			return false;

		int ox = 0;
		int oy = 0;

		Set<Point2D> locset = partLocs.keySet();
		oy = locset.stream().mapToInt(i -> (int) i.getY()).min().getAsInt();
		ox = locset.stream().mapToInt(i -> (int) i.getX()).min().getAsInt();

		rocket = new Rocket(g, name.getText());
		for (Point2D orig : partLocs.keySet()) {
			RocketPart p = partLocs.get(orig);
			p.setPosInRocket(new Position(orig.getX() - ox, orig.getY() - oy));
			p.setNorth(partLocs.get(new Point2D(orig.getX(), orig.getY() + 1)));
			p.setSouth(partLocs.get(new Point2D(orig.getX(), orig.getY() - 1)));
			p.setEast(partLocs.get(new Point2D(orig.getX() + 1, orig.getY())));
			p.setWest(partLocs.get(new Point2D(orig.getX() - 1, orig.getY())));
			rocket.addPart(p);
		}

		return validate(rocket);
	}

	public static boolean validate(Rocket rocket) {
		for (RocketPart part : rocket.getParts()) {
			if (part.getNorth() == null && part.getSouth() == null && part.getEast() == null && part.getWest() == null)
				return false;
		}

		boolean noseFound = false;
		boolean thrusterFound = false;
		for (RocketPart part : rocket.getParts()) {
			if (part.getType().equalsIgnoreCase(Parts.NOSE.name()))
				if (!noseFound)
					noseFound = true;
				else
					return false;
			if (part.getType().equalsIgnoreCase(Parts.THRUSTER.name()))
				if (!thrusterFound)
					thrusterFound = true;
				else
					return false;
		}

		return (noseFound && thrusterFound);
	}

	private void saveRocket() {
		if (rocket == null)
			return;
		createRocket();
		MainGameController.get().USER.addBlueprint(rocket);
	}

	private void loadRocket() {
		for (Rocket stuff : MainGameController.get().USER.getBlueprints()) {
			if (stuff.getName().equals(name.getText()))
				rocket = stuff;
		}
	}

	@Override
	public void render() {
		for (Point2D p : partLocs.keySet()) {
			Grid.renderInGrid(g, partLocs.get(p).getImage(), (int) p.getX(), (int) p.getY());
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed() && currPartString != null) {
			partLocs.put(MainGameController.getMouse().getPointOnGrid(), RocketPartData.get(currPartString[0],
					currPartString[1], currPartString[2].equals("true") ? true : false));
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			partLocs.remove(MainGameController.getMouse().getPointOnGrid());
		}
	}

}
