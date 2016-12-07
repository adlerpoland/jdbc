package com.project.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.project.domain.Interwencja;
import com.project.domain.Pojazd;


public class RelacjaTest {

	Interwencja interwencja_1 = new Interwencja("2010-01-01","GDAÑSK","TORUÑSKA",15);
	Interwencja interwencja_2 = new Interwencja("2010-01-01","GDAÑSK","TORUÑSKA",15);
	Pojazd pojazd_1 = new Pojazd("STAR","C");
	Pojazd pojazd_2 = new Pojazd("STAR","D");
	
	//DODANIE POJAZDOW DO INTERWENCJI
	@Test
	public void test_dodaj_pojazdy_do_interwencji()
	{	
		PojazdManager.init_database();
		InterwencjaManager.init_database();
		InterwencjaHasPojazdManager.init_database();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();

		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(3,InterwencjaHasPojazdManager.policz_interwencje_pojazdy());
	}
	
	//USUWANIE POJAZDOW Z INTERWENCJI
	@Test
	public void test_usun_pojazd_z_interwencji()
	{
		PojazdManager.init_database();
		InterwencjaManager.init_database();
		InterwencjaHasPojazdManager.init_database();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		PojazdManager.dodajPojazd(pojazd_1);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertEquals(1,InterwencjaHasPojazdManager.policz_interwencje_pojazdy());
		assertTrue(InterwencjaHasPojazdManager.usunPojazdZInterwencji(interwencja_1,pojazd_1));
		assertEquals(0,InterwencjaHasPojazdManager.policz_interwencje_pojazdy());
	}
	
	
	//POBRANIE X NALEZACYCH DO Y
	@Test
	public void test_pobierz_pojazdy_nalezace_do_interwencji()
	{
		PojazdManager.init_database();
		InterwencjaManager.init_database();
		InterwencjaHasPojazdManager.init_database();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(2,InterwencjaHasPojazdManager.policz_pojazdy_na_interwencji(interwencja_1));
	}
	
	//POBRANIE Y NALEZACYCH DO X
	@Test
	public void test_pobierz_interwencje_przypisane_do_pojazdu()
	{
		PojazdManager.init_database();
		InterwencjaManager.init_database();
		InterwencjaHasPojazdManager.init_database();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		
		assertEquals(2,InterwencjaHasPojazdManager.policz_interwencje_do_pojazdu(pojazd_1));
	}
	
	//SKASOWANIE WSZYSTKICH DODANYCH POJAZDOW DO INTERWENCJI
	@Test
	public void test_skasuj_wszystkie_dodane_pojazdy()
	{
		PojazdManager.init_database();
		InterwencjaManager.init_database();
		InterwencjaHasPojazdManager.init_database();
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		
		InterwencjaManager.dodajInterwencja(interwencja_1);
		InterwencjaManager.dodajInterwencja(interwencja_2);
		PojazdManager.dodajPojazd(pojazd_1);
		PojazdManager.dodajPojazd(pojazd_2);
		
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_1,pojazd_2));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_1));
		assertTrue(InterwencjaHasPojazdManager.dodajPojazdDoInterwencji(interwencja_2,pojazd_2));
		
		assertEquals(4,InterwencjaHasPojazdManager.policz_interwencje_pojazdy());
		
		InterwencjaHasPojazdManager.skasujInterwencjaHasPojazd();
		assertEquals(0,InterwencjaHasPojazdManager.policz_interwencje_pojazdy());
	}
	
}
