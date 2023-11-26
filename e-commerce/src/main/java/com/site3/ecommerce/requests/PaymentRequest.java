package com.site3.ecommerce.requests;

import java.util.Date;


import lombok.Data;

@Data
public class PaymentRequest {

    private Date datePayment;
    private long cardNumber;
    private String cardType;
    
    private OrderRequest order;
}
