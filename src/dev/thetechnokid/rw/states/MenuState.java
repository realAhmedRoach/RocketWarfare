package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.geometry.Point2D;
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
		b.setOnAction((event) -> textOn = !textOn);
		MainGameController.get().buttons().add(b);

		g.setFont(new Font("Consolas", 20));
	}

	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.DARKGRAY);
		if (textOn)
			g.fillText("hey", 50, 30);
		Grid.render(g);
	}

	@Override
	public void tick() {
		if (MainGameController.get().getMouse().isMousePressed()) {
			int x = MainGameController.get().getMouse().getX();
			int y = MainGameController.get().getMouse().getY();
			System.out.println(x + "," + y);
			Point2D p = Grid.getGridLocation(x, y);
			g.fillRect(p.getX(), p.getY(), Grid.SIZE, Grid.SIZE);
		}
	}

}
