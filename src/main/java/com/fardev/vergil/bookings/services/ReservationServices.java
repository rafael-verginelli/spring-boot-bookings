package com.fardev.vergil.bookings.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fardev.vergil.bookings.data.entity.Customer;
import com.fardev.vergil.bookings.data.entity.Reservation;
import com.fardev.vergil.bookings.data.entity.Vehicle;
import com.fardev.vergil.bookings.domain.VehicleReservation;
import com.fardev.vergil.bookings.repository.CustomerRepository;
import com.fardev.vergil.bookings.repository.ReservationRepository;
import com.fardev.vergil.bookings.repository.VehicleRepository;

@Service
public class ReservationServices {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private VehicleRepository vehicleRepository;
	private CustomerRepository customerRepository;
	private ReservationRepository reservationRepository;

	@Autowired
	public ReservationServices(VehicleRepository vehicleRepository, CustomerRepository customerRepository,
			ReservationRepository reservationRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.customerRepository = customerRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<VehicleReservation> getVehicleReservationForDate(String dateString) {
		Date date = getDateFromDateString(dateString);
		Iterable<Vehicle> vehicles = vehicleRepository.findAll();
		Map<Long, VehicleReservation> vehicleReservationMap = new HashMap<Long, VehicleReservation>();
		vehicles.forEach(v -> {
			VehicleReservation vehicleReservation = new VehicleReservation();
			vehicleReservation.setVehicleId(v.getVehicleId());
			vehicleReservation.setVehicleModel(v.getVehicleModel());
			vehicleReservation.setVehicleType(v.getVehicleType());
			vehicleReservationMap.put(v.getVehicleId(), vehicleReservation);
		});

		var res = reservationRepository.findByReservationDate(date);//reservationRepository.findAll();

		Iterable<Reservation> reservations = (Iterable<Reservation>) res;// reservationRepository.findByReservationDate(date);
		if (reservations != null) {
			System.out.println("Reservations are not null");
			reservations.forEach(r -> {
				Optional<Customer> customer = customerRepository.findById(r.getCustomerId());
				if (customer.isPresent()) {
					VehicleReservation vehicleReservation = vehicleReservationMap.get(r.getVehicleId());
					vehicleReservation.setCustomerId(customer.get().getCustomerId());
					vehicleReservation.setCustomerFirstName(customer.get().getFirstName());
					vehicleReservation.setCustomerLastName(customer.get().getLastName());
					vehicleReservationMap.put(r.getVehicleId(), vehicleReservation);
				}
			});
		} else {
			System.out.println("No reservations....");
		}

		List<VehicleReservation> vehicleReservations = new ArrayList<VehicleReservation>();
		vehicleReservationMap.forEach((vehicleId, vehicleReservation) -> {
			vehicleReservations.add(vehicleReservation);
		});

		return vehicleReservations;
	}

	private Date getDateFromDateString(String dateString) {
		Date date = null;
		if (dateString != null) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException e) {
				date = new Date();
				e.printStackTrace();
			}
		} else {
			date = new Date();
		}
		return date;
	}

}
