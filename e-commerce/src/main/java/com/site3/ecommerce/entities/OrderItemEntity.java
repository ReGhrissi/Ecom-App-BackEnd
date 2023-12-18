package com.site3.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.site3.ecommerce.dto.OrderDto;
import com.site3.ecommerce.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity(name="orderItems")
public class OrderItemEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=30, nullable=false)
	private String orderItemId;
	
    private int quantity;
    private double price;
    
	@OneToOne
	@JoinColumn(name="product_id") // ????
    private ProductEntity product;
    
	@ManyToOne
	@JoinColumn(name="orders_id")
    private OrderEntity order;

}
