package dev.thetechnokid.rw.entities;

import java.io.Serializable;
import java.util.*;

import dev.thetechnokid.rw.maths.*;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.*;
import physics.*;

public class Rocket extends FlyingObject implements Serializable {
	private static final long serialVersionUID = 7546621883949959769L;

	protected List<RocketPart> parts;
	protected String name;

	private transient Force thrust = new Force(0, Direction.NORTH.clone(), false);

	private boolean launched = false;
	private int time;

	private transient GraphicsContext g;

	public Rocket(GraphicsContext g, String name) {
		this.g = g;
		this.name = name;
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
			Position partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.x * Grid.SIZE));
			int yy = (int) (y + (partPos.y * Grid.SIZE));
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
		if (thrust.getForceY() < Force.GRAVITY.getForceY(time)) {
			Force.GRAVITY.setAccelerated(true);
			pos.y += Physics.positionY(++time, Force.GRAVITY, thrust);
		} else {
			pos.y += time > 1 ? Physics.positionY(--time, Force.GRAVITY, thrust)
					: Physics.positionY(time, Force.GRAVITY, thrust);
			if (time == 1)
				Force.GRAVITY.setAccelerated(false);
		}

		pos.x += Physics.positionX(time, Force.GRAVITY, thrust);
		System.out.println(thrust.getForceY() + " " + Force.GRAVITY.getForceY(time));
	}

	public double getForce() {
		return mass * thrust.getAcceleration().getMagnitude();
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
}
