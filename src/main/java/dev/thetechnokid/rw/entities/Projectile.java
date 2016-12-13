package dev.thetechnokid.rw.entities;

import dev.thetechnokid.rw.maths.VectorQuantity;
import dev.thetechnokid.rw.physics.Physics;
import dev.thetechnokid.rw.states.State;
import dev.thetechnokid.rw.utils.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Projectile extends FlyingObject {

	private Image image;
	
	public Projectile(GraphicsContext g, Image image) {
		super(g);
	}

	@Override
	public void tick() {
		pos.y = -Math.pow(Physics.G, 2) * time + velocity.magnitudeActualY() * time + 0;
		pos.x += velocity.magnitudeActualX();
		
		VectorQuantity airResistance = Physics.airResistance(1.225, velocity.getMagnitude(), 0.25, getWidth(),
				velocity.getDirection());
		velocity.decreaseMagnitude(airResistance.getMagnitude() / velocity.getMagnitude());
	}

	@Override
	public void render(double x, double y) {
		double scale = State.getCurrentState().scale();
		g.save();
		g.setTransform((new Affine(new Rotate(-(acceleration.getDirection().getDegrees() - 90),
				x + ((getWidth() * (Grid.SIZE * scale)) / 2), y + (getHeight() * (Grid.SIZE * scale))))));
		g.drawImage(image, x * scale, y * scale, Grid.SIZE * scale, Grid.SIZE * scale);
		g.restore();
	}

}
