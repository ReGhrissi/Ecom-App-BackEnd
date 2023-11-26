package com.site3.ecommerce.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity(name="payments")
public class PaymentEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
  	private long id;
	
	@NotBlank
	@Column(length = 30)
   	private String paymentId;
   	
    private Date datePayment;
    private long cardNumber;
    private String cardType;
    
	@OneToOne
	@JoinColumn(name="orders_id")
    private OrderEntity order;

}
