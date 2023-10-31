package com.site3.ecommerce.responses;

import lombok.Data;

@Data
public class AddressResponse {

	private String addressId;
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;

}
