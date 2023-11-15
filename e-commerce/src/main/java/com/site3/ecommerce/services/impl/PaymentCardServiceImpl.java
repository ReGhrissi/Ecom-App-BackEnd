package com.site3.ecommerce.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.ContactRepository;
import com.site3.ecommerce.dao.PaymentCardRepository;
import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.ContactDto;
import com.site3.ecommerce.dto.PaymentCardDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.ContactEntity;
import com.site3.ecommerce.entities.PaymentCardEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.services.PaymentCardService;
import com.site3.ecommerce.shared.Utils;



@Service
public class PaymentCardServiceImpl implements PaymentCardService {
	
	@Autowired
	PaymentCardRepository paymentCardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils util;

	
	@Override
	public PaymentCardDto createPaymentCard(PaymentCardDto paymentCard, String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		paymentCard.setPaymentCardId(util.generateStringId(30));
		paymentCard.setUser(userDto);
		
		PaymentCardEntity paymentCardEntity = modelMapper.map(paymentCard, PaymentCardEntity.class); 
		
		PaymentCardEntity newPaymentCard = paymentCardRepository.save(paymentCardEntity);
		
		PaymentCardDto paymentCardDto = modelMapper.map(newPaymentCard, PaymentCardDto.class);
		
		return paymentCardDto;
	}

	
	@Override
	public PaymentCardDto getPaymentCard(String paymentCardId) {

		PaymentCardEntity paymentCardEntity = paymentCardRepository.findByPaymentCardId(paymentCardId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		PaymentCardDto paymenDto = modelMapper.map(paymentCardEntity, PaymentCardDto.class);
		
		return paymenDto;
	}

	@Override
	public void deletePaymentCard(String paymentCardId) {
		
		PaymentCardEntity paymentCard = paymentCardRepository.findByPaymentCardId(paymentCardId);
		
		if(paymentCard == null) throw new RuntimeException("PaymentCard not found");
		
		paymentCardRepository.delete(paymentCard);
		
	}

}
