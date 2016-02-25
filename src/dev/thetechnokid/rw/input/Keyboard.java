package dev.thetechnokid.rw.input;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	@Override
	public void handle(KeyEvent event) {
		System.out.println("Source:" + event.getSource().getClass().getName());
		System.out.println("Target:" + event.getTarget().getClass().getName());
		if (event.getTarget() instanceof TextInputControl)
			return;
		if (event.getEventType() == KeyEvent.KEY_PRESSED)
			keys.put(event.getCode(), true);
		if (event.getEventType() == KeyEvent.KEY_RELEASED)
			keys.put(event.getCode(), false);
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
}
