package dev.thetechnokid.rw.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
	protected GraphicsContext g;
	protected Image image;

	public Entity(GraphicsContext g) {
		this.g = g;
	}

	public abstract void render();

	public abstract void tick();
}
