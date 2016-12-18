package dev.thetechnokid.rw.entities;

import java.util.ArrayList;

public class EntityController {
	private static ArrayList<FlyingObject> objects;
	
	static {
		objects = new ArrayList<>();
	}
	
	public static void addObject(FlyingObject object) {
		objects.add(object);
	}
	
	public static void removeObject(int index) {
		objects.remove(index);
	}
	
	public static void removeObject(FlyingObject object) {
		objects.remove(object);
	}
	
	public static void tick() {
		for (FlyingObject flyingObject : objects) {
			flyingObject.tick();
		}
	}
}
