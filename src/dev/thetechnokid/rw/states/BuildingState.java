package dev.thetechnokid.rw.states;

import java.util.HashMap;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BuildingState extends State {
	private int x, y;
	private HashMap<Point2D, Boolean> locs = new HashMap<>();
	private Color color = Color.RED;

	public BuildingState(GraphicsContext g) {
		super(g);

	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		
		Button mc = new Button("Mission Control");
		mc.setOnAction((event) -> State.setCurrentState(new MissionControlState(g)));
		
		MainGameController.buttons().add(mc);
		
		g.setFill(Color.RED);
	}

	@Override
	public void render() {
		g.setFill(color);
		g.fillRect(x, y, Grid.SIZE, Grid.SIZE);

		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 0), 3, 0);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 1), 3, 1);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 2), 3, 2);
		Grid.renderInGrid(g, Assets.flip(Assets.crop(Assets.ROCKET_PARTS, 1, 1)), 2, 3);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 1, 1), 4, 3);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 3), 3, 3);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 4), 3, 4);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 5), 3, 5);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 1, 5), 4, 5);
		Grid.renderInGrid(g, Assets.flip(Assets.crop(Assets.ROCKET_PARTS, 1, 5)), 2, 5);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 6), 3, 6);

		g.setFill(Color.CADETBLUE);
		for (Point2D p : locs.keySet()) {
			if (locs.get(p)) {
				g.fillOval(p.getX() * Grid.SIZE, p.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
			}
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
			locs.put(MainGameController.getMouse().getPointOnGrid(), true);
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			locs.put(MainGameController.getMouse().getPointOnGrid(), false);
		}
	}

}
