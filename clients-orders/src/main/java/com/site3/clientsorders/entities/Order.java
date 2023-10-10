package com.site3.clientsorders.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.site3.clientsorders.service.model.Payment;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="orders")
@Data @AllArgsConstructor @NoArgsConstructor  @ToString
public class Order {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    
    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;
    
    @ManyToOne
    private Client client;
    private double totalAmount;
    
   // @OneToOne
    //private Payment payment;
    
    private Long payment_ID;
    
    @Transient
    private Payment payment;


}
