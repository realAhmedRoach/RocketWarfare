package dev.thetechnokid.rw.states;

import static org.junit.Assert.fail;

import org.junit.*;

public class MissionControlStateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Ignore
	public void testTick() {
		fail("Not yet implemented");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissionControlStateNullRocket() {
		new MissionControlState(null, null, true);
	}

}
