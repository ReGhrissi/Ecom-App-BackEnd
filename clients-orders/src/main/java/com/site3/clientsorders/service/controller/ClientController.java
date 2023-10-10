package com.site3.clientsorders.service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site3.clientsorders.dao.ClientRepository;
import com.site3.clientsorders.entities.Client;
import com.site3.clientsorders.entities.Order;
import com.site3.clientsorders.entities.OrderItem;
import com.site3.clientsorders.service.model.Product;
import com.site3.clientsorders.web.OrderForm;
import com.site3.clientsorders.web.OrderProduct;

@CrossOrigin("*")
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	 @Autowired
	 private ClientRepository clientRepository;
	 
	 
	 
	 	@PostMapping("/clients")
	 	
	    public Client saveClient(@RequestBody OrderForm orderForm){
	    	
	        Client client=new Client();
	        client.setName(orderForm.getClient().getName());
	        client.setEmail(orderForm.getClient().getEmail());
	        client.setAddress(orderForm.getClient().getAddress());
	        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
	        client.setUsername(orderForm.getClient().getUsername());
	        
	       // System.out.println(client.getId());
	        
	        return clientRepository.save(client);
	 	}
	 
}
