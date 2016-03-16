package dev.thetechnokid.rw.utils;

import java.awt.image.BufferedImage;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Assets {
	public static Image ROCKET_PARTS;
	private static BufferedImage PARTS;

	private Assets() {
	}

	public static void init() {
		ROCKET_PARTS = new Image(RocketWarfare.class.getResourceAsStream("/images/spritesheet.png"));
		PARTS = SwingFXUtils.fromFXImage(ROCKET_PARTS, null);
	}

	public static Image crop(Image image, int col, int row) {
		BufferedImage img = PARTS.getSubimage(col * Grid.SIZE, row * Grid.SIZE, Grid.SIZE, Grid.SIZE);
		return SwingFXUtils.toFXImage(img, null);
	}

	public static void renderFlip(GraphicsContext g, Image image, int col, int row) {
		g.save();
		rotate(g, 270, (col * Grid.SIZE) + image.getWidth() / 2, (row * Grid.SIZE) + image.getHeight() / 2);
		g.drawImage(image, col * Grid.SIZE, row * Grid.SIZE);
		g.restore();
	}

	private static void rotate(GraphicsContext g, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		g.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}
}
