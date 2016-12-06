package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InterwencjaManager {
static java.sql.Statement stmt = null;
	
	public boolean connect_database(){
		final String DRIVER = "org.hsqldb.jdbcDriver";
	    final String DB_URL = "jdbc:hsqldb:file:mydb;ifexists=false;hsqldb.lock_file=false";
		
	    Connection conn;
	    
	    System.out.println("Nawiązywanie połączenia z bazą danych\n");	
	    
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
			   conn = DriverManager.getConnection(DB_URL,"admin","");   
			   stmt = conn.createStatement();
		} catch (SQLException e) {
					System.err.println("Problem z otwarciem połączenia");
					//e.printStackTrace();
		}      
	    return true;
	}
	
	public boolean init_database(){
		System.out.println("Inicjacja danych...\n");
		
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
}
