package com.project.service;
import org.junit.Test;
import static org.junit.Assert.*;

public class InterwencjaManagerTest {

	@Test 
	public void test_connection()
	{
		InterwencjaManager x = new InterwencjaManager();
		assertTrue(x.connect_database());
		
	}
	
	@Test
	public void test_create_tables()
	{
		InterwencjaManager x = new InterwencjaManager();
		assertTrue(x.init_database());
	}
	
}
