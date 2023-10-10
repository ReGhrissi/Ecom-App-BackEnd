package com.site3.clientsorders.web;

import lombok.Data;
import com.site3.clientsorders.entities.Client;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
	
    private Client client=new Client();
    private List<OrderProduct> products=new ArrayList<>();
}

