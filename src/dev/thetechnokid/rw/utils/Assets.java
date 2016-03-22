package dev.thetechnokid.rw.utils;

import java.nio.IntBuffer;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;

public class Assets {
	public static Image ROCKET_PARTS;

	private Assets() {
	}

	public static void init() {
		ROCKET_PARTS = new Image(RocketWarfare.class.getResourceAsStream("/images/spritesheet.png"));
	}

	public static Image crop(Image src, int col, int row) {
		PixelReader r = src.getPixelReader();
		WritablePixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
		int[] pixels = new int[Grid.SIZE * Grid.SIZE];
		r.getPixels(col * Grid.SIZE, row * Grid.SIZE, Grid.SIZE, Grid.SIZE, pixelFormat, pixels, 0, Grid.SIZE);
		WritableImage out = new WritableImage(Grid.SIZE, Grid.SIZE);
		PixelWriter w = out.getPixelWriter();
		w.setPixels(0, 0, Grid.SIZE, Grid.SIZE, pixelFormat, pixels, 0, Grid.SIZE);
		return out;
	}

	public static Image flip(Image src) {
		PixelReader r = src.getPixelReader();
		WritablePixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
		int[] pixels = new int[Grid.SIZE * Grid.SIZE];
		r.getPixels(0, 0, Grid.SIZE, Grid.SIZE, pixelFormat, pixels, 0, Grid.SIZE);
		int[] newPixels = new int[Grid.SIZE * Grid.SIZE];

		for (int i = 0; i < pixels.length; i++) {
			newPixels[(i / Grid.SIZE) * Grid.SIZE
					+ (i % Grid.SIZE)] = pixels[(Grid.SIZE - (i / Grid.SIZE) - 1) * Grid.SIZE + (i % Grid.SIZE)];
		}
		
		WritableImage image = new WritableImage(Grid.SIZE, Grid.SIZE);
		image.getPixelWriter().setPixels(0, 0, Grid.SIZE, Grid.SIZE, pixelFormat, newPixels, 0, Grid.SIZE);
		
		return image;
	}

	public static void renderCropped(GraphicsContext g, Image image, int col, int row, int x, int y) {
		g.drawImage(image, col * Grid.SIZE, row * Grid.SIZE, Grid.SIZE, Grid.SIZE, x, y, Grid.SIZE, Grid.SIZE);
	}

	public static void renderCropped(GraphicsContext g, Image image, int col, int row, Point2D pos) {
		renderCropped(g, image, col, row, (int) pos.getX(), (int) pos.getY());
	}

	public static void renderFlip(GraphicsContext g, Image image, int col, int row, int x, int y, int angle) {
		g.save();
		rotate(g, angle, (col * Grid.SIZE) + Grid.SIZE / 2, (row * Grid.SIZE) + Grid.SIZE / 2);
		renderCropped(g, image, col, row, x, y);
		g.restore();
	}

	public static void renderFlip(GraphicsContext g, Image image, int col, int row, Point2D pos, int angle) {
		renderFlip(g, image, col, row, (int) pos.getX(), (int) pos.getY(), angle);
	}

	private static void rotate(GraphicsContext g, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

}
