package com.site3.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.site3.ecommerce.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private long id;
	private String orderId;
	
    private Date orderDate;
    private double totalAmount;
    
    private OrderStatus orderStatus;
    private String reasonOfStatus;
    
    private List<OrderItemDto> orderItems;
    private ClientDto client;
    private PaymentDto payment;
}
