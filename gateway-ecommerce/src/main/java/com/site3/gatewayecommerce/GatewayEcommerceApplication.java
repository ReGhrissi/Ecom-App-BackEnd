package com.site3.gatewayecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.netflix.discovery.DiscoveryClient;
import com.site3.gatewayecommerce.config.CorsConfig;


@CrossOrigin("*")
@SpringBootApplication
public class GatewayEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayEcommerceApplication.class, args);
	}
	
	/* c'est une config statique
	@Bean
	RouteLocator routrLocator(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route((r)->r.path("/categories/**").uri("lb://E-COMMERCE"))
				.route((r)->r.path("/products/**").uri("lb://E-COMMERCE"))
				.route((r)->r.path("/orders/**").uri("lb://CLIENTS-ORDERS"))
				.route((r)->r.path("/orderItems/**").uri("lb://CLIENTS-ORDERS"))
				.route((r)->r.path("/clients/**").uri("lb://CLIENTS-ORDERS"))
				.build();
	}
	*/
	
	// confi dynamique qui permet de recuperer le nom du micros service d'apres l'url
	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(
			ReactiveDiscoveryClient rdc,
			DiscoveryLocatorProperties properties)
	{
		return new DiscoveryClientRouteDefinitionLocator  (rdc,properties);
	}
	
	

}
