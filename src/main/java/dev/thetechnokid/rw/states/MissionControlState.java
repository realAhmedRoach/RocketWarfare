package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.utils.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class MissionControlState extends State {
	private Color color = Color.RED;
	private Animator anim;

	private Rocket rocket;

	private Label altitudeLabel;
	private Label xLabel;
	private Label degreesLabel;
	private Label modifierLabel;
	private Label velocityLabel;

	private double rockx, rocky;
	private double ox, oy;

	public MissionControlState(GraphicsContext g, Rocket toControl) {
		super(g);
		if (toControl == null) throw new IllegalArgumentException("Rocket to control is null!");
		rocket = toControl;
	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		MainGameController.integrations().clear();

		g.setFill(Color.RED);

		anim = new Animator(1000, () -> {
			String altitudeText = String.format("%.2f", rocket.getAltitude());
			String xText = String.format("%.2f", rocket.getX());
			String modifierText = "Altitude Modifier: "
					+ String.format("%.4f", rocket.getAcceleration().getDirection().getAltitudeModifier())
					+ "; X Modifier: " + String.format("%.4f", rocket.getAcceleration().getDirection().getXModifier());
			String degreesText = rocket.getAcceleration().getDirection().getDegrees() + "\u00b0";
			String velocityText = rocket.getVelocity() + "";
			altitudeLabel.setText("Atitude: " + altitudeText);
			xLabel.setText("X: " + xText);
			degreesLabel.setText("Degrees: " + degreesText);
			modifierLabel.setText(modifierText);
			velocityLabel.setText("Velocity: " + velocityText);
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

		Button build = new Button("To Building");
		build.setOnAction((event) -> State.setCurrentState(new BuildingState(g)));

		b.setFocusTraversable(false);
		tiltRight.setFocusTraversable(false);
		tiltLeft.setFocusTraversable(false);
		thrust.setFocusTraversable(false);
		dethrust.setFocusTraversable(false);
		build.setFocusTraversable(false);

		altitudeLabel = new Label();
		xLabel = new Label();
		degreesLabel = new Label();
		modifierLabel = new Label();
		velocityLabel = new Label();

		MainGameController.buttons().addAll(b, tiltRight, tiltLeft, thrust, dethrust, altitudeLabel, xLabel,
				degreesLabel, velocityLabel, modifierLabel, new Separator(), build);
	}

	@Override
	public void render() {
		g.setFill(color);
		rocket.render((int) rockx, (int) rocky);
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().get(KeyCode.RIGHT))
			rocket.getAcceleration().getDirection().decreaseDegrees();
		else if (MainGameController.getKeyboard().get(KeyCode.LEFT))
			rocket.getAcceleration().getDirection().increaseDegrees();

		rocket.tick();
		anim.tick();

		rockx = (rocket.getX() / 8) - ox;
		rocky = MainGameController.getHeight() - (rocket.getAltitude() / 8) - oy;
		fixPos();
	}

	private void fixPos() {
		if (rockx > MainGameController.getWidth())
			ox += MainGameController.getWidth();
		else if (rockx < 0)
			ox -= MainGameController.getWidth();
		if (rocky > MainGameController.getHeight())
			oy += MainGameController.getHeight();
		else if (rocky < 0)
			oy -= MainGameController.getHeight();
	}
}
