package com.site3.ecommerce.web;

import com.site3.ecommerce.dao.*;
import com.site3.ecommerce.entities.*;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {
	
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    
    @RequestMapping(path = "/orders", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> handleOrdersRequest_1(HttpServletRequest request, @RequestBody(required = false) OrderForm orderForm)
    {
        
    	if (request.getMethod().equals("GET")) 
        {
    			java.util.List<Order> orders = orderRepository.findAll();
    		
    		return ResponseEntity.ok(orders);
        } 
        
        
        else if (request.getMethod().equals("POST")) 
        {
        	 
        	        Client client=new Client();
        	        
        	        client.setName(orderForm.getClient().getName());
        	        client.setEmail(orderForm.getClient().getEmail());
        	        client.setAddress(orderForm.getClient().getAddress());
        	        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        	        client.setUsername(orderForm.getClient().getUsername());
        	        
        	        client=clientRepository.save(client);
        	        
        	        System.out.println("nouveau client :"+client.getId());

        	        Order order=new Order();
        	        
        	        order.setClient(client);
        	        order.setDate(new Date());
        	        
        	        order=orderRepository.save(order);
        	        double total=0;
        	        
	        	        for(OrderProduct p:orderForm.getProducts())
	        	        {
	        	        	//enregitrement des orderItems
	        	            OrderItem orderItem=new OrderItem();
	        	            
	        	            orderItem.setOrder(order);
	        	            Product product=productRepository.findById(p.getId()).get();
	        	            orderItem.setProduct(product);
	        	            orderItem.setPrice(product.getCurrentPrice());
	        	            orderItem.setQuantity(p.getQuantity());
	        	            orderItemRepository.save(orderItem);
	        	            total+=p.getQuantity()*product.getCurrentPrice();
	        	        }
        	        
        	        order.setTotalAmount(total);
        	        
        	         Order orderPOST = orderRepository.save(order);
        	         System.out.println("le nouveau orderPOST :"+orderPOST);
        	         
        	    return ResponseEntity.status(HttpStatus.CREATED).body(orderPOST);
        	     
        }
    	else 
    	{
    			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    	}
    	
    }
   
    @RequestMapping(path = "/orders/{orderId}", method = { RequestMethod.GET, RequestMethod.PATCH })
    public ResponseEntity<?> handleeOrdersRequest_2(HttpServletRequest request , @PathVariable Long orderId, @RequestBody(required = false) OrderForm orderForm) 
    {
        if (request.getMethod().equals("GET")) 
        {
        		Order orderGET = new Order();
	    	
        		orderGET = orderRepository.findById(orderId).get();
 		    
 		   return ResponseEntity.ok(orderGET);
        } 
        
        else if (request.getMethod().equals("PATCH")) 
        {
        	Order orderPATCH = new Order();
        	
            orderPATCH = orderRepository.findById(orderId).get();
            
            System.out.println("order a modifier :"+ orderPATCH);
    	        	
    	    Payment paymentOfOrderPATCH = orderPATCH.getPayment();
    	            
    	   // paymentOfOrderPATCH.setId(orderForm.getPayment().getId());
    	           
    	    orderPATCH.setPayment(paymentOfOrderPATCH);
    	    
    	    orderPATCH = orderRepository.save(orderPATCH);

    	   return ResponseEntity.ok(orderPATCH);
        }
    	else 
    	{
    			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    	}
    }
    
    
    
    
   /* 
    // @GetMapping("/orders/{orderId}")
    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public Order getAllOrders() throws Exception
    {
			Order orderDB = new Order();
		    	
		   // orderDB = orderRepository.findById(orderId).get();
		    
		    return orderDB;
		        
    }
    
    
    
   // @GetMapping("/orders/{orderId}")
    @RequestMapping(path = "/orders/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable Long orderId) throws Exception
    {
			Order orderDB = new Order();
		    	
		    orderDB = orderRepository.findById(orderId).get();
		    
		    return orderDB;
		        
    }
    */
    
    /*
  
    
    
   // @PatchMapping("/orders/{orderId}")
    @RequestMapping(path = "/orders/{orderId}", method = RequestMethod.PATCH)
    public Order updateOrder(@PathVariable Long orderId,@RequestBody OrderForm orderForm){
    	
    	Order orderDB = new Order();
    	
        orderDB = orderRepository.findById(orderId).get();

	        	
	    Payment paymentOforderDB = orderDB.getPayment();
	            
	    paymentOforderDB.setId(orderForm.getPayment().getId());
	           
	   

        return orderRepository.save(orderDB);
      
    }

*/
/*
 @PostMapping("/orders")
    //@RequestMapping(path = "/orders", method = RequestMethod.POST)
    public Order saveOrder(@RequestBody OrderForm orderForm){
        Client client=new Client();
        client.setName(orderForm.getClient().getName());
        client.setEmail(orderForm.getClient().getEmail());
        client.setAddress(orderForm.getClient().getAddress());
        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        client.setUsername(orderForm.getClient().getUsername());
        client=clientRepository.save(client);
        System.out.println(client.getId());

        Order order=new Order();
        order.setClient(client);
        order.setDate(new Date());
        order=orderRepository.save(order);
        double total=0;
        for(OrderProduct p:orderForm.getProducts()){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            Product product=productRepository.findById(p.getId()).get();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getCurrentPrice());
            orderItem.setQuantity(p.getQuantity());
            orderItemRepository.save(orderItem);
            total+=p.getQuantity()*product.getCurrentPrice();
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
 */

}
