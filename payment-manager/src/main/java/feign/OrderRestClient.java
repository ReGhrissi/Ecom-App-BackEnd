package feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import model.Order;

@FeignClient(name ="CLIENTS-ORDERS")
public interface OrderRestClient {
	
	@GetMapping(path="/orders/{id}")
	Order getOrderById(@PathVariable(name="id") Long id);

}
