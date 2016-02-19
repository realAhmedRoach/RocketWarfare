package dev.thetechnokid.rw.stages;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuState extends State {

	private boolean textOn;
	
	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		Button b = new Button("hey");
		b.setOnAction((event) -> textOn =! textOn);
		MainGameController.get().buttons.getChildren().add(b);
		
		g.setFont(new Font("Consolas", 20));
	}
	
	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.DARKGRAY);
		if(textOn) g.fillText("hey", 50, 30);
		Grid.render(g);
	}

	@Override
	public void tick() {
	}

}
