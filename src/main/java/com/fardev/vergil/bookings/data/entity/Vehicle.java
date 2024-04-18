package com.fardev.vergil.bookings.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

	@Id
	@Column(name = "vehicle_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long vehicleId;

	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@Column(name = "vehicle_model")
	private String vehicleModel;
	
	@Column(name = "number_of_passengers")
	private String numberOfPassengers;
	
	@Column(name = "kilometers")
	private String kilometers;
	
	@Column(name = "production_year")
	private String productionYear;

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getKilometers() {
		return kilometers;
	}

	public void setKilometers(String kilometers) {
		this.kilometers = kilometers;
	}

	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}
	
	
}
