package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.domain.Interwencja;

public class InterwencjaManager {
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
	
	public static boolean dodajInterwencja(Interwencja i){
		try {
			String st = String.format("INSERT INTO Interwencja (data_interwencji,miejscowosc,ulica,numer) VALUES ('%s','%s','%s','%s')",i.getData(),i.getMiejscowosc(),i.getUlica(),i.getNumer());
			//System.out.println(st);
			conn.prepareStatement(st).executeUpdate();

			st = "SELECT MAX(id) FROM Interwencja";
			ResultSet result = stmt.executeQuery(st);
			result.next();
			int id = result.getInt(1);
			//System.out.println("ID TABELI: "+id);
			i.setId(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static int policzInterwencje(){
		try{
			String st = String.format("SELECT Count(*) FROM Interwencja");
			ResultSet result = stmt.executeQuery(st);
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean usunInterwencje(Interwencja i){
		try{
			String st = String.format("DELETE FROM Interwencja WHERE id='%s'",i.getId());
			conn.prepareStatement(st).executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean usunWszystkieInterwencje(){
		try{
			String st = String.format("DELETE FROM Interwencja");
			conn.prepareStatement(st).executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean aktualizujInterwencje(long id,String data,String miejscowosc,String ulica,int numer){
		try{
			String st = String.format("UPDATE Interwencja SET data_interwencji='%s', miejscowosc='%s', ulica='%s', numer='%s' WHERE id='%s'",data,miejscowosc,ulica,numer,id);
			conn.prepareStatement(st).executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sprawdzCzyIstnieje(long id){
		try{
			String st = String.format("SELECT COUNT(*) FROM Interwencja WHERE id='%s'",id);
			ResultSet result = stmt.executeQuery(st);
			result.next();
			if(result.getInt(1)>=1) 
				return true;
			else
				return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}
