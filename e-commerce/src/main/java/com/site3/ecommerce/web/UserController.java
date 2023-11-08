package com.site3.ecommerce.web;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.Product;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.exceptions.UserException;
import com.site3.ecommerce.requests.UserRequest;
import com.site3.ecommerce.responses.ErrorMessages;
import com.site3.ecommerce.responses.UserResponse;
import com.site3.ecommerce.services.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

//------------------------------------------- getUser() ------------------------------
	//@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@GetMapping(path="/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		
		UserDto userDto = userService.getUserByUserId(id);
		
		UserResponse userResponse = new UserResponse();
		
		//BeanUtils.copyProperties(userDto, userResponse);
		
//----------------------------------------------------
		ModelMapper modelMapper = new ModelMapper();
		
		userResponse =  modelMapper.map(userDto, UserResponse.class);
		
		//usersResponse.add(userResponse);
//-----------------------------------------------------------		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

//------------------------------------------------ getAllUsers() -----------------------------------
	//@GetMapping(produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@GetMapping()
	public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(value="page", defaultValue = "1")
	 											int page,@RequestParam(value="limit", defaultValue = "4")  
	 											int limit ,@RequestParam(value="search", defaultValue = "")
	  											String search,@RequestParam(value="status", defaultValue = "1") 
	  											int status) {
		
		List<UserResponse> usersResponse = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page, limit, search, status);
		
		for(UserDto userDto: users) {
			
			ModelMapper modelMapper = new ModelMapper();
			UserResponse userResponse =  modelMapper.map(userDto, UserResponse.class);
			
			usersResponse.add(userResponse);
		}
		
		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
	}
	
//---------------------------------------------- createUser() ---------------------------------------
	
	//@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
	//	    	 produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@PostMapping()
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		
		if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userRequest, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		
		UserDto createUser = userService.createUser(userDto);
		
		UserResponse userResponse =  modelMapper.map(createUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
		
		
	}
	
//---------------------------------------- updateUser() ----------------------------------------------

	//@PutMapping(path="/{id}", consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
	//		 				  produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@PutMapping(path="/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		
		//BeanUtils.copyProperties(userRequest, userDto);
		ModelMapper modelMapper = new ModelMapper();
		userDto = modelMapper.map(userRequest, UserDto.class);
		
		UserDto updateUser = userService.updateUser(id, userDto);
		
		UserResponse userResponse = new UserResponse();
		
		//BeanUtils.copyProperties(updateUser, userResponse);
		userResponse = modelMapper.map(updateUser, UserResponse.class);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

//---------------------------------------- deleteUser() ------------------------------------------------
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
//------------------------------------------Photo User ---------------------------------------------------
	
	// methodes pour la recuperation des photo des users
	
	@GetMapping(path="/photoUser/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	
	public byte[] getPhoto(@PathVariable("id") String id) throws Exception
	{
		UserEntity u = userRepository.findByUserId(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Users/"+u.getPhotoName()));
		
		
	}

	
	@PostMapping(path="/uploadPhoto/{id}")
	public  void uploadPhoto(MultipartFile file,@PathVariable String id) throws Exception
	{
		
		UserEntity u = userRepository.findByUserId(id);
		
		u.setPhotoName(id+".jpg");
		
		Files.write(Paths.get(System.getProperty("user.home")+"/Images_Projets/Projet_Ecommerce/Users/"+u.getPhotoName()),file.getBytes());
		userRepository.save(u);
	
	}
	
}
