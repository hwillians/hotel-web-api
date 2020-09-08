package dev.hotel.web.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private ClientService cServ;

	public ClientController(ClientService cServ) {
		this.cServ = cServ;
	}

	@GetMapping
	public List<Client> ListClients(@RequestParam Integer start, @RequestParam Integer size) {
		return cServ.listerClients(start, size);

	}

	@GetMapping("{uuid}")
	public ResponseEntity<?> clientsUUID(@PathVariable UUID uuid) {

		Optional<Client> c = cServ.recupererClient(uuid);

		if (c.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).header("message", "Client Trouvé").body(c.get());

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("L’UUID ne correspond pas à un uuid de client en base de données !");

		}

	}

	@PostMapping
	public ResponseEntity<?> newClient(@Valid @RequestBody CreerClientRequestDto client, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Client clientServ = cServ.creerClient(client.getNom(), client.getPrenoms());
			CreerClientReponseDto clientRep = new CreerClientReponseDto(clientServ);
			return ResponseEntity.ok(clientRep);
		} else {
			return ResponseEntity.badRequest().body("Le nom et le prénom doivent avoir plus de deux caractères !");
		}

	}

}