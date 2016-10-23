package dev.thetechnokid.rw.utils;

import dev.thetechnokid.rw.states.State;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grid {
	public static final int SIZE = 32;

	public static void render(GraphicsContext g) {
		final double F_SIZE = SIZE * State.getCurrentState().scale();
		int width = (int) g.getCanvas().getWidth();
		int height = (int) g.getCanvas().getHeight();
		for (double x = F_SIZE; x < width; x += F_SIZE) {
			g.strokeLine(x, 0, x, height);
		}
		for (double y = F_SIZE; y < height; y += F_SIZE) {
			g.strokeLine(0, y, width, y);
		}
	}

	public static Point2D getGridLocation(double x, double y) {
		final double F_SIZE = SIZE * State.getCurrentState().scale();

		Point2D point = null;

		double gx = x / F_SIZE;
		double gy = y / F_SIZE;

		point = new Point2D(gx * F_SIZE, gy * F_SIZE);

		return point;
	}

	public static Point2D getCanvasLocation(double x, double y) {
		final double F_SIZE = SIZE * State.getCurrentState().scale();
		Point2D point = new Point2D(x * F_SIZE, y * F_SIZE);
		return point;
	}

	public static void renderInGrid(GraphicsContext g, Image image, int col, int row) {
		final double F_SIZE = SIZE * State.getCurrentState().scale();
		g.drawImage(image, col * F_SIZE, row * F_SIZE);
	}
}
