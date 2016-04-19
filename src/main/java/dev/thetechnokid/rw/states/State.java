package dev.thetechnokid.rw.states;

import javafx.scene.canvas.GraphicsContext;

public abstract class State {
	private static State currentState;
	protected GraphicsContext g;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State state) {
		if (state != null)
			currentState = state;
	}

	public State(GraphicsContext g) {
		this(g, false);
	}

	public State(GraphicsContext g, boolean testing) {
		this.g = g;
		if (testing == false)
			init();
	}

	public boolean gridEnabled() {
		return true;
	}

	protected abstract void init();

	public abstract void render();

	public abstract void tick();
}
