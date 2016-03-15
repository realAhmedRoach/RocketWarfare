package dev.thetechnokid.rw.utils;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.scene.image.Image;

public class Assets {
	private Assets() {
	}

	public static Image ROCKET_PARTS = new Image(RocketWarfare.class.getResourceAsStream("res/images/spritesheet.png"));;
	
	public static Image crop(Image image, int row, int col) {
		// TODO: Make body
		return null;
	}
}
