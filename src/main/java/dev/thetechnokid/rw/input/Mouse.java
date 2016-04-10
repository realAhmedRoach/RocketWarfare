package dev.thetechnokid.rw.input;

import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.utils.Grid;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.*;

public class Mouse implements EventHandler<MouseEvent> {

	private boolean mousePressed;
	private boolean secondaryMousePressed;
	private int x, y;

	@Override
	public void handle(MouseEvent event) {
		x = (int) event.getX();
		y = (int) event.getY();
		if (x > MainGameController.getWidth() || x < 0) {
			event.consume();
			mousePressed = false;
			secondaryMousePressed = false;
		}
		if (y > MainGameController.getHeight() || y < 0) {
			event.consume();
			mousePressed = false;
			secondaryMousePressed = false;
		}
		if (event.isPrimaryButtonDown()) {
			mousePressed = true;
			secondaryMousePressed = false;
		} else if (event.isSecondaryButtonDown()) {
			mousePressed = false;
			secondaryMousePressed = true;
		} else {
			mousePressed = false;
			secondaryMousePressed = false;
		}
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public boolean isSecondaryMousePressed() {
		return secondaryMousePressed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point2D getPoint() {
		return new Point2D(getX(), getY());
	}

	public Point2D getPointOnGrid() {
		return new Point2D(getX() / Grid.SIZE, getY() / Grid.SIZE);
	}
}
