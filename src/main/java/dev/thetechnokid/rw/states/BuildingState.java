package dev.thetechnokid.rw.states;

import java.util.*;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.*;
import dev.thetechnokid.rw.utils.Grid;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class BuildingState extends State {
	private HashMap<Point2D, RocketPart> partLocs = new HashMap<>();

	private RocketPart currPart;

	private String[] currPartString;

	public BuildingState(GraphicsContext g) {
		super(g);

	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		MainGameController.integrations().clear();

		for (String part : RocketPart.allParts()) {
			String type = part.split("_")[0];
			String tier = part.split("_")[1];
			Button b = new Button();
			b.setId("parts");
			b.setGraphic(new ImageView(RocketPartData.image(type, tier, false)));
			b.setTooltip(new Tooltip(tier + " " + type));
			b.setOnAction((event) -> currPartString = new String[] { type, tier });
			if (Arrays.asList(RocketPart.FLIPPABLE_PARTS).contains(type)) {
				Button flipped = new Button();
				flipped.setId("parts");
				flipped.setGraphic(new ImageView(RocketPartData.image(type, tier, true)));
				flipped.setTooltip(new Tooltip(tier + " " + type));
				flipped.setOnAction((event) -> currPartString = new String[] { type, tier });
				MainGameController.integrations().add(flipped);
			}
			MainGameController.integrations().add(b);
		}

		Button finish = new Button("Complete!");
		finish.setOnAction(this::createRocket);
		finish.setFocusTraversable(false);
		MainGameController.buttons().add(finish);
	}

	private void createRocket(ActionEvent event) {
		int ox = 0;
		int oy = 0;

		Set<Point2D> locset = partLocs.keySet();
		oy = locset.stream().mapToInt(i -> (int) i.getY()).min().getAsInt();
		ox = locset.stream().mapToInt(i -> (int) i.getX()).min().getAsInt();

		Rocket r = new Rocket(g);
		for (Point2D orig : partLocs.keySet()) {
			RocketPart p = partLocs.get(orig);
			p.setPosInRocket(new Point2D(orig.getX() - ox, orig.getY() - oy));
			r.addPart(p);
		}

		State.setCurrentState(new MissionControlState(g, r));
	}

	@Override
	public void render() {
		if (currPartString != null)
			Grid.renderInGrid(g, RocketPartData.image(currPartString[0], currPartString[1], false), 0, 0);

		for (Point2D p : partLocs.keySet()) {
			Grid.renderInGrid(g, partLocs.get(p).getImage(), (int) p.getX(), (int) p.getY());
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed() && currPart != null) {
			partLocs.put(MainGameController.getMouse().getPointOnGrid(),
					RocketPartData.get(currPartString[0], currPartString[1], false));
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			partLocs.remove(MainGameController.getMouse().getPointOnGrid());
		}
	}

}
