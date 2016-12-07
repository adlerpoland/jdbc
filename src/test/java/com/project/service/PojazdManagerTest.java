package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.project.domain.Interwencja;
import com.project.domain.Pojazd;

public class PojazdManagerTest {	
	@Test 
	public void test_connection_and_create_tables()
	{
		PojazdManager x = new PojazdManager();
		assertTrue(x.init_database());
	}
	
	@Test
	public void test_dodaj_trzy_pojazdy()
	{
		Pojazd pojazd_1 = new Pojazd("STAR","152A");
		Pojazd pojazd_2 = new Pojazd("MAN","STRONG");
		Pojazd pojazd_3 = new Pojazd("STAR","ALFA");
		
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		PojazdManager.dodajPojazd(pojazd_3);
		assertEquals(3,PojazdManager.policz_pojazdy());
	}
}
