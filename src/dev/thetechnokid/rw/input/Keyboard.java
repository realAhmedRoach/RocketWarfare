package dev.thetechnokid.rw.input;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.*;

public class Keyboard implements EventHandler<KeyEvent> {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<KeyCode> releasedKeys = new ArrayList<>();

	@Override
	public void handle(KeyEvent event) {
		System.out.println("Source:" + event.getSource().getClass().getName());
		System.out.println("Target:" + event.getTarget().getClass().getName());
		if (event.getTarget() instanceof TextInputControl)
			return;
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			keys.put(event.getCode(), true);
			releasedKeys.remove(event.getCode());
		}
		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			keys.put(event.getCode(), false);
			if (!releasedKeys.contains(event.getCode()))
				releasedKeys.add(event.getCode());
		}
	}

	public boolean get(KeyCode k) {
		boolean p = false;
		try {
			p = keys.get(k);
		} catch (Exception e) {
			return false;
		}
		return p;
	}
	
	public boolean releasedKey(KeyCode k) {
		return releasedKeys.contains(k);
	}
}
