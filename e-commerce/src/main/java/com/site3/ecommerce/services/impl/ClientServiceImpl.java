package com.site3.ecommerce.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.site3.ecommerce.dto.ClientDto;
import com.site3.ecommerce.services.ClientService;


@Service
public class ClientServiceImpl implements ClientService {

	@Override
	public ClientDto createOrder(ClientDto client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDto getClientByClientId(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDto updateClient(String id, ClientDto clientDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(String clientId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClientDto> getClients() {
		// TODO Auto-generated method stub
		return null;
	}

}
