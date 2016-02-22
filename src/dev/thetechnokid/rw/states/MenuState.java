package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class MenuState extends State {

	private boolean textOn;
	private StringProperty nameProp;

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		Label name = new Label("Name:");
		TextField nameField = new TextField();
		nameField.setPromptText("JSmith");
		nameField.setMaxWidth(100);
		nameProp = nameField.textProperty();
		Button start = new Button("Start Building!");
		start.setOnAction((event) -> textOn = true);
		
		MainGameController.buttons().addAll(name, nameField, start);
	}

	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.BLUEVIOLET);
		if (textOn)
			g.fillText("Welcome, " + nameProp.get(), 50, 30);
		Grid.render(g);
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed()) {
			int x = MainGameController.getMouse().getX();
			int y = MainGameController.getMouse().getY();
			System.out.println(x + "," + y);
			Point2D p = Grid.getGridLocation(x, y);
			g.fillRect(p.getX(), p.getY(), Grid.SIZE, Grid.SIZE);
		}
	}

}
