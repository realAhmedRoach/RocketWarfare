package dev.thetechnokid.rw.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanguageTest {

	@Test
	public void testGetEnglish() {
		Language.setLocale("en_US");
		assertEquals("Incorrect en_US locale", "name", Language.get("name", false));
		assertEquals("Incorrect en_US locale capitalized", "Name", Language.get("name", true));
	}
	
	@Test
	public void testGetSpanish() {
		Language.setLocale("es_ES");
		assertEquals("Incorrect es_ES locale", "nombre", Language.get("name", false));
		assertEquals("Incorrect es_ES locale capitalized", "Nombre", Language.get("name", true));
	}
	
	@Test
	public void testGetMultiple() {
		Language.setLocale("en_US");
		assertEquals("Incorrect multiple en_US", "altitude velocity", Language.get("altitude velocity", false));
		assertEquals("Incorrect multiple en_US capitalized", "Altitude Velocity", Language.get("altitude velocity", true));
		
		Language.setLocale("es_ES");
		assertEquals("Incorrect multiple es_ES", "altitud velocidad", Language.get("altitude velocity", false));
		assertEquals("Incorrect multiple es_ES capitalized", "Altitud Velocidad", Language.get("altitude velocity", true));
	}

}
