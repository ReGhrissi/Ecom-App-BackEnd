package com.site3.ecommerce.dto;

import java.io.Serializable;


import lombok.Data;

@Data
public class OrderItemDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String orderItemId;
	
    private int quantity;
    private double price;
    
    private ProductDto product;
    
    private OrderDto order;

    
}
