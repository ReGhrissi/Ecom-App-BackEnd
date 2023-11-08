package com.site3.ecommerce.services;


import com.site3.ecommerce.dto.ContactDto;


public interface ContactService {

	ContactDto createContact(ContactDto contact, String email);
	
	ContactDto getContact(String contactId);
	
	void deleteContact(String contactId);
	
}
