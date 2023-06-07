package com.rgt.vehiclerentalsystem;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalService implements RentalCostCalculator {
	private List<Vehicle> vehicles = new ArrayList<>();
	private List<Rental> rentals = new ArrayList<>();

	public List<Vehicle> getAvailableVehicles() {
		List<Vehicle> availableVehicles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.isAvailable()) {
				availableVehicles.add(vehicle);
			}
		}
		return availableVehicles;
	}

	public Rental rentVehicle(Customer customer, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
		if (vehicle.isAvailable()) {
			Rental rental = new Rental(vehicle, customer, startTime, endTime);
			rentals.add(rental);
			vehicle.setAvailable(false);
			System.out.println("Vehicle rented successfully.");
			return rental;

		} else {
			return null;
		}

	}

	@Override
	public BigDecimal calculateRentalCost(Rental rental) {
		Vehicle vehicle = rental.getRentedVehicle();
		LocalDateTime startTime = rental.getStartTime();
		LocalDateTime endTime = rental.getEndTime();
		long duration = Duration.between(startTime, endTime).toHours();

		BigDecimal rentalCost = BigDecimal.ZERO;
		if (vehicle instanceof Car) {
			rentalCost = BigDecimal.valueOf(duration).multiply(BigDecimal.valueOf(10));
		} else if (vehicle instanceof MotorCycle) {
			rentalCost = BigDecimal.valueOf(duration).multiply(BigDecimal.valueOf(5));
		} else if (vehicle instanceof Bicycle) {
			rentalCost = BigDecimal.valueOf(duration).multiply(BigDecimal.valueOf(2));
		}
		return rentalCost;
	}

	// Return a rented vehicle
	public boolean returnVehicle(Rental rental) {
		if (rentals.contains(rental)) {
			Vehicle vehicle = rental.getRentedVehicle();
			vehicle.setAvailable(true);
			rentals.remove(rental);
			return true;
		}
		return false;
	}

	public Vehicle getVehicleByLicensePlate(String licensePlate) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getLicensePlate().equals(licensePlate)) {
				return vehicle;
			}
		}
		return null;
	}

	public Rental getRentalById(String rentalId) {
		for (Rental rental : rentals) {
			if (rental.getId().equals(rentalId)) {
				return rental;
			}
		}
		return null;
	}

	public void add(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
}
