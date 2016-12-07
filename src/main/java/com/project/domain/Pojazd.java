package com.project.domain;

public class Pojazd {
	private long id;
	private String marka;
	private String model;
	
	public Pojazd(String marka,String model){
		this.marka = marka;
		this.model = model;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMarka() {
		return marka;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
}
