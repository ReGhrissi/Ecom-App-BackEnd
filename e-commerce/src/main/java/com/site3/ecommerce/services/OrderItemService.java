package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.OrderItemDto;



public interface OrderItemService {
	
	OrderItemDto createOrder(OrderItemDto orderItem);
	
	OrderItemDto getOrderItemByOrderItemId(String orderItemtId);
	
	long getTotalProductsCountById(String productId);
	
	OrderItemDto updateOrderItem(String id, OrderItemDto orderItemDto);
	
	void deleteClient(String clientId);
	
	//List<OrderItemDto> getOrderItems(int page, int limit, String search, int status);
	
	List<OrderItemDto> getOrderItems();


}
