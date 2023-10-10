package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class Order {
	
	 	private Long id;
	 	private Date date;
	 	private double totalAmount;


}

