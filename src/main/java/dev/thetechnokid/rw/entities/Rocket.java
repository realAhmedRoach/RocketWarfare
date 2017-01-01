package dev.thetechnokid.rw.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.thetechnokid.rw.maths.Dimension;
import dev.thetechnokid.rw.maths.Position;
import dev.thetechnokid.rw.physics.Physics;
import dev.thetechnokid.rw.states.BuildingState;
import dev.thetechnokid.rw.states.State;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Rocket extends FlyingObject implements Serializable {
	private static final long serialVersionUID = 7546621883949959769L;

	protected List<RocketPart> parts;
	protected String name;

	private boolean launched = false;
	private boolean accelerationLocked;

	public static double MAX_ACCELERATION = 1;

	public Rocket(GraphicsContext g, String name) {
		super(g);
		this.name = name;
		size = new Dimension();
		pos = new Position();
		parts = new ArrayList<>();
	}

	@Override
	public void render(double x, double y) {
		double scale = State.getCurrentState().scale();
		g.save();
		g.setTransform((new Affine(new Rotate(-(this.getDirection().getDegrees()),
				x + ((getWidth() * (Grid.SIZE * scale)) / 2), y + (getHeight() * (Grid.SIZE * scale))))));
		for (RocketPart rocketPart : parts) {
			Position partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.x * (Grid.SIZE * scale)));
			int yy = (int) (y + (partPos.y * (Grid.SIZE * scale)));
			g.drawImage(rocketPart.getImage(), xx, yy, Grid.SIZE * scale, Grid.SIZE * scale);
		}
		g.restore();
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
			time++;
		} else {
			if (acceleration.getMagnitude() > 0)
				launched = true;
		}
	}

	private void calculatePos() {
		Physics.position(time, this, pos);
	}

	public double getForce() {
		return mass * acceleration.getMagnitude();
	}

	public String getName() {
		return name;
	}

	public void addPart(RocketPart part) {
		if (part == null)
			return;
		parts.add(part);
		mass += part.getMass();
		size.setWidth(parts.stream().mapToInt(p -> (int) p.getPosInRocket().x).max().getAsInt() + 1);
		size.setHeight(parts.stream().mapToInt(p -> (int) p.getPosInRocket().y).max().getAsInt() + 1);
	}

	public boolean isLaunched() {
		return launched;
	}

	public void crash() {
		State.setCurrentState(new BuildingState(g));
	}

	public boolean isAccelerationLocked() {
		return accelerationLocked;
	}

	public void toggleAccelerationLocked() {
		accelerationLocked = !accelerationLocked;
	}

	public List<RocketPart> getParts() {
		return parts;
	}
}
