package dev.thetechnokid.rw.states;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public abstract class State {
	private static State currentState;
	private static ArrayList<BackgroundState> bgStates = new ArrayList<>();

	protected GraphicsContext g;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State state) {
		if (state != null)
			currentState = state;
	}

	public static void addBackgroundState(BackgroundState state) {
		bgStates.add(state);
	}

	public static void tickAll() {
		currentState.tick();
		for (BackgroundState backgroundState : bgStates) {
			backgroundState.tick();
		}
	}

	public State(GraphicsContext g) {
		this(g, false);
	}

	public State(GraphicsContext g, boolean testing) {
		this.g = g;
		if (!testing)
			init();
	}

	public boolean gridEnabled() {
		return true;
	}

	public double scale() {
		return 1;
	}

	protected abstract void init();

	public abstract void render();

	public abstract void tick();
}
