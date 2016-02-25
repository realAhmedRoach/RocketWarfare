package dev.thetechnokid.rw.input;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.input.*;

public class Keyboard implements EventHandler<KeyEvent>{

	private HashMap<KeyCode, Boolean> keys= new HashMap<KeyCode, Boolean> ();
	
	@Override
	public void handle(KeyEvent event) {
		if (event.getEventType()==KeyEvent.KEY_PRESSED) 
			keys.put(event.getCode(), true);
		if (event.getEventType()==KeyEvent.KEY_RELEASED) 
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
