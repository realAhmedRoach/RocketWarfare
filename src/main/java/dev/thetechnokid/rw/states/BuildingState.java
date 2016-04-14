package dev.thetechnokid.rw.states;

import java.util.Arrays;
import java.util.HashMap;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.RocketPart;
import dev.thetechnokid.rw.utils.Grid;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
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

		Button mc = new Button("Mission Control");
		mc.setOnAction((event) -> State.setCurrentState(new MissionControlState(g)));
		mc.setFocusTraversable(false);

		MainGameController.buttons().add(mc);

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
		if (MainGameController.getMouse().isMousePressed()) {
			partLocs.put(MainGameController.getMouse().getPointOnGrid(), currPart);
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			partLocs.remove(MainGameController.getMouse().getPointOnGrid());
		}
	}

}
