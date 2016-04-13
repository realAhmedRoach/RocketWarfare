package dev.thetechnokid.rw.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.thetechnokid.rw.maths.Direction;

public class DirectionTest {

	private Direction d = new Direction(87);

	@Test
	public void testGetAltitudeModifier() {
		double mod = 87.0 / 90.0;
		assertEquals("Incorrect Altitude modifier for QI", mod, d.getAltitudeModifier(), 0);
	}

	@Test
	public void testGetXModifier() {
		double mod = 3.0 / 90.0;
		assertEquals("Incorrect X modifier for QI", mod, d.getXModifier(), 0);
	}

}
