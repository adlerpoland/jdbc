package com.project.domain;

public class InterwencjaHasPojazd {
	private long id_interwencja;
	private long id_pojazd;
	
	public InterwencjaHasPojazd(int id_int,int id_poj){
		this.id_interwencja = id_int;
		this.id_pojazd = id_poj;
	}
	
	public long getId_interwencja() {
		return id_interwencja;
	}
	public void setId_interwencja(long id) {
		this.id_interwencja = id;
	}
	
	public long getId_pojazd() {
		return id_pojazd;
	}
	public void setId_pojazd(long id) {
		this.id_pojazd = id;
	}
}
