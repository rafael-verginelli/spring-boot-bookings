package com.fardev.vergil.bookings.webservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fardev.vergil.bookings.domain.VehicleReservation;
import com.fardev.vergil.bookings.services.ReservationServices;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ReservationsController.class)
public class ReservationServicesTest {

	@MockBean
	private ReservationServices reservationServices;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void getReservations() throws Exception {
		
		List<VehicleReservation> mockReservationList = new ArrayList<VehicleReservation>();
		
		String dateString = "2024-03-15";
		Date date = DATE_FORMAT.parse(dateString);
		
		mockReservationList.add(getMockVehicleReservation(date));
		
		given(reservationServices.getVehicleReservationForDate(dateString)).willReturn(mockReservationList);
		
		this.mockMvc
		.perform(get("/reservations?date=" + "2024-03-15"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Somename Somelastname")));
	}
	
	private VehicleReservation getMockVehicleReservation(Date date) {
		
		VehicleReservation mockVehicleReservation = new VehicleReservation();
		
		mockVehicleReservation.setCustomerFirstName("Somename");
		mockVehicleReservation.setCustomerLastName("Somelastname");
		mockVehicleReservation.setReservationDate(date);
		mockVehicleReservation.setCustomerId(1);
		mockVehicleReservation.setVehicleId(1);
		mockVehicleReservation.setVehicleModel("Some Model");
		mockVehicleReservation.setVehicleType("Some Type");
		
		return mockVehicleReservation;
	}
	
}
