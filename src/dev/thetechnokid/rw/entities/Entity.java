package dev.thetechnokid.rw.entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	public int x, y;
	public int w, h;
	protected GraphicsContext g;

	public Entity(GraphicsContext g) {
		this.g = g;
	}

	public abstract void render();

	public abstract void tick();
}
