package com.site3.ecommerce.responses;

import lombok.Data;

@Data
public class ContactResponse {

	private String contactId;
	
	private String country;
	private String city;
	private String street;
	private String postal;
	
	private String mobile;
	private String skype;
	
}
