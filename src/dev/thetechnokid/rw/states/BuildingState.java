package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BuildingState extends State {
	private int x,y;

	public BuildingState(GraphicsContext g) {
		super(g);
		g.setFill(Color.RED);
	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		
		Button b = new Button("Change Colors");
		b.setOnAction((event) -> {
			if (g.getFill() == Color.RED)
				g.setFill(Color.ROSYBROWN);
			else
				g.setFill(Color.RED);
			g.getCanvas().requestFocus();
		});
		
		MainGameController.buttons().add(b);
	}

	@Override
	public void render() {
		g.fillRect(x, y, Grid.SIZE, Grid.SIZE);
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().get(KeyCode.UP)) {
			y -= Grid.SIZE;
		}
		if (MainGameController.getKeyboard().get(KeyCode.DOWN)) {
			y += Grid.SIZE;
		}
		if (MainGameController.getKeyboard().get(KeyCode.RIGHT)) {
			x += Grid.SIZE;
		}
		if (MainGameController.getKeyboard().get(KeyCode.LEFT)) {
			x -= Grid.SIZE;
		}
	}

}
