package com.project.service;
import org.junit.Test;

import com.project.domain.Interwencja;

import static org.junit.Assert.*;

public class InterwencjaManagerTest {

	private final static String DATA = "2014-01-02";
	private final static String MIEJSCOWOSC = "Gdañsk";
	private final static String ULICA = "KARTUSKA";
	private final static int NUMER = 152;
	
	static Interwencja interwencja_x = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
	static Interwencja interwencja_z = new Interwencja("2015-02-1","GDANSK","SZEROKA",16);
	
	@Test 
	public void test_connection()
	{
		assertTrue(InterwencjaManager.init_database());
		
	}
	
	@Test
	public void test_dodaj_jedna_interwencje()
	{		
		InterwencjaManager.init_database();
		assertTrue(InterwencjaManager.dodajInterwencja(interwencja_x));
	}
	
	
	@Test
	public void test_policz_interwencje()
	{
		InterwencjaManager.init_database();
		InterwencjaManager.usun_wszystkie_interwencje();
		
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja);
		Interwencja interwencja_2 = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		
		InterwencjaManager.dodajInterwencja(interwencja_z);
		
		assertEquals(3,InterwencjaManager.policz_interwencje());
	}
	
	@Test
	public void test_usun_jedna_interwencje()
	{
		InterwencjaManager.init_database();
		InterwencjaManager.usun_wszystkie_interwencje();
		
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		Interwencja interwencja_2 = new Interwencja("2010-01-01","GDAÑSK","TORUÑSKA",15);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		
		InterwencjaManager.usun_interwencje(interwencja_2);
		assertEquals(1,InterwencjaManager.policz_interwencje());
	}
	
	
	@Test
	public void test_usun_wszystkie_interwencje()
	{
		InterwencjaManager.init_database();
		InterwencjaManager.usun_wszystkie_interwencje();
		assertEquals(0,InterwencjaManager.policz_interwencje());
	}
	
	
	@Test
	public void test_zmien_dane_interwencji()
	{
		InterwencjaManager.init_database();
		Interwencja interwencja = new Interwencja("2013-01-01","GDYNIA","STARA",1);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		long id = interwencja.getId();
		assertTrue(InterwencjaManager.aktualizuj_interwencje(id,"2010-05-05","SOPOT","WISLANA",15));
	}
}
