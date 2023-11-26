package com.site3.ecommerce.responses;

import java.util.Date;



import lombok.Data;

@Data
public class PaymentResponse {

	private String paymentId;
    private Date datePayment;
    private long cardNumber;
    private String cardType;
    
    private OrderResponse order;
}
