package com.site3.clientsorders;

import java.util.Random;

import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import com.site3.clientsorders.dao.ClientRepository;
import com.site3.clientsorders.dao.OrderItemRepository;
import com.site3.clientsorders.dao.OrderRepository;
import com.site3.clientsorders.entities.Client;
import com.site3.clientsorders.entities.Order;
import com.site3.clientsorders.entities.OrderItem;
import com.site3.clientsorders.service.feign.*;
import com.site3.clientsorders.service.model.Payment;
import com.site3.clientsorders.service.model.Product;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
@EnableFeignClients
public class ClientsOrdersApplication implements CommandLineRunner{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	@Autowired
	private PaymentRestClient paymentRestClient;

	@Autowired
	private ProductRestClient productRestClient;

	public static void main(String[] args) {
		SpringApplication.run(ClientsOrdersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(Order.class,OrderItem.class, Client.class);
		
		orderRepository.save(new Order());
		
		Random rnd = new Random();
		orderRepository.findAll().forEach(
				o ->{
						for(int i=0; i<5 ; i++)
						{
							OrderItem oI = new OrderItem();
							
							oI.setOrder(o);
							oI.setPrice(10+rnd.nextInt(200));
							oI.setQuantity(10+rnd.nextInt(200));
							orderItemRepository.save(oI);
							
						}
					});
		
		Payment payment = paymentRestClient.getPaymentById(1L);
		System.out.println("--------------------------");
		System.out.println(payment.getId());
		System.out.println(payment.getCardNumber());
		System.out.println(payment.getCardType());
		System.out.println("--------------------------");
		
		Product product = productRestClient.getProductById(1L);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct_ID(product.getId());
		orderItem.setPrice(product.getCurrentPrice());
		orderItem.setQuantity(15);
		Order order2 = new Order();
		orderRepository.save(order2);
		orderItem.setOrder(order2);
		orderItemRepository.save(orderItem);
		
		PagedModel<Product> productPageModel = productRestClient.getPagetProducts(1,15);
		
		Order order3 = new Order();
		orderRepository.save(order3);
		
		productPageModel.forEach(
				p-> {
						OrderItem orderItem2 = new OrderItem();
						orderItem2.setOrder(order3);
						orderItem2.setPrice(p.getCurrentPrice());
						orderItem2.setProduct_ID(p.getId());
						orderItem2.setQuantity(1+new Random().nextInt(100));
						orderItemRepository.save(orderItem2);			
					
				});
		
		
		
	}
}
