package com.gazorpazorp.model.dto;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Location;

public class CustomerInfoUpdateDto {
	private Location location;
	
	
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}



	public void Incorporate (Customer customer) {
		if (location != null) {
			customer.setLocation(location);
		}
	}
}
