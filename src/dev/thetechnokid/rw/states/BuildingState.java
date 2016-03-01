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
	private Animator anim;
	private int recty;

	public BuildingState(GraphicsContext g) {
		super(g);

	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		g.setFill(Color.RED);

		anim = new Animator(1000, () -> recty += Grid.SIZE);

		Button b = new Button("Change Colors");
		b.setOnAction((event) -> {
			if (color.equals(Color.RED))
				color = (Color.ROSYBROWN);
			else
				color = (Color.RED);
			g.getCanvas().requestFocus();
		});

		MainGameController.buttons().add(b);
	}

	@Override
	public void render() {
		g.setFill(color);
		g.fillRect(x, y, Grid.SIZE, Grid.SIZE);
		g.setFill(Color.BEIGE);
		g.fillRect(0, recty, Grid.SIZE, Grid.SIZE);

		g.setFill(Color.CADETBLUE);
		for (Point2D p : locs.keySet()) {
			if (locs.get(p)) {
				g.fillRect(p.getX() * Grid.SIZE, p.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
			}
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().releasedKey(KeyCode.UP)) {
			y -= Grid.SIZE;
		}
		else if (MainGameController.getKeyboard().releasedKey(KeyCode.DOWN)) {
			y += Grid.SIZE;
		}
		else if (MainGameController.getKeyboard().releasedKey(KeyCode.RIGHT)) {
			x += Grid.SIZE;
		}
		else if (MainGameController.getKeyboard().releasedKey(KeyCode.LEFT)) {
			x -= Grid.SIZE;
		}
		else if (MainGameController.getMouse().isMousePressed()) {
			locs.put(MainGameController.getMouse().getPointOnGrid(), true);
		}
		anim.tick();
	}

}
