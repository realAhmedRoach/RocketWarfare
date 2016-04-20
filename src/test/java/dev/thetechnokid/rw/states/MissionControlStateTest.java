package dev.thetechnokid.rw.states;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.canvas.Canvas;

public class MissionControlStateTest {

	private static Canvas c;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c = new Canvas();
	}

	@Test
	@Ignore
	public void testTick() {
		fail("Not yet implemented");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissionControlStateNullRocket() {
		new MissionControlState(c.getGraphicsContext2D(), null, true);
	}

}
