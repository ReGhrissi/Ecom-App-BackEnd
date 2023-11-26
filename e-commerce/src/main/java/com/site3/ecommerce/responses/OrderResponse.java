package com.site3.ecommerce.responses;

import java.util.Date;
import java.util.List;

import com.site3.ecommerce.enums.OrderStatus;
import com.site3.ecommerce.requests.ClientRequest;
import com.site3.ecommerce.requests.OrderItemRequest;
import com.site3.ecommerce.requests.PaymentRequest;

import lombok.Data;


@Data
public class OrderResponse {

	private String orderId;
    private Date orderDate;
    private double totalAmount;
    
    private OrderStatus orderStatus;
    private String reasonOfStatus;
    
    private List<OrderItemResponse> orderItems;
    
    private ClientResponse client;
    
    private PaymentResponse payment;
}
