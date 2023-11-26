package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.OrderDto;
import com.site3.ecommerce.requests.OrderRequest;


public interface OrderService {

	OrderDto createOrder(OrderDto order, OrderRequest orderRequest, String email);
	
	OrderDto getOrderByOrderId(String orderId);
	
	OrderDto updateOrder(String id, OrderDto orderDto);
	
	void deleteOrder(String orderId);
	
	//List<OrderDto> getOrders(int page, int limit, String search, int status);
	
	List<OrderDto> getOrders();
	
	List<OrderDto> getOrdersByUser(String UserId);
	
	List<OrderDto> getAllRegistereds(); 
	
	List<OrderDto> getAllUnderTreatements();
	
	List<OrderDto> getAllCancelleds();
	
	List<OrderDto> getAllSents();
	
	List<OrderDto> getAllDelivereds();
	
	List<OrderDto> getAllReturneds();
}
