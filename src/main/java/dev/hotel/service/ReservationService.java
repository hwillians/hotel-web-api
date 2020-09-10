package dev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.HotelException;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository resRep;
	private ClientService cServ;
	private ChambreService chambreService;

	/**
	 * @param resRep
	 * @param cServ
	 * @param chambreService
	 */
	public ReservationService(ReservationRepository resRep, ClientService cServ, ChambreService chambreService) {
		this.resRep = resRep;
		this.cServ = cServ;
		this.chambreService = chambreService;
	}

	@Transactional
	public Reservation creerReservation(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambres) {

		List<String> messagesErreurs = new ArrayList<>();

		Optional<Client> opClient = cServ.recupererClient(clientId);

		if (opClient.isEmpty()) {
			messagesErreurs.add("L'uuid " + clientId + " ne correspond à aucun client");
		}

		List<Chambre> listChambres = new ArrayList<>();

		for (UUID uuidChambre : chambres) {
			Optional<Chambre> opChambre = chambreService.recupererChambre(uuidChambre);
			if (opChambre.isPresent()) {
				listChambres.add(opChambre.get());
			} else {
				messagesErreurs.add("L'uuid " + uuidChambre + " ne correspond à aucune chambre");
			}

		}

		if (!messagesErreurs.isEmpty()) {
			throw new HotelException(messagesErreurs);
		}

		Reservation res = new Reservation();
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res.setClient(opClient.get());
		res.setChambres(listChambres);

		return resRep.save(res);
	}

}
