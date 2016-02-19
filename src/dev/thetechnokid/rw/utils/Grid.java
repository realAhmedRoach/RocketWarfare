package dev.thetechnokid.rw.utils;

import javafx.scene.canvas.GraphicsContext;

public class Grid {
	public static final int SIZE = 32;

	public static void render(GraphicsContext g) {
		int width = (int) g.getCanvas().getWidth();
		int height = (int) g.getCanvas().getHeight();
		for (int x = SIZE; x < width; x += SIZE) {
			g.strokeLine(x, 0, x, height);
		}
		for (int y = SIZE; y < height; y += SIZE) {
			g.strokeLine(0, y, width, y);
		}
	}
}
