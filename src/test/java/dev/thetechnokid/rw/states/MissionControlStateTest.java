package dev.thetechnokid.rw.states;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MissionControlStateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Ignore
	public void testTick() {
		fail("Not yet implemented");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testMissionControlState() {
		new MissionControlState(null, null);
	}

}
