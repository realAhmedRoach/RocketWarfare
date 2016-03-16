package dev.thetechnokid.rw.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.*;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

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
	
	public static Image flip(Image image) {
		BufferedImage buffImg = SwingFXUtils.fromFXImage(image, null);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		tx.translate(buffImg.getWidth(null), 0);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage newImage =  op.filter(buffImg, null);
		return SwingFXUtils.toFXImage(newImage, null);
	}
}
