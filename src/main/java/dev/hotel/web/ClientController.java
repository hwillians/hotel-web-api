package dev.hotel.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = RequestMethod.GET, path = "clients")
	public List<Client> ListClients(@RequestParam Integer start, @RequestParam Integer size) {

		List<Client> clients = cr.findAll(PageRequest.of(start, size)).getContent();

		return clients;

	}

	@RequestMapping(method = RequestMethod.GET, path = "client/{utilisateurId}")
	public ResponseEntity<?> clientsUUID(@PathVariable UUID utilisateurId) {

		Optional<Client> c = cr.findById(utilisateurId);

		if (c.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).header("message", "Client Trouvé").body(c.get());

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("L’UUID ne correspond pas à un uuid de client en base de données !");

		}

	}

	@RequestMapping(method = RequestMethod.POST, path = "newClient")
	public ResponseEntity<?> newClient(@Valid @RequestParam String nom, @Valid @RequestParam String prenoms) {
		Client c = new Client();
		c.setNom(nom);
		c.setPrenoms(prenoms);

		try {
			cr.save(c);
			return ResponseEntity.status(HttpStatus.OK).body(c);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Le nom et le prénom doivent avoir plus de deux caractères !");
		}

	}

}