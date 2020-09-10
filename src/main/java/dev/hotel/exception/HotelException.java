package dev.hotel.exception;

import java.util.List;

public class HotelException extends RuntimeException {

	private List<String> messagesErreurs;

	/**
	 * @param messagesErreurs
	 */
	public HotelException(List<String> messagesErreurs) {
		this.messagesErreurs = messagesErreurs;
	}

	/**
	 * @return the messagesErreurs
	 */
	public List<String> getMessagesErreurs() {
		return messagesErreurs;
	}

	/**
	 * @param messagesErreurs the messagesErreurs to set
	 */
	public void setMessagesErreurs(List<String> messagesErreurs) {
		this.messagesErreurs = messagesErreurs;
	}

}
