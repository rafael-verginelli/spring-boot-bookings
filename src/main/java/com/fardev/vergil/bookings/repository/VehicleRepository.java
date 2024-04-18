package com.fardev.vergil.bookings.repository;

import org.springframework.data.repository.CrudRepository;

import com.fardev.vergil.bookings.data.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>{

	Vehicle findByVehicleModel(String vehicleModel);
	
}
