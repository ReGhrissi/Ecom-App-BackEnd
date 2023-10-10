package com.site3.clientsorders.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.site3.clientsorders.service.model.Payment;
import com.site3.clientsorders.service.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @ToString

public class OrderItem {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
    
    // @ManyToOne
    //  private Product product;
      
    private Long product_ID;
    @Transient
    private Product product;
}
