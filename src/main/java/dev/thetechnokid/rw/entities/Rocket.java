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

	protected int mass;
	protected Dimension size;
	protected transient Position pos;
	protected List<RocketPart> parts;
	
	private Force thrust = new Force(0, Direction.NORTH.clone());

	private boolean launched = false;
	private int time;

	private GraphicsContext g;

	public Rocket(GraphicsContext g) {
		this.g = g;
		size = new Dimension();
		pos = new Position();
		parts = new ArrayList<RocketPart>();
	}

	@Override
	public void render(int x, int y) {
		g.save();
		g.setTransform((new Affine(new Rotate(-(thrust.getAcceleration().getDirection().getDegrees() - 90),
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
			time++;
			calculatePos();
		} else {
			if (thrust.getAcceleration().getMagnitude() > 0) {
				thrust.getAcceleration().setMagnitude((int) (Physics.G + 1));
				launched = true;
			}
		}
	}

	private void calculatePos() {

		pos.altitude += Physics.positionY(time, Force.GRAVITY, thrust);
		pos.x += Physics.positionX(time, Force.GRAVITY, thrust);

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

	public double getAcceleration() {
		return thrust.getAcceleration().getMagnitude();
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
		return thrust.getAcceleration();
	}

	public double getForce() {
		return mass * thrust.getAcceleration().getMagnitude();
	}

	public void addPart(RocketPart part) {
		if (part == null)
			return;
		parts.add(part);
		mass += part.getMass();
		size.setWidth(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getX()).max().getAsInt());
		size.setHeight(parts.stream().mapToInt(p -> (int) p.getPosInRocket().getY()).max().getAsInt());
	}

	public boolean isLaunched() {
		return launched;
	}
}
