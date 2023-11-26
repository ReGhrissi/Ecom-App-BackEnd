package com.site3.ecommerce.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.site3.ecommerce.dao.PayementRepository;
import com.site3.ecommerce.entities.Order;
import com.site3.ecommerce.entities.Payment;

@CrossOrigin("*")
@RestController
public class PaymentController {
	
	@Autowired
	private PayementRepository payementRepository;
	
	
/*	
	@PostMapping("/payments")
    public Payment savePayment(@RequestBody PaymentForm paymentForm)
    {
		Payment payment = new Payment();
		
		payment.setCardNumber(paymentForm.getCardNumber());
		payment.setCardType(paymentForm.getCardType());
		payment.setDatePayment(new Date());
		
		return payementRepository.save(payment);
		
    }
	
	@RequestMapping(path = "/payments/{paymentId}", method = { RequestMethod.GET, RequestMethod.PATCH })
    public ResponseEntity<?> handleeOrdersRequest_2(HttpServletRequest request , @PathVariable Long paymentId, @RequestBody(required = false) PaymentForm paymentForm) 
    {
 */
		/*
        if (request.getMethod().equals("GET")) 
        {
        		Order orderGET = new Order();
	    	
        		orderGET = orderRepository.findById(orderId).get();
 		    
 		   return ResponseEntity.ok(orderGET);
        } 
        */
  /*      
        if (request.getMethod().equals("PATCH")) 
        {
        	Payment paymentPATCH = new Payment();
        	
        	paymentPATCH = payementRepository.findById(paymentId).get();
            
            System.out.println("PAYMENT a modifier :"+ paymentPATCH);
    	        	
    	    Order orderOfpaymentPATCH = paymentForm.getOrder();
    	            
    	   // orderOfpaymentPATCH.setId(.getPayment().getId());
    	           
    	    paymentPATCH.setOrder(orderOfpaymentPATCH);
    	    
    	    paymentPATCH = payementRepository.save(paymentPATCH);

    	   return ResponseEntity.ok(paymentPATCH);
        }
    	else 
    	{
    			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    	}
    }
    
*/	

}
