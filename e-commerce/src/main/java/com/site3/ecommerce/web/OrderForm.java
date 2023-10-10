package com.site3.ecommerce.web;

import lombok.Data;
import com.site3.ecommerce.entities.Client;
import com.site3.ecommerce.entities.Payment;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
    private Client client=new Client();
    private List<OrderProduct> products=new ArrayList<>();
    private Payment payment = new Payment();
}
@Data
class OrderProduct{
    private Long id;
    private int quantity;

}
