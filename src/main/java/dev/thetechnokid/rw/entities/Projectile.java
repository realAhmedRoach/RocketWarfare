package dev.thetechnokid.rw.entities;

import dev.thetechnokid.rw.physics.Physics;

public class Projectile extends FlyingObject {

	@Override
	public void tick() {
		pos.y = -Math.pow(Physics.G, 2) * time + velocity.getMagnitude() * time + 0; 
	}

	@Override
	public void render(double x, double y) {

	}

}
