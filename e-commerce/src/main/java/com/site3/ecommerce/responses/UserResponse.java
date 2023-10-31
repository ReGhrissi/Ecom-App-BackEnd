package com.site3.ecommerce.responses;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
	private Boolean admin;
	
	private List<AddressResponse> addresses;
	private ContactResponse contact;
}
