package dev.thetechnokid.rw.stages;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MenuState extends State {

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		MainGameController.get().buttons.getChildren().add(new Button("hey"));
	}
	
	@Override
	public void render() {
		g.setStroke(Color.RED);
		Grid.render(g);
	}

	@Override
	public void tick() {
	}

}
