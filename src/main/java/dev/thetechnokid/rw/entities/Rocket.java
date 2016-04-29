package dev.thetechnokid.rw.entities;

import java.io.Serializable;
import java.util.*;

import dev.thetechnokid.rw.maths.*;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.*;

public class Rocket extends Entity implements FlyingObject, Serializable {
	private static final long serialVersionUID = 7546621883949959769L;

	private static final double VEL_DELTA = (1.0 / 10.0);

	protected int mass;
	protected Dimension size;
	protected transient Position pos;
	private double acceleration;
	protected transient VectorQuantity velocity;
	protected List<RocketPart> parts;

	private boolean launched = false;
	private int time;

	private GraphicsContext g;

	public Rocket(GraphicsContext g) {
		this.g = g;
		size = new Dimension();
		pos = new Position();
		velocity = new VectorQuantity();
		parts = new ArrayList<RocketPart>();
	}

	@Override
	public void render(int x, int y) {
		g.save();
		g.setTransform((new Affine(new Rotate(-(velocity.getDirection().getDegrees() - 90),
				x + ((getWidth() * Grid.SIZE) / 2), y + (getHeight() * Grid.SIZE)))));
		for (RocketPart rocketPart : parts) {
			Point2D partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.getX() * Grid.SIZE));
			int yy = (int) (y + (partPos.getY() * Grid.SIZE));
			g.drawImage(rocketPart.getImage(), xx, yy);
		}
		g.restore();
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
		} else {
			if (velocity.getMagnitude() > 0) {
				velocity.setMagnitude((int) (Physics.G + 1));
				launched = true;
			}
		}
	}

	private void calculatePos() {
		double aa = (velocity.getMagnitude() * velocity.getDirection().getAltitudeModifier());
		double xa = (velocity.getMagnitude() * velocity.getDirection().getXModifier());

		boolean falling = (aa - Physics.G < 0);

		if (falling) {
			acceleration -= VEL_DELTA;
			time++;
		} else {
			if (acceleration <= Physics.initialVelocity(mass))
				acceleration += VEL_DELTA;
			if(time >= 1) time--;
		}

		pos.altitude += Physics.position(aa, acceleration, time);
		pos.x += xa;

	}

	public double getX() {
		return pos.x;
	}

	public void setX(double x) {
		pos.x = x;
	}

	public Position getPosition() {
		return pos;
	}

	public int getAcceleration() {
		return (int) acceleration;
	}

	public double getAltitude() {
		return pos.altitude;
	}

	public int getMass() {
		return mass;
	}

	public int getWidth() {
		return (int) size.getWidth();
	}

	public int getHeight() {
		return (int) size.getHeight();
	}

	public VectorQuantity getVelocity() {
		return velocity;
	}

	public double getForce() {
		return mass * velocity.getMagnitude();
	}

	public void addPart(RocketPart part) {
		if (part == null)
			return;
		parts.add(part);
		mass += part.getMass();
		size.setWidth(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getX()).max().getAsInt());
		size.setHeight(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getY()).max().getAsInt());
		acceleration = Physics.initialVelocity(mass);
	}

	public boolean isLaunched() {
		return launched;
	}
}
