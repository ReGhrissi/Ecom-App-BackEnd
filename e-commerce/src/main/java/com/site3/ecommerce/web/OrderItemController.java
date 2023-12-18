package com.site3.ecommerce.web;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site3.ecommerce.dto.OrderDto;
import com.site3.ecommerce.responses.OrderResponse;
import com.site3.ecommerce.services.OrderItemService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
	
	@Autowired
	OrderItemService orderItemService;
	
	
	
	@GetMapping(path="/totalProductsCountByProductId/{productId}")
	public long getTotalProductsCountByProductId(@PathVariable String productId) {
					
			return  orderItemService.getTotalProductsCountById(productId);			
	} 

}
