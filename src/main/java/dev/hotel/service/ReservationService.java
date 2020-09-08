package dev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
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

	public Reservation creerReservation(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambres) {

		Optional<Client> opClient = cServ.recupererClient(clientId);

		Client client = opClient.get();

		List<Chambre> listChambres = new ArrayList<>();

		for (int i = 0; i < chambres.size(); i++) {
			Chambre chambre = chambreService.recupererChambre(chambres.get(i)).get();
			listChambres.add(chambre);
		}

		Reservation res = new Reservation();
		res.setDateDebut(dateDebut);
		res.setDateFin(dateFin);
		res.setClient(client);
		res.setChambres(listChambres);

		return resRep.save(res);
	}

}
