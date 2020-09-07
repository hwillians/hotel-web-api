package dev.hotel.web;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
public class ClientController {

	private ClientRepository cr;

	public ClientController(ClientRepository cr) {
		this.cr = cr;
	}

	// GET /query?prenom=Ross&nom=Odd
	@RequestMapping(method = RequestMethod.GET, path = "clients")
	// GET /hello
	public List<Client> clients(@RequestParam Integer start, @RequestParam Integer size) {

		List<Client> clients = cr.findAll(PageRequest.of(start, size)).getContent();

		return clients;

	}

}
