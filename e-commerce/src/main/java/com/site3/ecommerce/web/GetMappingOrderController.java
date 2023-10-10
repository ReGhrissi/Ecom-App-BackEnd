package com.site3.ecommerce.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.site3.ecommerce.dao.OrderRepository;
import com.site3.ecommerce.entities.Order;



@CrossOrigin("*")
@RestController
public class GetMappingOrderController {
	
/*	
	   @Autowired
	   private OrderRepository orderRepository;
	
	
	 @GetMapping("/orders/{orderId}")
	//@RequestMapping(path = "/orders/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Long orderId) throws Exception
    {
			Order orderDB = new Order();
		    	
		    orderDB = orderRepository.findById(orderId).get();
		    
		    return orderDB;
		        
    }
*/
}
