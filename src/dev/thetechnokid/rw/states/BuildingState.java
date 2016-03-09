package dev.thetechnokid.rw.states;

import java.util.HashMap;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BuildingState extends State {
	private int x, y;
	private HashMap<Point2D, Boolean> locs = new HashMap<>();
	private Color color = Color.RED;
	private Animator anim;
	private int recty;

	private Rocket rocket;

	private Label altitudeLabel;
	private Label degreesLabel;

	public BuildingState(GraphicsContext g) {
		super(g);
		rocket = new Rocket(g);
	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		g.setFill(Color.RED);

		anim = new Animator(1000, () -> {
			recty += Grid.SIZE;
			altitudeLabel.setText("Atitude: " + String.format("%.2f", rocket.getAltitude()));
			degreesLabel.setText(
					"Modifier: " + String.format("%.4f", rocket.getAcceleration().getDirection().getAltitudeModifier())
							+ " " + "Degrees: " + rocket.getAcceleration().getDirection().degrees);
		});

		Button b = new Button("Change Colors");
		b.setOnAction((event) -> {
			if (color.equals(Color.RED))
				color = (Color.ROSYBROWN);
			else
				color = (Color.RED);
			g.getCanvas().requestFocus();
		});

		Button tiltRight = new Button("->");
		tiltRight.setOnAction((event) -> {
			rocket.getAcceleration().getDirection().degrees--;
		});

		Button tiltLeft = new Button("<-");
		tiltLeft.setOnAction((event) -> {
			rocket.getAcceleration().getDirection().degrees++;
		});

		Button thrust = new Button("Thrust");
		thrust.setOnAction((event) -> {
			rocket.getAcceleration().increaseMagnitude(1);
		});
		
		Button dethrust = new Button("De-Thrust");
		dethrust.setOnAction((event) -> {
			rocket.getAcceleration().decreaseMagnitude(1);
		});
		
		b.setFocusTraversable(false);
		tiltRight.setFocusTraversable(false);
		tiltLeft.setFocusTraversable(false);
		thrust.setFocusTraversable(false);
		dethrust.setFocusTraversable(false);
		
		altitudeLabel = new Label();
		degreesLabel = new Label();

		MainGameController.buttons().addAll(b, tiltRight, tiltLeft, thrust, dethrust, altitudeLabel, degreesLabel);
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
				g.fillOval(p.getX() * Grid.SIZE, p.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
			}
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().releasedKey(KeyCode.UP)) {
			y -= Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.DOWN)) {
			y += Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.RIGHT)) {
			x += Grid.SIZE;
		} else if (MainGameController.getKeyboard().releasedKey(KeyCode.LEFT)) {
			x -= Grid.SIZE;
		}
		if (MainGameController.getMouse().isMousePressed()) {
			locs.put(MainGameController.getMouse().getPointOnGrid(), true);
		} else if (MainGameController.getMouse().isSecondaryMousePressed()) {
			locs.put(MainGameController.getMouse().getPointOnGrid(), false);
		}
		rocket.tick();
		anim.tick();
	}

}
