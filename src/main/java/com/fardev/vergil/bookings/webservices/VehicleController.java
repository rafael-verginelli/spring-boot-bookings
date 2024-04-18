package com.fardev.vergil.bookings.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fardev.vergil.bookings.data.entity.Vehicle;
import com.fardev.vergil.bookings.repository.VehicleRepository;

@RestController
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;

	@RequestMapping(value = "/vehicles", method = RequestMethod.GET)
	List<Vehicle> findAll(@RequestParam(required = false) String vehicleModel) {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if (vehicleModel == null) {
			Iterable<Vehicle> iterableVehicle = vehicleRepository.findAll();
			iterableVehicle.forEach(v -> {
				vehicleList.add(v);
			});
		} else {
			Vehicle vehicle = vehicleRepository.findByVehicleModel(vehicleModel);
			if (vehicle != null) {
				vehicleList.add(vehicle);
			}
		}
		return vehicleList;
	}
}
