package dev.thetechnokid.rw.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Utils {
	public static void centerText(GraphicsContext g, String text, int size) {
		int width = (int) g.getCanvas().getWidth();
		int height = (int) g.getCanvas().getHeight();

		int x = (width / 2);
		int y = (height / 2);

		g.setFont(new Font("Consolas", size));
		g.fillText(text, x, y);
	}

	public static void wait(int millisecs) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < millisecs) {
		}
	}
}
