package dev.hotel.web.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreerReservationRequestDto {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private UUID clientId;

	private List<UUID> chambres;

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the clientId
	 */

	public UUID getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the chambres
	 */
	public List<UUID> getChambres() {
		return chambres;
	}

	/**
	 * @param chambres the chambres to set
	 */
	public void setChambres(List<UUID> chambres) {
		this.chambres = chambres;
	}

}
