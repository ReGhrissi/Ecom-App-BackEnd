package com.site3.ecommerce.requests;



import lombok.Data;

@Data
public class OrderItemRequest {
	
	   	private int quantity;
	    private double price;
	    
	    private long id;
	  //  private ProductRequest product;
	    
	    private OrderRequest order;

}
