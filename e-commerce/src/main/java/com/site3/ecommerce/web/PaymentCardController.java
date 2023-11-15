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
import com.site3.ecommerce.dto.PaymentCardDto;
import com.site3.ecommerce.requests.ContactRequest;
import com.site3.ecommerce.requests.PaymentCardRequest;
import com.site3.ecommerce.responses.ContactResponse;
import com.site3.ecommerce.responses.PaymentCardResponse;
import com.site3.ecommerce.services.PaymentCardService;

@RestController
@RequestMapping("/paymentCards")
public class PaymentCardController {
	
	@Autowired
	PaymentCardService paymentCardService;
	
	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<PaymentCardResponse> StorePaymentCard(@RequestBody PaymentCardRequest paymentCardRequest, Principal principal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PaymentCardDto paymentCardDto = modelMapper.map(paymentCardRequest, PaymentCardDto.class);
		PaymentCardDto createPaymentCard = paymentCardService.createPaymentCard(paymentCardDto, principal.getName());
		
		PaymentCardResponse newPaymentCard = modelMapper.map(createPaymentCard, PaymentCardResponse.class);
		
		return new ResponseEntity<PaymentCardResponse>(newPaymentCard, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<PaymentCardResponse> getOnePaymentCard(@PathVariable(name="id") String paymentCardId) {
		
		PaymentCardDto paymentCardDto = paymentCardService.getPaymentCard(paymentCardId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		PaymentCardResponse paymentCardResponse = modelMapper.map(paymentCardDto, PaymentCardResponse.class);
		
		return new ResponseEntity<PaymentCardResponse>(paymentCardResponse, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updatePaymentCard(@PathVariable(name="id") String paymentCardId) {
		
		
		/*
		 * 
		 * 
		 * a ajouter
		 * 
		 * 
		 * */
		return new ResponseEntity<>("update paymentCard", HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePaymentCard(@PathVariable(name="id") String paymentCardId) {
		
		paymentCardService.deletePaymentCard(paymentCardId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
