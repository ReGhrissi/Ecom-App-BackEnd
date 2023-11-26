package com.site3.ecommerce.responses;

import com.site3.ecommerce.requests.OrderRequest;
import com.site3.ecommerce.requests.ProductRequest;

import lombok.Data;


@Data
public class OrderItemResponse {
	
	private String orderItemId;
  	private int quantity;
    private double price;
    
    private ProductResponse product;
    
   

}
