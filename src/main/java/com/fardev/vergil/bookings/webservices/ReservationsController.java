package com.fardev.vergil.bookings.webservices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fardev.vergil.bookings.domain.VehicleReservation;
import com.fardev.vergil.bookings.services.ReservationServices;

@Controller
@RequestMapping("/reservations")
public class ReservationsController {
	
	@Autowired
	private ReservationServices reservationServices;

	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model) {
		
		List<VehicleReservation> vehicleReservationList = this.reservationServices.getVehicleReservationForDate(dateString);
		model.addAttribute("vehicleReservations", vehicleReservationList);
		
		return "reservations";
	}

}
