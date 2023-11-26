package com.site3.ecommerce.web;

import lombok.Data;
import com.site3.ecommerce.entities.Client;
import com.site3.ecommerce.entities.ClientEntity;
import com.site3.ecommerce.entities.Payment;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
    private ClientEntity clientEntity=new ClientEntity();
    private List<OrderProduct> products=new ArrayList<>();
    private Payment payment = new Payment();
}

@Data
class OrderProduct{
    private long id;
    private int quantity;

}

