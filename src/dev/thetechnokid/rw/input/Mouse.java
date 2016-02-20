package dev.thetechnokid.rw.input;

import javafx.event.EventHandler;
import javafx.scene.input.*;

public class Mouse implements EventHandler<MouseEvent> {

	private boolean mousePressed;
	private int x, y;

	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown()) {
			mousePressed = true;
			x = (int) event.getX();
			y = (int) event.getY();
		}
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
