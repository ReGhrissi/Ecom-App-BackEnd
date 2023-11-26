package com.site3.ecommerce.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.ClientRepository;
import com.site3.ecommerce.dao.OrderItemRepository;
import com.site3.ecommerce.dao.OrderRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dao.UserRepository;
import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ClientDto;
import com.site3.ecommerce.dto.ContactDto;
import com.site3.ecommerce.dto.OrderDto;
import com.site3.ecommerce.dto.OrderItemDto;
import com.site3.ecommerce.dto.PaymentCardDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.dto.UserDto;
import com.site3.ecommerce.entities.CategoryEntity;
import com.site3.ecommerce.entities.ClientEntity;
import com.site3.ecommerce.entities.ContactEntity;
import com.site3.ecommerce.entities.OrderEntity;
import com.site3.ecommerce.entities.OrderItemEntity;
import com.site3.ecommerce.entities.PaymentCardEntity;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.entities.UserEntity;
import com.site3.ecommerce.enums.OrderStatus;
import com.site3.ecommerce.requests.OrderItemRequest;
import com.site3.ecommerce.requests.OrderRequest;
import com.site3.ecommerce.requests.ProductRequest;
import com.site3.ecommerce.services.OrderService;
import com.site3.ecommerce.shared.Utils;
import com.site3.ecommerce.web.OrderForm;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	Utils util;
	
	
	OrderStatus orderStatus;

//------------------------------------------------------------------------------------------
    private double calculateTotalAmount(List<OrderItemRequest> orderItems) {
        double total = 0.0;
        for (OrderItemRequest orderItem : orderItems) {
            total += orderItem.getQuantity() * orderItem.getPrice();
        }
        return total;
    }
//-----------------------------------------------------------------------------------------
	@Override
	public OrderDto createOrder(OrderDto order, OrderRequest orderRequest, String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		
		OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
		
		
		 	ClientEntity clientEntity= orderEntity.getClient();
	        
			clientEntity.setClinetId(util.generateStringId(30));
			clientEntity.setUsername(userEntity.getUserId());
	       
	        
	        ClientEntity clientSaved =clientRepository.save(clientEntity);
	        orderEntity.setClient(clientSaved);
	        

	        
	        List<OrderItemEntity> orderItems = new ArrayList<>();
	        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItems()) {
	        	
	            OrderItemEntity orderItemEntity = new OrderItemEntity();
	            orderItemEntity.setOrderItemId(util.generateStringId(30));
	            orderItemEntity.setQuantity(orderItemRequest.getQuantity());
	            orderItemEntity.setPrice(orderItemRequest.getPrice());
	        	
	            ProductEntity productEntity = productRepository.findById(orderItemRequest.getId());
	            if (productEntity == null) {
	            	throw new RuntimeException("Product not Exists !");
	            }
	            
	            orderItemEntity.setProduct(productEntity);
	            orderItemEntity.setOrder(orderEntity);

	            orderItems.add(orderItemEntity);
	        }

	        orderEntity.setOrderItems(orderItems);
	        
	        orderEntity.setOrderId(util.generateOrderId());
	        orderEntity.setOrderDate(new Date());
	        orderEntity.setTotalAmount(calculateTotalAmount(orderRequest.getOrderItems()));
	        orderEntity.setOrderStatus(OrderStatus.REGISTERED);

	        
	        OrderEntity savedOrder = orderRepository.save(orderEntity);
	        
	              
			OrderDto saveOrderDto = modelMapper.map(savedOrder, OrderDto.class);

			return saveOrderDto;

	
	}
	

//------------------------------------------------------------------------------------------
	@Override
	public OrderDto getOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}
//---------------------------------------------------------------------------------------------
	@Override
	public OrderDto updateOrder(String id, OrderDto orderDto) {
		
		OrderEntity orderEntity = orderRepository.findByOrderId(id);
		
	    if (orderEntity == null) 
	    {
	        throw new RuntimeException("Order not found");
	    }

	    
	    // Mettez à jour les propriétés de l'entité 
	    orderEntity.setOrderStatus(orderDto.getOrderStatus());
	    orderEntity.setReasonOfStatus(orderDto.getReasonOfStatus());


	    OrderEntity orderUpdated = orderRepository.save(orderEntity);
		
		ModelMapper modelMapper = new ModelMapper();	
	
		OrderDto order = new OrderDto();

		order = modelMapper.map(orderUpdated, OrderDto.class);
		
		return order;
	}

//--------------------------------------------------------------------------------------------
	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub

	}
	
//---------------------------------------------------------------------------------------------
	@Override
	public List<OrderDto> getOrders() {
			
			List<OrderDto> ordersDto = new ArrayList<>();
			
			List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findAll();
			
			for(OrderEntity orderEntity: orders) {
				
				ModelMapper modelMapper = new ModelMapper();	
				
				OrderDto order = modelMapper.map(orderEntity, OrderDto.class);
				
				ordersDto.add(order);
			}
			
			return ordersDto;
		
	}
//--------------------------------------------------------------------------------------------------
	
	@Override
	public List<OrderDto> getOrdersByUser(String userId) {
		
        List<OrderDto> ordersDto = new ArrayList<>();
        
        // Trouver les clients en utilisant l'identifiant (userId)
        List<ClientEntity> clients =  (List<ClientEntity>) clientRepository.findByUsername(userId);
        
        if (clients != null) 
        {
			        for (ClientEntity client : clients) {
			        	
			            List<OrderEntity> orders = orderRepository.findByClient(client);
			
			            ModelMapper modelMapper = new ModelMapper();
			            
			            for (OrderEntity orderEntity : orders) 
			            {
			                OrderDto order = modelMapper.map(orderEntity, OrderDto.class);
			                ordersDto.add(order);
			            }
			        }
        }
        /*
        if (clients != null) {
            // Récupérer toutes les commandes associées à cet utilisateur
            List<OrderEntity> orders = orderRepository.findByClient(client);
            
            // Mapper les entités des commandes en DTOs
            ModelMapper modelMapper = new ModelMapper();
            
            for (OrderEntity orderEntity : orders) 
            {
                OrderDto order = modelMapper.map(orderEntity, OrderDto.class);
                ordersDto.add(order);
            }
        }
        */
        return ordersDto;
    }

	
//----------------------------------------------------------------------------------------------------	
	@Override
	public List<OrderDto> getAllRegistereds() {
		
		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.REGISTERED);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}
	
	
	@Override
	public List<OrderDto> getAllUnderTreatements() {
		
		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.UNDER_TREATEMENT);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}

	@Override
	public List<OrderDto> getAllCancelleds() {
		
		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.CANCELLED);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}
	
	
	@Override
	public List<OrderDto> getAllSents() {
		
		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.SENT);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}
	
	
	@Override
	public List<OrderDto> getAllDelivereds() {

		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.DELIVERED);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}
	
	
	@Override
	public List<OrderDto> getAllReturneds() {

		List<OrderEntity> orders = (List<OrderEntity>) orderRepository.findByOrderStatus(orderStatus.RETURNED);
		
		Type listType = new TypeToken<List<OrderDto>>() {}.getType();
		
		List<OrderDto> ordersDto = new ModelMapper().map(orders, listType);
		
		return ordersDto;
	}

}
