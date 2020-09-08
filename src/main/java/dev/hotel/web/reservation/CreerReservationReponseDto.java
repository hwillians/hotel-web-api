package dev.hotel.web.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.hotel.entite.Reservation;

public class CreerReservationReponseDto extends CreerReservationRequestDto {

	private UUID uuid;

	public CreerReservationReponseDto(Reservation res) {
		this.uuid = res.getUuid();
		this.setDateDebut(res.getDateDebut());
		this.setDateFin(res.getDateFin());

		List<UUID> chambres = new ArrayList<>();

		for (int i = 0; i < res.getChambres().size(); i++) {
			chambres.add(res.getChambres().get(i).getUuid());
		}
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
