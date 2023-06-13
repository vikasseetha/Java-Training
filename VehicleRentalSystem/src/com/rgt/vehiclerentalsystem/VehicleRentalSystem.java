package com.rgt.vehiclerentalsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class VehicleRentalSystem {
	public static void main(String[] args) {
		RentalService rentalService = new RentalService();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Vehicle Rental System");
			System.out.println("1. Add Vehicle");
			System.out.println("2. List Available Vehicles");
			System.out.println("3. Rent a Vehicle");
			System.out.println("4. Calculate Rental Cost");
			System.out.println("5. Return Vehicle");
			System.out.println("6. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addVehicle(rentalService, scanner);
				break;
			case 2:
				listAvailableVehicles(rentalService);
				break;
			case 3:
				rentVehicle(rentalService, scanner);
				break;
			case 4:
				calculateRentalCost(rentalService, scanner);
				break;
			case 5:
				returnVehicle(rentalService, scanner);
				break;
			case 6:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

			System.out.println();
		}
	}

	private static void addVehicle(RentalService rentalService, Scanner scanner) 
	{
		System.out.println("Enter the type of vehicle : ");
		System.out.println("1: Car");
		System.out.println("2 :MotorBike");
		System.out.println("3 :Bicycle");
		int type = scanner.nextInt();

		System.out.print("Enter the license plate: ");
		String licensePlate = scanner.next();

		System.out.print("Enter the make: ");
		String make = scanner.next();

		System.out.print("Enter the model: ");
		String model = scanner.next();
		
		Vehicle vehicle;
		switch (type) {
		case 1:
			vehicle = new Car(licensePlate, make, model);
			break;
		case 2:
			vehicle = new MotorCycle(licensePlate, make, model);
			break;
		case 3:
			vehicle = new Bicycle(licensePlate, make, model);
			break;
		default:
			System.out.println("Invalid vehicle type. Vehicle not added.");
			return;
		}
		rentalService.add(vehicle);
		System.out.println("Vehicle added successfully.");
	}

	private static void listAvailableVehicles(RentalService rentalService) {
		List<Vehicle> availableVehicles = rentalService.getAvailableVehicles();
		if (availableVehicles.isEmpty()) {
			System.out.println("No vehicles available for rent.");
		} else {
			System.out.println("Available Vehicles:");
			for (Vehicle vehicle : availableVehicles) {
				System.out.println("License Plate: " + vehicle.getLicensePlate());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model: " + vehicle.getModel());
				System.out.println();
			}
		}
	}

	private static void rentVehicle(RentalService rentalService, Scanner scanner) {
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();

		System.out.print("Enter your ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter the license plate of the vehicle you want to rent: ");
		String licensePlate = scanner.nextLine();

		System.out.print("Enter the start date and time (yyyy-MM-dd HH:mm): ");
		String startDateTimeStr = scanner.nextLine();
		LocalDateTime startTime;

		try {
			startTime = LocalDateTime.parse(startDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date-time format. Please enter in the format yyyy-MM-dd HH:mm.");
			return;
		}

		System.out.print("Enter the end date and time (yyyy-MM-dd HH:mm): ");
		String endDateTimeStr = scanner.nextLine();
		LocalDateTime endTime;

		try {
			endTime = LocalDateTime.parse(endDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date-time format. Please enter in the format yyyy-MM-dd HH:mm.");
			return;
		}

		Customer customer = new Customer(firstName, lastName, id);
		Vehicle selectedVehicle = rentalService.getVehicleByLicensePlate(licensePlate);

		if (selectedVehicle != null) {
			Rental rental = rentalService.rentVehicle(customer, selectedVehicle, startTime, endTime);

			if (rental != null) {
				System.out.println("Rental successful!");
			} else {
				System.out.println("The selected vehicle is not available for rent.");
			}
		} else {
			System.out.println("Invalid license plate. Please try again.");
		}
	}

	private static void calculateRentalCost(RentalService rentalService, Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.nextLine();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			BigDecimal rentalCost = rentalService.calculateRentalCost(rental);
			System.out.println("Rental Cost: $" + rentalCost);
		} else {
			System.out.println("Invalid rental ID.");
		}
	}

	private static void returnVehicle(RentalService rentalService, Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.nextLine();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			boolean success = rentalService.returnVehicle(rental);
			if (success) {
				System.out.println("Vehicle returned successfully.");
			} else {
				System.out.println("Failed to return the vehicle.");
			}
		} else {
			System.out.println("Invalid rental ID.");
		}
	}
}