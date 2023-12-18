package com.site3.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site3.ecommerce.dao.OrderItemRepository;
import com.site3.ecommerce.dao.ProductRepository;
import com.site3.ecommerce.dto.OrderItemDto;
import com.site3.ecommerce.entities.OrderItemEntity;
import com.site3.ecommerce.entities.ProductEntity;
import com.site3.ecommerce.services.OrderItemService;


@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public OrderItemDto createOrder(OrderItemDto orderItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto getOrderItemByOrderItemId(String orderItemtId) {
		// TODO Auto-generated method stub
		return null;
	}
//------------	

	@Override
	public long getTotalProductsCountById(String productId)
	{
		ProductEntity currentProduct = productRepository.findByProductId(productId);
		
		return orderItemRepository.countByProduct(currentProduct);
	}
	
//---------------
	@Override
	public OrderItemDto updateOrderItem(String id, OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(String clientId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderItemDto> getOrderItems() {
		// TODO Auto-generated method stub
		return null;
	}



}
