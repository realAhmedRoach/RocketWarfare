package dev.thetechnokid.rw.states;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.utils.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class MissionControlState extends State {
	private Animator anim;

	private Rocket rocket;

	private Label altitudeLabel;
	private Label xLabel;
	private Label degreesLabel;
	private Label modifierLabel;
	private Label velocityLabel;
	private Label apfLabel;

	private double rockx, rocky;
	private double ox, oy;

	public MissionControlState(GraphicsContext g, Rocket toControl) {
		super(g);
		if (toControl == null)
			throw new IllegalArgumentException("Rocket to control is null!");
		rocket = toControl;
	}

	public MissionControlState(GraphicsContext g, Rocket toControl, boolean testing) {
		super(g, testing);
		if (toControl == null)
			throw new IllegalArgumentException("Rocket to control is null!");
		rocket = toControl;
	}

	@Override
	protected void init() {
		MainGameController.buttons().clear();
		MainGameController.integrations().clear();

		g.setFill(Color.RED);

		anim = new Animator(1000 / RocketWarfare.FPS, () -> {
			String altitudeText = String.format("%.2f", rocket.getAltitude());
			String xText = String.format("%.2f", rocket.getX());
			String modifierText = Language.get("altitude modifier") + ": "
					+ String.format("%.4f", rocket.getVelocity().getDirection().getAltitudeModifier())
					+ "; X "+ Language.get("modifier") + ": " + String.format("%.4f", rocket.getVelocity().getDirection().getXModifier());
			String degreesText = rocket.getVelocity().getDirection().getDegrees() + "\u00b0";
			String velocityText = rocket.getAcceleration() + "";
			String apfText = String.format("%,.4f", rocket.getVelocity().apf());
			altitudeLabel.setText(Language.get("altitud") + ": " + altitudeText);
			xLabel.setText("X: " + xText);
			degreesLabel.setText(Language.get("degrees") + ": " + degreesText);
			modifierLabel.setText(modifierText);
			velocityLabel.setText(Language.get("velocity") + ": " + velocityText);
			apfLabel.setText("APF: " + apfText);
		});

		Button tiltRight = new Button("->");
		tiltRight.setOnAction((event) -> {
			rocket.getVelocity().getDirection().decreaseDegrees();
		});

		Button tiltLeft = new Button("<-");
		tiltLeft.setOnAction((event) -> {
			rocket.getVelocity().getDirection().increaseDegrees();
		});

		Button thrust = new Button("Thrust");
		thrust.setOnAction((event) -> {
			rocket.getVelocity().increaseMagnitude(1);
		});

		Button dethrust = new Button("De-Thrust");
		dethrust.setOnAction((event) -> {
			rocket.getVelocity().decreaseMagnitude(1);
		});

		Button build = new Button("To Building");
		build.setOnAction((event) -> State.setCurrentState(new BuildingState(g)));

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
		apfLabel = new Label();

		MainGameController.buttons().addAll(tiltRight, tiltLeft, thrust, dethrust, altitudeLabel, xLabel, degreesLabel,
				velocityLabel, modifierLabel, apfLabel, new Separator(), build);
	}

	@Override
	public void render() {
		g.setFill(Color.ORANGERED);
		g.fillText(String.format("%,d", (int) -oy) + "", 60, 20);
		g.fillText(String.format("%,d", (int) ox) + "", MainGameController.getWidth() - 40,
				MainGameController.getHeight() - 20);
		rocket.render((int) rockx, (int) rocky);
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().get(KeyCode.RIGHT))
			rocket.getVelocity().getDirection().decreaseDegrees();
		else if (MainGameController.getKeyboard().get(KeyCode.LEFT))
			rocket.getVelocity().getDirection().increaseDegrees();

		rocket.tick();
		anim.tick();

		rockx = (rocket.getX() / (Grid.SIZE)) - ox;
		rocky = MainGameController.getHeight() - (rocket.getAltitude() / (Grid.SIZE))
				- ((rocket.getHeight() + 1) * Grid.SIZE) - oy;
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
