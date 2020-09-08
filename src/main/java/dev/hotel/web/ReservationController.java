package dev.hotel.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.repository.ReservationRepository;

@RestController
public class ReservationController {

	private ReservationRepository resR;

	/**
	 * @param resR
	 */
	public ReservationController(ReservationRepository resR) {
		this.resR = resR;
	}

	@RequestMapping(method = RequestMethod.POST, path = "reservations")
	public void reservations() {

	}

}
