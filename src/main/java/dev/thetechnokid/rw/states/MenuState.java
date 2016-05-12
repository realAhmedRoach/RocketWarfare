package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MenuState extends State {

	private boolean textOn, showWelcome;
	private String name;
	private boolean gridded;

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label nameLabel = new Label(Language.get("name") + ": ");
		TextField nameField = new TextField();
		nameField.setPromptText(Language.get("name"));
		nameField.setMaxWidth(100);

		Label pwLabel = new Label(Language.get("password") + ":");
		PasswordField pwField = new PasswordField();
		pwField.setPromptText(Language.get("password"));
		pwField.setMaxWidth(100);

		Button start = new Button("Start Building!");
		start.setOnAction((event) -> {
			name = nameField.getText();
			textOn = true;
			gridded = true;
			start.disableProperty().set(true);
			g.getCanvas().requestFocus();
		});
		
		grid.add(nameLabel, 0, 1);
		grid.add(nameField, 1, 1);
		grid.add(pwLabel, 0, 2);
		grid.add(pwField, 1, 2);
		grid.add(start, 1, 3);


		MainGameController.buttons().addAll(grid);
	}

	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.BLUEVIOLET);

		if (showWelcome) {
			Utils.wait(1500);
			State.setCurrentState(new BuildingState(g));
		}

		if (textOn) {
			Utils.centerText(g, Language.get("welcome") + ", " + name, 20);
			showWelcome = true;
		}
		// Grid.render(g);
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed()) {
			int x = MainGameController.getMouse().getX();
			int y = MainGameController.getMouse().getY();
			Point2D p = Grid.getGridLocation(x, y);
			g.fillRect(p.getX(), p.getY(), Grid.SIZE, Grid.SIZE);
		}

	}

	@Override
	public boolean gridEnabled() {
		return gridded;
	}
}
