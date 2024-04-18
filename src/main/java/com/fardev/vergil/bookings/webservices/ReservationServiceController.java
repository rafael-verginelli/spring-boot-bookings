package com.fardev.vergil.bookings.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fardev.vergil.bookings.domain.VehicleReservation;
import com.fardev.vergil.bookings.services.ReservationServices;

@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {
	
	@Autowired
	private ReservationServices reservationServices;
	
	@RequestMapping(value = "/reservations/{date}", method = RequestMethod.GET)
	public List<VehicleReservation> getAllReservationsForDate(@PathVariable(value = "date") String dateString) {
		return this.reservationServices.getVehicleReservationForDate(dateString);
	}
}
