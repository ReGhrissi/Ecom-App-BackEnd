
package com.site3.ecommerce.web;

import com.site3.ecommerce.dao.*;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.OrderDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.*;
import com.site3.ecommerce.exceptions.UserException;
import com.site3.ecommerce.requests.OrderRequest;
import com.site3.ecommerce.requests.UserRequest;
import com.site3.ecommerce.responses.CategoryResponse;
import com.site3.ecommerce.responses.ErrorMessages;
import com.site3.ecommerce.responses.OrderResponse;
import com.site3.ecommerce.responses.ProductResponse;
import com.site3.ecommerce.responses.UserResponse;
import com.site3.ecommerce.services.OrderService;
import com.site3.ecommerce.services.UserService;

import lombok.Data;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    
    @PostMapping()
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest, Principal principal) throws Exception {
		
	//	if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = modelMapper.map(orderRequest, OrderDto.class);
		
		OrderDto createOrder = orderService.createOrder(orderDto, orderRequest, principal.getName());
		
		OrderResponse orderResponse =  modelMapper.map(createOrder, OrderResponse.class);
		
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
		
		
	}
    
 //-------------------------------------------------------------------------------------------------   
	@PutMapping(path="/{id}")
	public ResponseEntity<OrderResponse> updateUser(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
		
		OrderDto orderDto = new OrderDto();

		ModelMapper modelMapper = new ModelMapper();
		
		orderDto = modelMapper.map(orderRequest, OrderDto.class);
		
		OrderDto updateOrder = orderService.updateOrder(id, orderDto);
		
		OrderResponse orderResponse = new OrderResponse();
		
		orderResponse = modelMapper.map(updateOrder, OrderResponse.class);
		
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.ACCEPTED);
	}
	
 //--------------------------------------------------------------------------------------------------------  
	@GetMapping()
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		
		List<OrderResponse> ordersResponse = new ArrayList<>();
		
		List<OrderDto> orders = orderService.getOrders();
		
		for(OrderDto orderDto: orders) {
			
			ModelMapper modelMapper = new ModelMapper();
			OrderResponse orderResponse =  modelMapper.map(orderDto, OrderResponse.class);
			
			ordersResponse.add(orderResponse);
		}
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
	}
	
//-----------------------------------------------------------------------------------------------------------
	@GetMapping(path="/{userId}")
	public ResponseEntity<List<OrderResponse>> getOrdersByUser(@PathVariable String userId) {
		
		List<OrderResponse> ordersResponse = new ArrayList<>();
		
		List<OrderDto> orders = orderService.getOrdersByUser(userId);
		
		for(OrderDto orderDto: orders) {
			
			ModelMapper modelMapper = new ModelMapper();
			OrderResponse orderResponse =  modelMapper.map(orderDto, OrderResponse.class);
			
			ordersResponse.add(orderResponse);
		}
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
	}
//-------------------------------------------------------------------------------------------------------------
	@GetMapping(path="/registeredOrders")
	public ResponseEntity<List<OrderResponse>>getENREGISTREEs() {
		
		List<OrderDto> orders = orderService.getAllRegistereds();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		 
	}
	
	@GetMapping(path="/underTreatementOrders")
	public ResponseEntity<List<OrderResponse>>getUNDER_TREATEMENTs() {
		
		List<OrderDto> orders = orderService.getAllUnderTreatements();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(path="/cancelledOrders")
	public ResponseEntity<List<OrderResponse>>getCANCELLEDs() {
		
		List<OrderDto> orders = orderService.getAllCancelleds();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(path="/sentOrders")
	public ResponseEntity<List<OrderResponse>>getSENTs() {
		
		List<OrderDto> orders = orderService.getAllSents();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(path="/deliveredOrders")
	public ResponseEntity<List<OrderResponse>>getDELIVEREds() {
		
		List<OrderDto> orders = orderService.getAllDelivereds();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(path="/returnedOrders")
	public ResponseEntity<List<OrderResponse>>getRETURNEDs() {
		
		List<OrderDto> orders = orderService.getAllReturneds();
		
		Type listType = new TypeToken<List<OrderResponse>>() {}.getType();
		List<OrderResponse> ordersResponse = new ModelMapper().map(orders, listType);
		
		return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
		
	}

 /*   
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
       */
        	   /*     
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
        	        */
    /*
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
    
    
    */
    
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



