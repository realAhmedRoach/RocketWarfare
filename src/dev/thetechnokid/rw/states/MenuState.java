package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MenuState extends State {

	private boolean textOn;
	private String name;
	private boolean grid;

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);

		Label nameLabel = new Label("Name:");
		TextField nameField = new TextField();
		nameField.setFocusTraversable(false);
		nameField.setPromptText("JSmith");
		nameField.setMaxWidth(100);
		Button start = new Button("Start Building!");
		start.setOnAction((event) -> {
			name = nameField.getText();
			textOn = true;
			grid = true;
			start.disableProperty().set(true);
			g.getCanvas().requestFocus();
		});

		MainGameController.buttons().addAll(nameLabel, nameField, start);
	}

	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.BLUEVIOLET);
		if (textOn) {
			Utils.centerText(g, "Welcome, " + name, 20);
			State.setCurrentState(new BuildingState(g));
			Utils.wait(1000);
		}
		// Grid.render(g);
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

	@Override
	public boolean gridEnabled() {
		return grid;
	}
}
