package dev.thetechnokid.rw.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.transform.*;

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

	/**
	 * Sets the transform for the GraphicsContext to rotate around a pivot
	 * point.
	 *
	 * @param g
	 *            the graphics context the transform to applied to.
	 * @param angle
	 *            the angle of rotation.
	 * @param px
	 *            the x pivot coordinate for the rotation (in canvas
	 *            coordinates).
	 * @param py
	 *            the y pivot coordinate for the rotation (in canvas
	 *            coordinates).
	 */
	private static void rotate(GraphicsContext g, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		g.setTransform(new Affine(r));
	}

	/**
	 * Draws an image on a graphics context.
	 *
	 * The image is drawn at (tlpx, tlpy) rotated by angle pivoted around the
	 * point: (tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2)
	 *
	 * @param g
	 *            the graphics context the image is to be drawn on.
	 * @param angle
	 *            the angle of rotation.
	 * @param tlpx
	 *            the top left x coordinate where the image will be plotted (in
	 *            canvas coordinates).
	 * @param tlpy
	 *            the top left y coordinate where the image will be plotted (in
	 *            canvas coordinates).
	 */
	public static void drawRotatedImage(GraphicsContext g, Image image, double angle, double tlpx, double tlpy) {
		g.save(); // saves the current state on stack, including the current
					// transform
		rotate(g, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
		g.drawImage(image, tlpx, tlpy);
		g.restore(); // back to original state (before rotation)
	}

	/**
	 * Formats a double.
	 * 
	 * @param number
	 *            The number to be formatted
	 * @return A formatted double with 2 decimal points.
	 */
	public static String format(double number) {
		return String.format("%,.2f", number);
	}
}
