package com.site3.ecommerce.web;

import java.security.Principal;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.site3.ecommerce.dto.ContactDto;
import com.site3.ecommerce.requests.ContactRequest;
import com.site3.ecommerce.responses.ContactResponse;
import com.site3.ecommerce.services.ContactService;



@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	
	@Autowired
	ContactService contactService;

	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<ContactResponse> StoreContact(@RequestBody ContactRequest contactRequest, Principal principal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ContactDto contactDto = modelMapper.map(contactRequest, ContactDto.class);
		ContactDto createContact = contactService.createContact(contactDto, principal.getName());
		
		ContactResponse newContact = modelMapper.map(createContact, ContactResponse.class);
		
		return new ResponseEntity<ContactResponse>(newContact, HttpStatus.CREATED);
	}

		
	@GetMapping("/{id}")
	public  ResponseEntity<ContactResponse> getOneContact(@PathVariable(name="id") String contactId) {
		
		ContactDto contactDto = contactService.getContact(contactId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		ContactResponse contactResponse = modelMapper.map(contactDto, ContactResponse.class);
		
		return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateContact(@PathVariable(name="id") String contactId) {
		
		
		/*
		 * 
		 * 
		 * a ajouter
		 * 
		 * 
		 * */
		return new ResponseEntity<>("update contact", HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable(name="id") String contactId) {
		
		contactService.deleteContact(contactId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
