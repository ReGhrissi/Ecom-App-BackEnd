package com.site3.ecommerce.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.AddressRepository;
import com.site3.ecommerce.dao.ContactRepository;
import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.AddressDto;
import com.site3.ecommerce.dto.ContactDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.AddressEntity;
import com.site3.ecommerce.entities.ContactEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.services.ContactService;
import com.site3.ecommerce.shared.Utils;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils util;

	@Override
	public ContactDto createContact(ContactDto contact, String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		contact.setContactId(util.generateStringId(30));
		contact.setUser(userDto);
		
		ContactEntity contactEntity = modelMapper.map(contact, ContactEntity.class); 
		
		ContactEntity newContact = contactRepository.save(contactEntity);
		
		ContactDto contactDto = modelMapper.map(newContact, ContactDto.class);
		
		return contactDto;
	}

	
	
	@Override
	public ContactDto getContact(String contactId) {
		
		ContactEntity contactEntity = contactRepository.findByContactId(contactId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		ContactDto contactDto = modelMapper.map(contactEntity, ContactDto.class);
		
		return contactDto;
	}

	
	
	@Override
	public void deleteContact(String contactId) {
		
		ContactEntity contact = contactRepository.findByContactId(contactId);
		
		if(contact == null) throw new RuntimeException("Contact not found");
		
		contactRepository.delete(contact);
	
	}
	

}
