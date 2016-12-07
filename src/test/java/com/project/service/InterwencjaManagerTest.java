package com.project.service;
import org.junit.Test;

import com.project.domain.Interwencja;

import static org.junit.Assert.*;

public class InterwencjaManagerTest {

	private final static String DATA = "2014-01-02";
	private final static String MIEJSCOWOSC = "Gdañsk";
	private final static String ULICA = "KARTUSKA";
	private final static int NUMER = 152;
	
	@Test 
	public void test_connection_and_create_tables()
	{
		InterwencjaManager x = new InterwencjaManager();
		assertTrue(x.init_database());
		
	}
	
	@Test
	public void test_dodaj_jedna_interwencje()
	{
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		
		assertEquals(1,InterwencjaManager.dodajInterwencja(interwencja));
	}
	
	@Test
	public void test_policz_interwencje()
	{
		Interwencja interwencja = new Interwencja(DATA,MIEJSCOWOSC,ULICA,NUMER);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		assertEquals(2,InterwencjaManager.policz_interwencje());
	}
	
	@Test
	public void test_usun_jedna_interwencje()
	{
		Interwencja interwencja = new Interwencja("2010-01-01","GDAÑSK","TORUÑSKA",15);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		assertTrue(InterwencjaManager.usun_interwencje(interwencja));
		assertEquals(2,InterwencjaManager.policz_interwencje());
	}
	
	@Test
	public void test_usun_wszystkie_interwencje()
	{
		assertTrue(InterwencjaManager.usun_wszystkie_interwencje());
		assertEquals(0,InterwencjaManager.policz_interwencje());
	}
	
	@Test
	public void test_zmien_dane_interwencji()
	{
		Interwencja interwencja = new Interwencja("2013-01-01","GDYNIA","STARA",1);
		InterwencjaManager.dodajInterwencja(interwencja);
		
		long id = interwencja.getId();
		assertTrue(InterwencjaManager.aktualizuj_interwencje(id,"2010-05-05","SOPOT","WISLANA",15));
	}
}
