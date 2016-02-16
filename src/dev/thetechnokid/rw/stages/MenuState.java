package dev.thetechnokid.rw.stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuState extends State {

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	public void render() {
		g.setFill(Color.RED);
		g.setFont(Font.font("Consolas", 20));
		g.fillText("Hey", (g.getCanvas().getWidth()/2)-20, 50);
	}

	@Override
	public void tick() {
	}

}
