package com.site3.ecommerce.requests;

import lombok.Data;

@Data
public class ContactRequest {
	
	private String country;
	private String city;
	private String street;
	private String postal;
	private String mobile;
	private String skype;

}
