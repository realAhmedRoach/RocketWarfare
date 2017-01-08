package dev.thetechnokid.rw.states;

import com.jfoenix.controls.JFXButton;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.entities.Rocket;
import dev.thetechnokid.rw.utils.*;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class MissionControlState extends State {
	private Animator anim;

	private Rocket rocket;

	private Label altitudeLabel;
	private Label xLabel;
	private Label degreesLabel;

	private Gauge velocity;
	private Gauge acceleration;
	private Gauge time;

	private double rockx, rocky;
	private double ox, oy;
	private double scale = 1;

	public static final double DELTA = 0.025;

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
		MainGameController.getRight().clear();
		MainGameController.getLeft().clear();

		g.setFill(Color.RED);

		velocity = GaugeBuilder.create().skinType(Gauge.SkinType.MODERN).title(Language.get("velocity")).unit("FPS")
				.decimals(2).maxValue(500).build();
		acceleration = GaugeBuilder.create().skinType(Gauge.SkinType.INDICATOR).title(Language.get("acceleration"))
				.unit("FPS/S").decimals(4).maxValue(Rocket.MAX_ACCELERATION).build();
		time = GaugeBuilder.create().skinType(Gauge.SkinType.LCD).title(Language.get("time")).unit("Secs")
				.maxValue(Double.MAX_VALUE).averageVisible(false).maxSize(200, 200).decimals(0).build();
		time.setMinMeasuredValueVisible(false);
		time.setMaxMeasuredValueVisible(false);

		anim = new Animator(1000 / RocketWarfare.FPS, () -> {
			String altitudeText = Utils.format(rocket.getAltitude());
			String xText = Utils.format(rocket.getX());
			String degreesText = rocket.getVelocity().getDirection().getDegrees() + "\u00b0";
			int timeSecs = rocket.getTime() / RocketWarfare.FPS;
			altitudeLabel.setText(Language.get("altitude") + ": " + altitudeText);
			xLabel.setText("X: " + xText);
			degreesLabel.setText(Language.get("degrees") + ": " + degreesText);
			velocity.setValue(Math.abs(rocket.getVelocity().getMagnitude()));
			acceleration.setValue(rocket.getAcceleration().getMagnitude());
			time.setValue(timeSecs);
		});

		JFXButton scaleUp = new JFXButton("+");
		scaleUp.setOnAction((event) -> {
			if (scale < 2)
				scale += 0.5;
		});

		JFXButton scaleDown = new JFXButton("-");
		scaleDown.setOnAction((event) -> {
			if (scale > 0.5)
				scale -= 0.5;
		});

		JFXButton expand = new JFXButton(Language.get("expand"));
		expand.setOnAction((event) -> {
			MainGameController.getCanvas().setWidth(1056);
		});

		JFXButton build = new JFXButton(Language.get("rebuild"));
		build.setOnAction((event) -> State.setCurrentState(new BuildingState(g)));

		scaleUp.setFocusTraversable(false);
		scaleDown.setFocusTraversable(false);
		expand.setFocusTraversable(false);
		build.setFocusTraversable(false);

		altitudeLabel = new Label();
		xLabel = new Label();
		degreesLabel = new Label();

		MainGameController.getLeft().addAll(scaleUp, scaleDown, expand, new Separator(), altitudeLabel, xLabel,
				degreesLabel, new Separator(), build);
		MainGameController.getRight().addAll(velocity, acceleration, time);

		MainGameController.getCanvas().requestFocus();
	}

	@Override
	public void render() {
		rocket.render((int) rockx, (int) rocky);
	}

	@Override
	public void tick() {
		if (MainGameController.getKeyboard().get(KeyCode.RIGHT))
			rocket.getAcceleration().getDirection().decreaseDegrees();
		else if (MainGameController.getKeyboard().get(KeyCode.LEFT))
			rocket.getAcceleration().getDirection().increaseDegrees();

		if (MainGameController.getKeyboard().releasedKey(KeyCode.ESCAPE))
			MainGameController.getCanvas().setWidth(352);
		else if (MainGameController.getKeyboard().releasedKey(KeyCode.F11))
			MainGameController.getCanvas().setWidth(1056);

		if (MainGameController.getKeyboard().get(KeyCode.UP)
				&& rocket.getAcceleration().getMagnitude() < Rocket.MAX_ACCELERATION) {
			rocket.getAcceleration().increaseMagnitude(DELTA);
		} else if (MainGameController.getKeyboard().get(KeyCode.DOWN) && rocket.getAcceleration().getMagnitude() > 0) {
			rocket.getAcceleration().decreaseMagnitude(DELTA);
		} else if (rocket.getAcceleration().getMagnitude() > 0 && !rocket.isAccelerationLocked())
			rocket.getAcceleration().decreaseMagnitude(DELTA);

		if (MainGameController.getKeyboard().releasedKey(KeyCode.SPACE)) {
			rocket.toggleAccelerationLocked();
		}

		if (rocket.getAcceleration().getMagnitude() < 0)
			rocket.getAcceleration().setMagnitude(0);

		rocket.tick();
		anim.tick();

		rockx = (rocket.getX() / (Grid.SIZE)) - ox;
		rocky = MainGameController.getHeight() - (rocket.getAltitude() / (Grid.SIZE))
				- ((rocket.getHeight()) * Grid.SIZE) - oy;
		fixPos();
	}

	@Override
	public double scale() {
		return scale;
	}

	private void fixPos() {
		if (rockx > MainGameController.getWidth() - rocket.getWidth())
			ox += MainGameController.getWidth();
		else if (rockx < 0)
			ox -= MainGameController.getWidth();
		if (rocky > MainGameController.getHeight() - (rocket.getHeight()))
			oy += MainGameController.getHeight();
		else if (rocky < 0)
			oy -= MainGameController.getHeight();
	}
}
