package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.ClientDto;



public interface ClientService {
	
	ClientDto createOrder(ClientDto client);
	
	ClientDto getClientByClientId(String clientId);
	
	ClientDto updateClient(String id, ClientDto clientDto);
	
	void deleteClient(String clientId);
	
	//List<ClientDto> getClients(int page, int limit, String search, int status);
	
	List<ClientDto> getClients();

}
