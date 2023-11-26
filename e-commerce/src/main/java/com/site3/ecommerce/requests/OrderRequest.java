package com.site3.ecommerce.requests;

import java.util.Date;
import java.util.List;


import com.site3.ecommerce.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderRequest {
	
   // private Date orderDate;
  //  private double totalAmount;
    
   private OrderStatus orderStatus;
    private String reasonOfStatus;
    
    private List<OrderItemRequest> orderItems;
    
    private ClientRequest client;
    
    private PaymentRequest payment;

}
