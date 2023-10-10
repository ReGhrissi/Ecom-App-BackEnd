package com.site3.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems;
    @ManyToOne
    private Client client;
    private double totalAmount;
    
    @OneToOne(mappedBy = "order")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Payment payment;

}
