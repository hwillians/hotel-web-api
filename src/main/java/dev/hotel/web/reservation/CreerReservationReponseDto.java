package dev.hotel.web.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;

public class CreerReservationReponseDto extends CreerReservationRequestDto {

	private UUID uuid;

	public CreerReservationReponseDto(Reservation res) {

		List<UUID> chambres = new ArrayList<>();
		for (Chambre ch : res.getChambres()) {
			chambres.add(ch.getUuid());
		}

		this.uuid = res.getUuid();
		this.setDateDebut(res.getDateDebut());
		this.setDateFin(res.getDateFin());
		this.setChambres(chambres);
		this.setClientId(res.getClient().getUuid());
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
