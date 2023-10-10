package com.site3.clientsorders.service.controller;

import com.site3.clientsorders.dao.ClientRepository;
import com.site3.clientsorders.dao.OrderItemRepository;
import com.site3.clientsorders.dao.OrderRepository;
//import org.sid.lightecomv1.dao.ProductRepository;
import com.site3.clientsorders.entities.Client;
import com.site3.clientsorders.entities.Order;
import com.site3.clientsorders.entities.OrderItem;
import com.site3.clientsorders.service.feign.ProductRestClient;
import com.site3.clientsorders.service.model.Product;
import com.site3.clientsorders.web.OrderForm;
import com.site3.clientsorders.web.OrderProduct;

//import org.sid.lightecomv1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {
	
   // @Autowired
   // private ProductRepository productRepository;
	
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
	private ProductRestClient productRestClient;
    
    
    @PostMapping("/orders")
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
        
        for(OrderProduct p : orderForm.getProducts())
        {
        	
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            Product product = productRestClient.getProductById(p.getId());
            
          //  Product product=productRepository.findById(p.getId()).get();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getCurrentPrice());
            orderItem.setQuantity(p.getQuantity());
            orderItemRepository.save(orderItem);
            total+=p.getQuantity()*product.getCurrentPrice();
         
        }
        
        order.setTotalAmount(total);
        
        return orderRepository.save(order);
         
    }
     
}
