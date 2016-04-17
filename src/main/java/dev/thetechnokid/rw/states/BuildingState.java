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

	public BuildingState(GraphicsContext g) {
		super(g);

	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		MainGameController.integrations().clear();

		for (RocketPart part : RocketPart.allParts()) {
			Button b = new Button();
			b.setGraphic(new ImageView(part.getImage()));
			b.setTooltip(new Tooltip(part.getTier() + " " + part.getType()));
			b.setOnAction((event) -> currPart = part);
			if (Arrays.asList(RocketPart.FLIPPABLE_PARTS).contains(part.getType())) {
				Button flipped = new Button();
				RocketPart f = RocketPart.allParts().getFlipped(RocketPart.allParts().indexOf(part));
				flipped.setGraphic(new ImageView(f.getImage()));
				flipped.setTooltip(new Tooltip(f.getTier() + " " + f.getType()));
				flipped.setOnAction((event) -> currPart = f);
				MainGameController.integrations().add(flipped);
			}
			MainGameController.integrations().add(b);
		}

		MainGameController.buttons().add(new Separator());

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

		MainGameController.setStatus("ox = " + ox + " oy = " + oy);

		// List<RocketPart> parts = new ArrayList<RocketPart>();
		Rocket r = new Rocket(g);
		for (Point2D orig : partLocs.keySet()) {
			RocketPart p = partLocs.get(orig);
			r.addPart(p);
			p.setPosInRocket(new Point2D(orig.getX() - ox, orig.getY() - oy));
			System.out.println("Pos:" + p.getPosInRocket());
		}

		State.setCurrentState(new MissionControlState(g, r));
	}

	@Override
	public void render() {
		if (currPart != null)
			Grid.renderInGrid(g, currPart.getImage(), 0, 0);

		for (Point2D p : partLocs.keySet()) {
			Grid.renderInGrid(g, partLocs.get(p).getImage(), (int) p.getX(), (int) p.getY());
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed() && currPart != null) {
			partLocs.put(MainGameController.getMouse().getPointOnGrid(), currPart);
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			partLocs.remove(MainGameController.getMouse().getPointOnGrid());
		}
	}

}
