package dev.thetechnokid.rw.entities;

import java.io.Serializable;
import java.util.*;

import dev.thetechnokid.rw.maths.*;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.*;

public class Rocket extends FlyingObject implements Serializable {
	private static final long serialVersionUID = 7546621883949959769L;

	protected List<RocketPart> parts;

	private Force thrust = new Force(0, Direction.NORTH.clone(), false);

	private boolean launched = false;
	private boolean falling;
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
			calculatePos();
		} else {
			if (thrust.getAcceleration().getMagnitude() > 0) {
				thrust.getAcceleration().setMagnitude((int) (Physics.G + 1));
				time = 1;
				launched = true;
			}
		}
	}

	private void calculatePos() {
		if (thrust.getForceY() - Force.GRAVITY.getForceY() < 0) {
			Force.GRAVITY.setAccelerated(true);
			if (!falling) {
				time = 1;
				falling = true;
			}
			pos.altitude += Physics.positionY(++time, Force.GRAVITY, thrust);
		} else {
			pos.altitude += falling ? Physics.positionY(--time, Force.GRAVITY, thrust)
					: Physics.positionY(++time, Force.GRAVITY, thrust);
			if (time <= Force.GRAVITY.getForceY()) {
				falling = false;
				Force.GRAVITY.setAccelerated(false);
			}
		}

		pos.x += Physics.positionX(time, Force.GRAVITY, thrust);
		System.out.println(time);
	}
	
	public double getAcceleration() {
		return thrust.getAcceleration().getMagnitude();
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
