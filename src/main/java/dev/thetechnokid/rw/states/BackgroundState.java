package dev.thetechnokid.rw.states;

import javafx.scene.canvas.GraphicsContext;

public abstract class BackgroundState extends State {

	public BackgroundState(GraphicsContext g) {
		super(g);
	}

	@Override
	public final void render() {
	}
}
