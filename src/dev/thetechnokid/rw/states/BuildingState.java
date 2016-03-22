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

	private Rocket rocket;

	private Label altitudeLabel;
	private Label xLabel;
	private Label degreesLabel;
	private Label modifierLabel;

	private double rockx, rocky;
	private int angle;

	public BuildingState(GraphicsContext g) {
		super(g);
		rocket = new Rocket(g);
	}

	@Override
	protected void init() {
		Assets.init();

		MainGameController.buttons().clear();
		g.setFill(Color.RED);

		anim = new Animator(1000, () -> {
			String altitudeText = String.format("%.2f", rocket.getAltitude());
			String xText = String.format("%.2f", rocket.getX());
			String modifierText = "Altitude Modifier: "
					+ String.format("%.4f", rocket.getAcceleration().getDirection().getAltitudeModifier())
					+ "; X Modifier: " + String.format("%.4f", rocket.getAcceleration().getDirection().getXModifier());
			String degreesText = rocket.getAcceleration().getDirection().getDegrees() + "\u00b0";
			altitudeLabel.setText("Atitude: " + altitudeText);
			xLabel.setText("X: " + xText);
			degreesLabel.setText("Degrees: " + degreesText);
			modifierLabel.setText(modifierText);
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
			rocket.getAcceleration().getDirection().decreaseDegrees();
		});

		Button tiltLeft = new Button("<-");
		tiltLeft.setOnAction((event) -> {
			rocket.getAcceleration().getDirection().increaseDegrees();
		});

		Button thrust = new Button("Thrust");
		thrust.setOnAction((event) -> {
			rocket.getAcceleration().increaseMagnitude(1);
		});

		Button dethrust = new Button("De-Thrust");
		dethrust.setOnAction((event) -> {
			rocket.getAcceleration().decreaseMagnitude(1);
		});

		Button rotate = new Button("Rotate ->");
		rotate.setOnAction((event) -> angle += 90);

		b.setFocusTraversable(false);
		tiltRight.setFocusTraversable(false);
		tiltLeft.setFocusTraversable(false);
		thrust.setFocusTraversable(false);
		dethrust.setFocusTraversable(false);

		altitudeLabel = new Label();
		xLabel = new Label();
		degreesLabel = new Label();
		modifierLabel = new Label();

		MainGameController.buttons().addAll(b, tiltRight, tiltLeft, thrust, dethrust, altitudeLabel, xLabel,
				degreesLabel, modifierLabel, rotate);
	}

	@Override
	public void render() {
		g.setFill(color);
		g.fillRect(x, y, Grid.SIZE, Grid.SIZE);
		g.fillOval(rockx, rocky, Grid.SIZE, Grid.SIZE);

		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 0), 3, 0);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 1), 3, 1);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 2), 3, 2);
		Grid.renderInGrid(g, Assets.flip(Assets.crop(Assets.ROCKET_PARTS, 1, 1)), 2, 2);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 1, 1), 4, 2);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 3), 3, 3);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 4), 3, 4);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 5), 3, 5);
		Grid.renderInGrid(g, Assets.crop(Assets.ROCKET_PARTS, 0, 6), 3, 6);

		g.setFill(Color.CADETBLUE);
		for (Point2D p : locs.keySet()) {
			if (locs.get(p)) {
				g.fillOval(p.getX() * Grid.SIZE, p.getY() * Grid.SIZE, Grid.SIZE, Grid.SIZE);
			}
		}
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().get(KeyCode.RIGHT))
			rocket.getAcceleration().getDirection().decreaseDegrees();
		else if (MainGameController.getKeyboard().get(KeyCode.LEFT))
			rocket.getAcceleration().getDirection().increaseDegrees();
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

		rockx = (rocket.getX() / 16);
		rocky = MainGameController.getHeight() - (rocket.getAltitude() / 16);
	}

}
