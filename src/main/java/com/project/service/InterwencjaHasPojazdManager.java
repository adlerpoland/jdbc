package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.domain.Interwencja;
import com.project.domain.Pojazd;

public class InterwencjaHasPojazdManager {
	static java.sql.Statement stmt = null;
	static Connection conn;
	
	public static boolean initDatabase(){
		final String DRIVER = "org.hsqldb.jdbcDriver";
		final String DB_URL = "jdbc:hsqldb:file:db/mydb;ifexists=false;hsqldb.lock_file=false";
		
		//System.out.println("Nawi¹zywanie po³¹czenia z baz¹ danych\n");	
	    
	    //Check JDBC
	    try 
	    {
			Class.forName(DRIVER);
		} 
	    catch (ClassNotFoundException e) 
	    {
			System.err.println("Brak sterownika JDBC");
			//e.printStackTrace();
		}
	    
	    //Connect
	    try {
			   conn = DriverManager.getConnection(DB_URL,"SA","");   
			   stmt = conn.createStatement();
		} catch (SQLException e) {
					System.err.println("Problem z otwarciem po³¹czenia");
					//e.printStackTrace();
		}      
	    
	    //INTERWENCJA
	    String createDataInterwencja="CREATE TABLE IF NOT EXISTS Interwencja(id INTEGER PRIMARY KEY IDENTITY, data_interwencji DATE, miejscowosc VARCHAR(30), ulica VARCHAR(30), numer INTEGER)";
		try {
			stmt.execute(createDataInterwencja);
		} catch (SQLException e) {
			System.err.println("Blad przy tworzeniu tabeli Interwencja");
			//e.printStackTrace();
			return false;
		}   
		
		//POJAZD
	    String createDataPojazd="CREATE TABLE IF NOT EXISTS Pojazd(id INTEGER PRIMARY KEY IDENTITY, marka VARCHAR(30),model VARCHAR(30), data_produkcji DATE, przebieg INTEGER)";
	    try {
	        stmt.execute(createDataPojazd);
	
	    } catch (SQLException e) {
	        System.err.println("Blad przy tworzeniu tabeli Pojazd");
	        //e.printStackTrace();
	        return false;
	    }
		
	    //INTERWENCJA HAS POJAZD
	    String createDataHas="CREATE TABLE IF NOT EXISTS Interwencja_has_Pojazd(Interwencja_id INTEGER REFERENCES Interwencja(id), Pojazd_id INTEGER REFERENCES Pojazd(id))";
	    try {
	        stmt.execute(createDataHas);
	
	    } catch (SQLException e) {
	        System.err.println("Blad przy tworzeniu tabeli Interwencja has Pojazd");
	        //e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public static boolean dodajPojazdDoInterwencji(Interwencja i, Pojazd p){
		try {
			String st = String.format("INSERT INTO Interwencja_has_Pojazd (Interwencja_id,Pojazd_id) VALUES ('%s','%s')",i.getId(),p.getId());
			conn.prepareStatement(st).executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean usunPojazdZInterwencji(Interwencja i, Pojazd p){
		try {
			String st = String.format("DELETE FROM Interwencja_has_Pojazd WHERE Interwencja_id='%s' AND Pojazd_id='%s'",i.getId(),p.getId());
			conn.prepareStatement(st).executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean skasujInterwencjaHasPojazd()
	{
		try {
			String st = String.format("DELETE FROM Interwencja_has_Pojazd");
			conn.prepareStatement(st).executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static int policzInterwencjePojazdy(){
		try{
			String st = String.format("SELECT Count(*) FROM Interwencja_has_Pojazd");
			ResultSet result = stmt.executeQuery(st);
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int policzPojazdyNaInterwencji(Interwencja i){
		try{
			String st = String.format("SELECT Count(*) FROM Interwencja_has_Pojazd WHERE Interwencja_id='%s'",i.getId());
			ResultSet result = stmt.executeQuery(st);
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int policzInterwencjeDoPojazdu(Pojazd i){
		try{
			String st = String.format("SELECT Count(*) FROM Interwencja_has_Pojazd WHERE Pojazd_id='%s'",i.getId());
			ResultSet result = stmt.executeQuery(st);
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
