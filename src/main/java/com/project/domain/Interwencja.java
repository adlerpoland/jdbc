package com.project.domain;

public class Interwencja {
	private long id;
	private String data_interwencji;
	private String miejscowosc;
	private String ulica;
	private int numer;
	
	public Interwencja(String data,String miejscowosc,String ulica,int numer){
		this.data_interwencji = data;
		this.miejscowosc = miejscowosc;
		this.ulica = ulica;
		this.numer = numer;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getData() {
		return data_interwencji;
	}
	
	public String getMiejscowosc() {
		return miejscowosc;
	}
	
	public String getUlica() {
		return ulica;
	}
	
	public int getNumer() {
		return numer;
	}
	
	public void setMiejscowosc(String x) {
		this.miejscowosc = x;
	}
}
