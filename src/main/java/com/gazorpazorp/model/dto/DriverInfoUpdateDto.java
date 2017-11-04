package com.gazorpazorp.model.dto;

import com.gazorpazorp.model.Driver;

public class DriverInfoUpdateDto {
	private String car;
	private String file;
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public void Incorporate (Driver driver) {
		if (car != null) 
			driver.setCar(car);		
	}
}
