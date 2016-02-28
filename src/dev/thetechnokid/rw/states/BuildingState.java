package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BuildingState extends State {
	private int x,y;
	private Color color = Color.RED;

	public BuildingState(GraphicsContext g) {
		super(g);
		
	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		g.setFill(Color.RED);
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