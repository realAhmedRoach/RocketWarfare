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
	public void render(int x, int y, double scale) {
		g.save();
		g.setTransform((new Affine(new Rotate(-(acceleration.getDirection().getDegrees() - 90),
				x + ((getWidth() * (Grid.SIZE * scale)) / 2), y + (getHeight() * (Grid.SIZE * scale))))));
		for (RocketPart rocketPart : parts) {
			Position partPos = rocketPart.getPosInRocket();
			int xx = (int) (x + (partPos.x * Grid.SIZE));
			int yy = (int) (y + (partPos.y * Grid.SIZE));
			g.drawImage(rocketPart.getImage(), xx, yy, Grid.SIZE * scale, Grid.SIZE * scale);
		}
		g.restore();
	}

	@Override
	public void tick() {
		if (launched) {
			calculatePos();
		} else {
			if (acceleration.getMagnitude() > 0) {
				// acceleration.setMagnitude((int) (Physics.G + 1));
				time = 1;
				launched = true;
			}
		}
	}

	private void calculatePos() {
		if (acceleration.magnitudeActualY() < Force.GRAVITY.getForceY(time)) {
			Force.GRAVITY.setAccelerated(true);
		} else if (time > 1) {
			time--;
		} else {
			Force.GRAVITY.setAccelerated(false);
		}

		Physics.position(time, pos, velocity, acceleration, Force.GRAVITY);
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
}
