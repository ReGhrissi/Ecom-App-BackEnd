package com.site3.ecommerce.enums;

import org.springframework.stereotype.Component;


public enum OrderStatus {
	
	REGISTERED,
    PAID,
    UNDER_TREATEMENT,
    CANCELLED,
    SENT,
    DELIVERED,
    RETURNED;
}
