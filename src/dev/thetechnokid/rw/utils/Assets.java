package dev.thetechnokid.rw.utils;

import java.nio.IntBuffer;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.scene.image.*;

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
			newPixels[i] = pixels[i - 2 * (i % Grid.SIZE) + Grid.SIZE - 1];
		}

		WritableImage image = new WritableImage(Grid.SIZE, Grid.SIZE);
		image.getPixelWriter().setPixels(0, 0, Grid.SIZE, Grid.SIZE, pixelFormat, newPixels, 0, Grid.SIZE);

		return image;
	}
}
