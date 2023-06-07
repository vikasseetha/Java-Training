//package com.rgt.vehiclerentalsystem;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//public class Rental 
//{
//	private String id;
//	private Vehicle rentedVehicle;
//	private Customer customer;
//	private LocalDateTime startTime;
//	private LocalDateTime endTime;
//	
//	public String getId() {
//		return id;
//	}
//
//	public Vehicle getRentedVehicle() {
//		return rentedVehicle;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public LocalDateTime getStartTime() {
//		return startTime;
//	}
//
//	public LocalDateTime getEndTime() {
//		return endTime;
//	}
//
//	public Rental(Vehicle rentedVehicle, Customer customer, LocalDateTime startTime, LocalDateTime endTime) {
//		super();
//		this.id = UUID.randomUUID().toString();
//        System.out.println("Rental Id : " + id);
//		this.rentedVehicle = rentedVehicle;
//		this.customer = customer;
//		this.startTime = startTime;
//		this.endTime = endTime;
//	}	
//}
package com.rgt.vehiclerentalsystem;

import java.time.LocalDateTime;
import java.util.UUID;

public class Rental {
	private String id;
	private Vehicle rentedVehicle;
	private Customer customer;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public Rental(Vehicle rentedVehicle, Customer customer, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = UUID.randomUUID().toString();
		System.out.println("Rental Id : " + id);
		this.rentedVehicle = rentedVehicle;
		this.customer = customer;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public Vehicle getRentedVehicle() {
		return rentedVehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
}