package com.gazorpazorp.model.dto;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Location;

public class CustomerInfoUpdateDto {
	private Location location;
	private String profileImageId;
	
	
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getProfileImageId() {
		return profileImageId;
	}
	public void setProfileImageId(String profileImageId) {
		this.profileImageId = profileImageId;
	}
	
	public void Incorporate (Customer customer) {
		if (location != null) 
			customer.setLocation(location);		
		if (profileImageId != null)
			customer.setProfileImageId(profileImageId);
	}
}
