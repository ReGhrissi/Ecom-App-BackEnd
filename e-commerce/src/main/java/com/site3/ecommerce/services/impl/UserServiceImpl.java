package com.site3.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.site3.ecommerce.dao.ContactRepository;
import com.site3.ecommerce.dao.PaymentCardRepository;
import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.AddressDto;
import com.site3.ecommerce.dto.ContactDto;
import com.site3.ecommerce.dto.PaymentCardDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.ContactEntity;
import com.site3.ecommerce.entities.PaymentCardEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.services.UserService;
import com.site3.ecommerce.shared.Utils;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	PaymentCardRepository paymentCardRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	Utils util;
	
	
//--------------------------------------------------------------------------
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		
		if(checkUser != null) throw new RuntimeException("User Alrady Exists !");
		
/*		
		for(int i=0; i < user.getAddresses().size(); i++) {
			
			AddressDto address = user.getAddresses().get(i);
			address.setUser(user);
			address.setAddressId(util.generateStringId(30));
			user.getAddresses().set(i, address);
		}
*/		
	//	user.getContact().setContactId(util.generateStringId(30));
	//	user.getContact().setUser(user);
		
//------------- paymentCard-----------------------		
	//	user.getPaymentCard().setPaymentCardId(util.generateStringId(30));
	//	user.getPaymentCard().setUser(user);
		
        ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userEntity.setUserId(util.generateStringId(32));
		userEntity.setAdmin(false);
		userEntity.setActive(true);
		userEntity.setPhotoName("unknown.jpg");
		
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto =  modelMapper.map(newUser, UserDto.class);
		
		return userDto; 
	}

//----------------------------------------------------------------------------------
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email); 
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

//-------------------------------------------------------------------------------------
	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email); 
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

//-------------------------------------------------------------------------------------
	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId); 
		
		UserDto userDto = new UserDto();
		
		//BeanUtils.copyProperties(userEntity, userDto);
	
//----------------------
		ModelMapper modelMapper = new ModelMapper();	
		userDto = modelMapper.map(userEntity, UserDto.class);
		
		//usersDto.add(user);
//----------------------------		
		return userDto;
	}

//--------------------------------------------------------------------------------------
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
	    if (userEntity == null) 
	    {
	        throw new UsernameNotFoundException(userId);
	    }
//----------	
		//long UserID = userEntity.getId();
		
		//ContactEntity contactEntity = contactRepository.findByUserId(UserID);
		
		ContactEntity contactEntity = userEntity.getContact();
		PaymentCardEntity paymentCardEntity = userEntity.getPaymentCard();
		
	    if (contactEntity != null && userDto.getContact() !=null) 
	    {
	        // Si elle n'existe pas, créez une nouvelle entité ContactEntity
	      //  contactEntity = new ContactEntity();
	      //  contactEntity.setContactId(util.generateStringId(30));
	      //  contactEntity.setUser(userEntity);
		    ContactDto contactDto = userDto.getContact();
		    
		    contactEntity.setCountry(contactDto.getCountry());
		    contactEntity.setCity(contactDto.getCity());
		    contactEntity.setStreet(contactDto.getStreet());
		    contactEntity.setPostal(contactDto.getPostal());
		    contactEntity.setMobile(contactDto.getMobile());
		    contactEntity.setSkype(contactDto.getSkype());
		    
		    ContactEntity contactUpdated = contactRepository.save(contactEntity);
		    userEntity.setContact(contactUpdated);
	    	
	    }
	    
	    if (paymentCardEntity != null && userDto.getPaymentCard() != null) 
	    {
	    	//paymentCardEntity = new PaymentCardEntity();
	    	//paymentCardEntity.setPaymentCardId(util.generateStringId(30));
	    	//paymentCardEntity.setUser(userEntity);
	    	
		    PaymentCardDto paymentCardDto = userDto.getPaymentCard();
	
		    paymentCardEntity.setCardNumber(paymentCardDto.getCardNumber());
		    paymentCardEntity.setCardOwner(paymentCardDto.getCardOwner());
		    
		    PaymentCardEntity paymentCardUpdated = paymentCardRepository.save(paymentCardEntity);
		    userEntity.setPaymentCard(paymentCardUpdated);
	    }
	    

	   

	    
	    // Mettez à jour les propriétés de l'entité UserEntity  
	    userEntity.setFirstName(userDto.getFirstName());
	    userEntity.setLastName(userDto.getLastName());
	    
	    if(userDto.getAdmin() != null)
	    {
	    userEntity.setAdmin(userDto.getAdmin());
	    }
	    
	    if(userDto.getActive() != null)
	    {
	    	userEntity.setActive(userDto.getActive());
	    }
	    // Sauvegardez les entités mises à jour
	   
	    

	    
	    

	    
	    UserEntity userUpdated = userRepository.save(userEntity);

//----------
	
		//BeanUtils.copyProperties(contactDto,contactEntity );
		
		ModelMapper modelMapper = new ModelMapper();	
	
		UserDto user = new UserDto();
		
		//BeanUtils.copyProperties(userUpdated, user);
		user = modelMapper.map(userUpdated, UserDto.class);
		
		return user;
	}

//----------------------------------------------------------------------------------
	@Override
	public void deleteUser(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId); 
		
		userRepository.delete(userEntity);
		
	}

//----------------------------------------------------------------------------------
	@Override
	public List<UserDto> getUsers(int page, int limit, String search, int status) {
		
		if(page > 0) page = page - 1;
		
		List<UserDto> usersDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage;
		
		if(search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			
			userPage = userRepository.findAllUserByCriteria(pageableRequest, search, status);
		}
		
		
		List<UserEntity> users = userPage.getContent();
		
		for(UserEntity userEntity: users) {
			
			ModelMapper modelMapper = new ModelMapper();	
			UserDto user = modelMapper.map(userEntity, UserDto.class);
			
			usersDto.add(user);
		}
		
		return usersDto;
	}

}
