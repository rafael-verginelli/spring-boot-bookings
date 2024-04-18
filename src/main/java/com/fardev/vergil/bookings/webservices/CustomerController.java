package com.fardev.vergil.bookings.webservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fardev.vergil.bookings.data.entity.Customer;
import com.fardev.vergil.bookings.data.entity.Reservation;
import com.fardev.vergil.bookings.repository.CustomerRepository;
import com.fardev.vergil.bookings.repository.ReservationRepository;

@RestController
@RequestMapping(value = "/rest")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Customer>> findAll() {
		List<Customer> customerList = (List<Customer>)customerRepository.findAll();
	
		if(customerList.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerByLastName(@PathVariable("id") String lastName) {
		Customer customer = customerRepository.findByLastName(lastName);
		
		if(customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deleteAllCustomers() {
		reservationRepository.deleteAll();
		customerRepository.deleteAll();
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable(value = "id") long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		
		if(!customer.isPresent()) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);	
		}
		
		Iterable<Reservation> reservationList = (Iterable<Reservation>)reservationRepository.findByCustomerId(id);
		reservationList.forEach(r -> {
			reservationRepository.delete(r);
		});
		
		customerRepository.deleteById(id);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") String lastName, @RequestBody Customer customer) {
		Customer currentCustomer = customerRepository.findByLastName(lastName);
		
		if(currentCustomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);	
		}
		
		currentCustomer.setFirstName(customer.getFirstName());
		currentCustomer.setLastName(customer.getLastName());
		currentCustomer.setAddress(customer.getAddress());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setCountry(customer.getCountry());
		currentCustomer.setState(customer.getState());
		currentCustomer.setPhoneNumber(customer.getPhoneNumber());
		
		customerRepository.save(currentCustomer);
		
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		boolean customerAlreadyExists = customerRepository.existsById(customer.getCustomerId());
		
		if(customerAlreadyExists) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}
		
		customerRepository.save(customer);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(customer.getCustomerId()).toUri());
		
		return new ResponseEntity<Customer>(headers, HttpStatus.CREATED);
	}
}
