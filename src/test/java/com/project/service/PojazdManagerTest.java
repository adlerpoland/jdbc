package com.project.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.project.domain.Pojazd;

public class PojazdManagerTest {	
	
	static Pojazd pojazd_1 = new Pojazd("STAR","152A");
	static Pojazd pojazd_2 = new Pojazd("MAN","STRONG");
	static Pojazd pojazd_3 = new Pojazd("STAR","ALFA");
	
	static Pojazd pojazd_4 = new Pojazd("STAR","A");
	static Pojazd pojazd_5 = new Pojazd("STAR","B");
	
	@Test
	public void testDodajTrzyPojazdy()
	{		
		PojazdManager.initDatabase();
		PojazdManager.usunWszystkiePojazdy();
		
		assertEquals(1,PojazdManager.dodajPojazd(pojazd_1));
		assertEquals(1,PojazdManager.dodajPojazd(pojazd_2));
		assertEquals(1,PojazdManager.dodajPojazd(pojazd_3));
		assertEquals(1,PojazdManager.dodajPojazd(pojazd_4));
		assertEquals(1,PojazdManager.dodajPojazd(pojazd_5));
		assertEquals(5,PojazdManager.policzPojazdy());
	}
	
	
}
