package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.domain.Pojazd;

public class PojazdManager {
	static java.sql.Statement stmt = null;
	static Connection conn;
	
	public boolean init_database(){
		final String DRIVER = "org.hsqldb.jdbcDriver";
	    final String DB_URL = "jdbc:hsqldb:file:mydb;ifexists=false;hsqldb.lock_file=false";
		
	    
	    
	    System.out.println("Nawi¹zywanie po³¹czenia z baz¹ danych\n");	
	    
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
					System.err.println("Problem z otwarciem po³¹czenia");
					//e.printStackTrace();
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
	    
	    return true;
	}
	
	public static int dodajPojazd(Pojazd i){
		int count = 0;
		try {
			String st = String.format("INSERT INTO Pojazd (marka,model) VALUES ('%s','%s')",i.getMarka(),i.getModel());
			count = conn.prepareStatement(st).executeUpdate();

			st = "SELECT MAX(id) FROM Pojazd";
			ResultSet result = stmt.executeQuery(st);
			result.next();
			int id = result.getInt(1);
			//System.out.println("ID TABELI: "+id);
			i.setId(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static int policz_pojazdy(){
		try{
			String st = String.format("SELECT Count(*) FROM Pojazd");
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
