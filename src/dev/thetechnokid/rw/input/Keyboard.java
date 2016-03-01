package dev.thetechnokid.rw.input;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.*;

public class Keyboard implements EventHandler<KeyEvent> {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private KeyCode releasedKey = null;
	private long clearTime = System.currentTimeMillis();
	
	@Override
	public void handle(KeyEvent event) {
		System.out.println("Source:" + event.getSource().getClass().getName());
		System.out.println("Target:" + event.getTarget().getClass().getName());
		if (event.getTarget() instanceof TextInputControl)
			return;
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			keys.put(event.getCode(), true);
			releasedKey = null;
		}
		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			keys.put(event.getCode(), false);
			releasedKey = event.getCode();
		}
	}

	public void tick() {
		if (System.currentTimeMillis() - clearTime > 16) {
			releasedKey = null;
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
		return releasedKey == k;
	}
}
