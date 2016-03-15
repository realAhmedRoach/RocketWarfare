package dev.thetechnokid.rw.utils;

import java.awt.image.BufferedImage;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;

public class Assets {
	private Assets() {
	}

	public static Image ROCKET_PARTS = new Image(RocketWarfare.class.getResourceAsStream("res/images/spritesheet.png"));
	public static BufferedImage PARTS = null; 
	
	static {
		PARTS = SwingFXUtils.fromFXImage(ROCKET_PARTS, PARTS);
	}
	public static Image crop(Image image, int row, int col) {
		BufferedImage img = PARTS.getSubimage(col * Grid.SIZE, row * Grid.SIZE, Grid.SIZE, Grid.SIZE);
		WritableImage outImg = null;
		return SwingFXUtils.toFXImage(img, outImg);
	}
}
