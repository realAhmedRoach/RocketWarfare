package dev.thetechnokid.rw.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.thetechnokid.rw.maths.Direction;

public class DirectionTest {
	
	private Direction d = new Direction(87);
	
	@Test
	public void testGetAltitudeModifier() {
		assertEquals("Incorrect Altitude modifier for QI", 87/90, d.getAltitudeModifier(), 0);
	}

	@Test
	public void testGetXModifier() {
		assertEquals("Incorrect X modifier for QI", 3/90, d.getXModifier(), 0);
	}

}
