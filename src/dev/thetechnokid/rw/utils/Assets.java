package dev.thetechnokid.rw.utils;

import dev.thetechnokid.rw.RocketWarfare;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class Assets {
	public static Image ROCKET_PARTS;

	private Assets() {
	}

	public static void init() {
		ROCKET_PARTS = new Image(RocketWarfare.class.getResourceAsStream("/images/spritesheet.png"));
	}

	public static Image crop(Image src, int col, int row) {
		PixelReader r = src.getPixelReader();
		int sx = col * Grid.SIZE;
		int sy = row * Grid.SIZE;
		int ex = sx + Grid.SIZE;
		int ey = sy + Grid.SIZE;
		int rx = 0;
		int ry = 0;
		
		WritableImage out = new WritableImage(Grid.SIZE - 1, Grid.SIZE - 1);
		PixelWriter w = out.getPixelWriter();
		
		for(int y = sy; y <= ey; y++, ry++) {
			for(int x = sx; x <= ex; x++, rx++) {
				Color c = r.getColor(x, y);
				w.setColor(rx, ry, c);
			}	
		}
		return out;
	}
	
	public static void renderCropped(GraphicsContext g, Image image, int col, int row, int x, int y) {
		g.drawImage(image, col * Grid.SIZE, row * Grid.SIZE, Grid.SIZE, Grid.SIZE, x, y, Grid.SIZE, Grid.SIZE);
	}

	public static void renderCropped(GraphicsContext g, Image image, int col, int row, Point2D pos) {
		renderCropped(g, image, col, row, (int) pos.getX(), (int) pos.getY());
	}

	public static void renderFlip(GraphicsContext g, Image image, int col, int row, int x, int y, int angle) {
		g.save();
		g.rotate(angle);
		renderCropped(g, image, col, row, x, y);
		g.restore();
	}
	
	public static void renderFlip(GraphicsContext g, Image image, int col, int row, Point2D pos, int angle) {
		renderFlip(g, image, col, row, (int) pos.getX(), (int) pos.getY(), angle);
	}

}
