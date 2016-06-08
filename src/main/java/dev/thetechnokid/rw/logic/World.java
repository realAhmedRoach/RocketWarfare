package dev.thetechnokid.rw.logic;

import java.util.*;

import dev.thetechnokid.rw.entities.FlyingObject;

public class World {
	private List<FlyingObject> objects = new ArrayList<>();

	public void addObject(FlyingObject object) {
		if (object != null)
			objects.add(object);
	}

	public void removeObject(FlyingObject object) {
		if (object != null)
			objects.remove(object);
	}

	public void render(int x, int y) {
		// TODO: Implement world render
	}
}
