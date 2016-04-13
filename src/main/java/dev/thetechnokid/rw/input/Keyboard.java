package dev.thetechnokid.rw.input;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.*;

public class Keyboard implements EventHandler<KeyEvent> {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private KeyCode releasedKey = null;

	@Override
	public void handle(KeyEvent event) {
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
		releasedKey = null;
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
		return (releasedKey != null) ? releasedKey.equals(k) : false;
	}
}
