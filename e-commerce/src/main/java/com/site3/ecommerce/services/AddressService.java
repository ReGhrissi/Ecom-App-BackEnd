package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAllAddresses(String email);
	
	AddressDto createAddress(AddressDto address, String email);
	
    AddressDto getAddress(String addressId);
	
	void deleteAddress(String addressId);
}
