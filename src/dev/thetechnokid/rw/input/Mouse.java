package dev.thetechnokid.rw.input;

import dev.thetechnokid.rw.controllers.MainGameController;
import javafx.event.EventHandler;
import javafx.scene.input.*;

public class Mouse implements EventHandler<MouseEvent> {

	private boolean mousePressed;
	private int x, y;

	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown()) {
			x = (int) event.getX();
			y = (int) event.getY();
			mousePressed = true;
			if (x > MainGameController.getWidth() || x < 0) {
				event.consume();
				mousePressed = false;
			} if (y > MainGameController.getHeight() || y < 0) {
				event.consume();
				mousePressed = false;
			}
		} else {
			mousePressed = false;
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
