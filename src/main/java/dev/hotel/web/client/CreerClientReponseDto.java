package dev.hotel.web.client;

import java.util.UUID;

import dev.hotel.entite.Client;

public class CreerClientReponseDto extends CreerClientRequestDto {

	private UUID uuid;

	public CreerClientReponseDto(Client client) {
		this.uuid = client.getUuid();
		this.setNom(client.getNom());
		this.setPrenoms(client.getPrenoms());
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
