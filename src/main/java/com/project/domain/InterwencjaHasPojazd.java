package com.project.domain;

public class InterwencjaHasPojazd {
	private long Interwencja_id;
	private long Pojazd_id;
	
	public InterwencjaHasPojazd(int id_int,int id_poj){
		this.Interwencja_id = id_int;
		this.Pojazd_id = id_poj;
	}
	
	public long getId_interwencja() {
		return Interwencja_id;
	}
	public void setId_interwencja(long id) {
		this.Interwencja_id = id;
	}
	
	public long getId_pojazd() {
		return Pojazd_id;
	}
	public void setId_pojazd(long id) {
		this.Pojazd_id = id;
	}
}
