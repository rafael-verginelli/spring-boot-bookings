package com.fardev.vergil.bookings.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fardev.vergil.bookings.data.entity.Reservation;
import com.fardev.vergil.bookings.repository.ReservationRepository;

@RestController
public class ResController {

	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping(value = "/ress", method = RequestMethod.GET)
	List<Reservation> findAll() {
		List<Reservation> reservationsList = new ArrayList<Reservation>();

		Iterable<Reservation> reservationsIterableList = reservationRepository.findAll();
		reservationsIterableList.forEach(r -> {
			reservationsList.add(r);
		});
		return reservationsList;
	}
}
