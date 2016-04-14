package dev.thetechnokid.rw.states;

import java.util.*;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.RocketPart;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BuildingState extends State {
	private int x, y;
	private HashMap<Point2D, RocketPart> partLocs = new HashMap<>();
	private Color color = Color.RED;

	private RocketPart currPart;

	public BuildingState(GraphicsContext g) {
		super(g);

	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();

		Button mc = new Button("Mission Control");
		mc.setOnAction((event) -> State.setCurrentState(new MissionControlState(g)));
		mc.setFocusTraversable(false);

		MainGameController.buttons().add(mc);

		g.setFill(Color.RED);

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
		g.setFill(color);
		g.fillRect(x, y, Grid.SIZE, Grid.SIZE);

		if (currPart != null)
			Grid.renderInGrid(g, currPart.getImage(), 0, 0);

		Grid.renderInGrid(g, RocketPart.get("nose", "normal").getImage(), 3, 0);
		Grid.renderInGrid(g, RocketPart.get("body", "normal").getImage(), 3, 1);
		Grid.renderInGrid(g, RocketPart.get("window", "normal").getImage(), 3, 2);
		Grid.renderInGrid(g, RocketPart.get("door", "normal").getImage(), 3, 3);
		Grid.renderInGrid(g, RocketPart.get("missileholder", "normal").getImage(), 3, 4);
		Grid.renderInGrid(g, RocketPart.get("thruster", "normal").getImage(), 3, 5);
		Grid.renderInGrid(g, Assets.flip(RocketPart.get("fin", "camo").getImage()), 2, 2);
		Grid.renderInGrid(g, RocketPart.get("fin", "camo").getImage(), 4, 2);
		Grid.renderInGrid(g, Assets.flip(RocketPart.get("missile", "normal").getImage()), 2, 4);
		Grid.renderInGrid(g, RocketPart.get("missile", "normal").getImage(), 4, 4);

		g.setFill(Color.CADETBLUE);
		for (Point2D p : partLocs.keySet()) {
			Grid.renderInGrid(g, partLocs.get(p).getImage(), (int) p.getX(), (int) p.getY());
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().releasedKey(KeyCode.UP)) {
			y -= Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.DOWN)) {
			y += Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.RIGHT)) {
			x += Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.LEFT)) {
			x -= Grid.SIZE;
		}
		if (MainGameController.getMouse().isMousePressed()) {
			partLocs.put(MainGameController.getMouse().getPointOnGrid(), currPart);
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			partLocs.remove(MainGameController.getMouse().getPointOnGrid());
		}
	}

}
