package dev.thetechnokid.rw.utils;

import java.awt.image.BufferedImage;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;

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
}
