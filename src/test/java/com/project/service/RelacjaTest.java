package com.project.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.project.domain.Interwencja;
import com.project.domain.Pojazd;


public class RelacjaTest {

	Interwencja interwencja_1 = new Interwencja("2010-01-01","GDANSK","TORUNSKA",15);
	Interwencja interwencja_2 = new Interwencja("2010-01-01","GDANSK","TORUNSKA",15);
	Pojazd pojazd_1 = new Pojazd("STAR","C");
	Pojazd pojazd_2 = new Pojazd("STAR","D");
	
	//DODANIE POJAZDOW DO INTERWENCJI
	@Test
	public void testDodajPojazdyDoInterwencji()
	{	
		PojazdManager.initDatabase();
		InterwencjaManager.initDatabase();
		InterwencjaHasPojazdManager.initDatabase();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();

		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(3,InterwencjaHasPojazdManager.policzInterwencjePojazdy());
	}
	
	//USUWANIE POJAZDOW Z INTERWENCJI
	@Test
	public void testUsunPojazdZInterwencji()
	{
		PojazdManager.initDatabase();
		InterwencjaManager.initDatabase();
		InterwencjaHasPojazdManager.initDatabase();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		PojazdManager.dodajPojazd(pojazd_1);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertEquals(1,InterwencjaHasPojazdManager.policzInterwencjePojazdy());
		assertTrue(InterwencjaHasPojazdManager.usunPojazdZInterwencji(interwencja_1,pojazd_1));
		assertEquals(0,InterwencjaHasPojazdManager.policzInterwencjePojazdy());
	}
	
	
	//POBRANIE X NALEZACYCH DO Y
	@Test
	public void testPobierzPojazdyNalezaceDoInterwencji()
	{
		PojazdManager.initDatabase();
		InterwencjaManager.initDatabase();
		InterwencjaHasPojazdManager.initDatabase();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(2,InterwencjaHasPojazdManager.policzPojazdyNaInterwencji(interwencja_1));
	}
	
	//POBRANIE Y NALEZACYCH DO X
	@Test
	public void testPobierzInterwencjePrzypisaneDoPojazdu()
	{
		PojazdManager.initDatabase();
		InterwencjaManager.initDatabase();
		InterwencjaHasPojazdManager.initDatabase();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(2,InterwencjaHasPojazdManager.policzInterwencjeDoPojazdu(pojazd_1));
	}
	
	//SKASOWANIE WSZYSTKICH DODANYCH POJAZDOW DO INTERWENCJI
	@Test
	public void testSkasujWszystkieDodanePojazdy()
	{
		PojazdManager.initDatabase();
		InterwencjaManager.initDatabase();
		InterwencjaHasPojazdManager.initDatabase();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_2));
		
		assertEquals(4,InterwencjaHasPojazdManager.policzInterwencjePojazdy());
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		assertEquals(0,InterwencjaHasPojazdManager.policzInterwencjePojazdy());
	}
	
}
