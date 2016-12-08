package com.project.service;
import org.junit.Test;

import com.project.domain.Interwencja;

import static org.junit.Assert.*;

public class InterwencjaManagerTest {

	private final static String DATA = "2014-01-02";
	private final static String MIEJSCOWOSC = "Gdansk";
	private final static String ULICA = "KARTUSKA";
	private final static int NUMER = 152;
	
	static Interwencja interwencja_x = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
	static Interwencja interwencja_z = new Interwencja("2015-02-1","GDANSK","SZEROKA",16);
	
	@Test 
	public void testConnection()
	{
		assertTrue(InterwencjaManager.initDatabase());
		
	}
	
	@Test
	public void testDodajJednaInterwencje()
	{		
		InterwencjaManager.initDatabase();
		assertTrue(InterwencjaManager.dodajInterwencja(interwencja_x));
	}
	
	
	@Test
	public void testPoliczInterwencje()
	{
		InterwencjaManager.initDatabase();
		InterwencjaManager.usunWszystkieInterwencje();
		
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja);
		Interwencja interwencja_2 = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		
		InterwencjaManager.dodajInterwencja(interwencja_z);
		
		assertEquals(3,InterwencjaManager.policzInterwencje());
	}
	
	@Test
	public void testUsunJednaInterwencje()
	{
		InterwencjaManager.initDatabase();
		InterwencjaManager.usunWszystkieInterwencje();
		
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		Interwencja interwencja_2 = new Interwencja("2010-01-01","GDANSK","TORUNSKA",15);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		
		assertTrue(InterwencjaManager.sprawdzCzyIstnieje(interwencja_2.getId()));
		InterwencjaManager.usunInterwencje(interwencja_2);
		assertFalse(InterwencjaManager.sprawdzCzyIstnieje(interwencja_2.getId()));
		assertEquals(1,InterwencjaManager.policzInterwencje());
	}
	
	
	@Test
	public void testUsunWszystkieInterwencje()
	{
		InterwencjaManager.initDatabase();
		InterwencjaManager.usunWszystkieInterwencje();
		assertEquals(0,InterwencjaManager.policzInterwencje());
	}
	
	
	@Test
	public void testZmienDaneInterwencji()
	{
		InterwencjaManager.initDatabase();
		Interwencja interwencja = new Interwencja("2013-01-01","GDYNIA","STARA",1);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		long id = interwencja.getId();
		assertTrue(InterwencjaManager.aktualizujInterwencje(id,"2010-05-05","SOPOT","WISLANA",15));
	}
}
