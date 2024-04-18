package com.fardev.vergil.bookings.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fardev.vergil.bookings.data.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	List<Reservation> findByReservationDate(Date reservationDate);
	
	List<Reservation> findByCustomerId(long customerId);
	
}
