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
	private Label velocityLabel;
	private Label accelerationLabel;
	private Label timeLabel;

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
		MainGameController.buttons().clear();
		MainGameController.integrations().clear();

		g.setFill(Color.RED);

		anim = new Animator(1000 / RocketWarfare.FPS, () -> {
			String altitudeText = Utils.format(rocket.getAltitude());
			String xText = Utils.format(rocket.getX());
			String degreesText = rocket.getVelocity().getDirection().getDegrees() + "\u00b0";
			String velocityText = Utils.format(rocket.getVelocity().getMagnitude());
			String accelerationText = Utils.format(rocket.getAcceleration().getMagnitude());
			int timeSecs = rocket.getTime() / RocketWarfare.FPS;
			int mins = timeSecs / 60;
			int secs = timeSecs % 60;
			String timeText = mins + " mins " + secs + " secs";
			altitudeLabel.setText(Language.get("altitude") + ": " + altitudeText);
			xLabel.setText("X: " + xText);
			degreesLabel.setText(Language.get("degrees") + ": " + degreesText);
			velocityLabel.setText(Language.get("velocity") + ": " + velocityText);
			accelerationLabel.setText(Language.get("acceleration") + ": " + accelerationText);
			timeLabel.setText(timeText);
		});

		Button tiltRight = new Button("->");
		tiltRight.setOnAction((event) -> {
			rocket.getAcceleration().getDirection().decreaseDegrees();
		});

		Button tiltLeft = new Button("<-");
		tiltLeft.setOnAction((event) -> {
			rocket.getAcceleration().getDirection().increaseDegrees();
		});

		Button scaleUp = new Button("+");
		scaleUp.setOnAction((event) -> {
			if (scale < 2)
				scale += 0.5;
		});

		Button scaleDown = new Button("-");
		scaleDown.setOnAction((event) -> {
			if (scale > 0.5)
				scale -= 0.5;
		});

		Button expand = new Button(Language.get("expand"));
		expand.setOnAction((event) -> {
			MainGameController.getCanvas().setWidth(1056);
		});

		Button build = new Button(Language.get("rebuild"));
		build.setOnAction((event) -> State.setCurrentState(new BuildingState(g)));

		tiltRight.setFocusTraversable(false);
		tiltLeft.setFocusTraversable(false);
		scaleUp.setFocusTraversable(false);
		scaleDown.setFocusTraversable(false);
		build.setFocusTraversable(false);

		altitudeLabel = new Label();
		xLabel = new Label();
		degreesLabel = new Label();
		velocityLabel = new Label();
		accelerationLabel = new Label();
		timeLabel = new Label();

		MainGameController.buttons().addAll(tiltRight, tiltLeft, scaleUp, scaleDown, expand, new Separator(),
				altitudeLabel, xLabel, degreesLabel, velocityLabel, accelerationLabel, timeLabel, new Separator(), build);
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
			rocket.getAcceleration().getDirection().decreaseDegrees();
		else if (MainGameController.getKeyboard().get(KeyCode.LEFT))
			rocket.getAcceleration().getDirection().increaseDegrees();

		if (MainGameController.getKeyboard().releasedKey(KeyCode.ESCAPE))
			MainGameController.getCanvas().setWidth(352);
		else if (MainGameController.getKeyboard().releasedKey(KeyCode.F11))
			MainGameController.getCanvas().setWidth(1056);

		if (MainGameController.getKeyboard().get(KeyCode.UP) && rocket.getAcceleration().getMagnitude() < 1) {
			rocket.getAcceleration().increaseMagnitude(DELTA);
		} else if (MainGameController.getKeyboard().get(KeyCode.DOWN) && rocket.getAcceleration().getMagnitude() > 0) {
			rocket.getAcceleration().decreaseMagnitude(DELTA);
		} else if (rocket.getAcceleration().getMagnitude() > 0)
			rocket.getAcceleration().decreaseMagnitude(DELTA);

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
