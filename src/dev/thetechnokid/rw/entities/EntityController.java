package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class EntityController {
	private ArrayList<RocketPart> shownRocketParts;
	private GraphicsContext g;

	public EntityController(GraphicsContext g) {
		this.g = g;
		shownRocketParts = new ArrayList<>();
	}

	public void addRocketPart(RocketPart part) {
		if (part != null)
			shownRocketParts.add(part);
	}

	public void render() {
		for (RocketPart rocketPart : shownRocketParts) {
			g.drawImage(rocketPart.image, rocketPart.getPosInRocket().getX(),
					rocketPart.getPosInRocket().getY());
		}
	}

}
