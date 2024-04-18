package com.fardev.vergil.bookings.repository;

import org.springframework.data.repository.CrudRepository;

import com.fardev.vergil.bookings.data.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByLastName(String lastName);
	
}
