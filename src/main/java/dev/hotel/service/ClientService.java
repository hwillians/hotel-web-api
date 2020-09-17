package dev.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository cr;

	public ClientService(ClientRepository cr) {
		this.cr = cr;
	}

	public List<Client> listerClients(Integer start, Integer size) {

		return cr.findAll(PageRequest.of(start, size)).getContent();
	}

	public Optional<Client> recupererClient(UUID uuid) {

		return cr.findById(uuid);
	}

	@Transactional
	public Client creerClient(String nom, String prenoms) {

		return cr.save(new Client(nom, prenoms));

	}

	public List<Client> listerAllClients() {

		return cr.findAll();
	}

}
