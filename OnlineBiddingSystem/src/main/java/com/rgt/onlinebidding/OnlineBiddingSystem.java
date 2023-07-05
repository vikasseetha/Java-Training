package com.rgt.onlinebidding;

import java.util.List;
import java.util.Scanner;

import com.rgt.onlinebidding.entity.Bid;
import com.rgt.onlinebidding.entity.Item;
import com.rgt.onlinebidding.entity.User;
import com.rgt.onlinebidding.exception.InvalidCredentialsException;
import com.rgt.onlinebidding.exception.UserAlreadyExistsException;
import com.rgt.onlinebidding.exception.UserNotFoundException;
import com.rgt.onlinebidding.interfcae.BiddingStrategy;
import com.rgt.onlinebidding.service.AutomaticBiddingStrategy;
import com.rgt.onlinebidding.service.IncrementalBiddingStrategy;
import com.rgt.onlinebidding.service.ItemManagmentService;
import com.rgt.onlinebidding.service.UserManagementService;

public class OnlineBiddingSystem {
	private static Scanner scanner = new Scanner(System.in);
	private static UserManagementService userManagmentService = new UserManagementService();
	private static ItemManagmentService itemManagementService = new ItemManagmentService();
	private static  User currentUser ;
	private static Item item = new Item();

	public static void main(String[] args) {
		System.out.println("------------Welcome to online Bidding System------------");
		boolean exit = false;
		while (!exit) {
			System.out.println("Please choose an option");
			System.out.println("1. Create Account");
			System.out.println("2. Log in");
			System.out.println("3. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				login();
				break;
			case 3:
				exit = true;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
		System.out.println("Thank you for using the online Bidding system");
	}

	private static void createAccount() {
		System.out.println("Enter a username:");
		String username = scanner.nextLine();

		System.out.println("Enter a password:");
		String password = scanner.nextLine();
		try {
			userManagmentService.createUser(username, password);
			System.out.println("Account created successfully");
		} catch (UserAlreadyExistsException e) {
			System.out.println("User registration failed: " + e.getMessage());
		}
	}

	private static void login() {
		System.out.println("Enter a username:");
		String username = scanner.nextLine();

		System.out.println("Enter a password:");
		String password = scanner.nextLine();

		try {
			    currentUser = userManagmentService.authenticateUser(username, password);
			if (currentUser != null) {
				System.out.println("Login successful. Welcome, " + username + "!");
				boolean loggedIn = false;
				while (!loggedIn) {
					System.out.println("\nWelcome to the Online Bidding System!");
					System.out.println("1.Add items");
					System.out.println("2. Search Items");
					System.out.println("3. View Bidding History");
					System.out.println("4. Logout");
					System.out.print("Choose an option: ");
					int userOption = scanner.nextInt();

					switch (userOption) {
					case 1:

						System.out.print("Enter the name: ");
						String name = scanner.next();

						System.out.print("Enter the description: ");
						String description = scanner.next();

						System.out.print("Enter the Bid: ");
						double currentHighestBid = scanner.nextDouble();

						itemManagementService.addItem(name, description, currentHighestBid);
						System.out.println("Item added successfully.");

						break;

					case 2:
						System.out.print("Enter a search keyword: ");
						String keyword = scanner.next();
						List<Item> searchResults = itemManagementService.searchItems(keyword);
						System.out.println("\nSearch results:");
						int index = 1;
						for (Item item : searchResults) {
							System.out.println(index + ". " + item.getName() + " - " + item.getDescription()
									+ " - Current highest bid: Rs " + item.getCurrentHighestBid());
							index++;
						}

						System.out.print("\nEnter an item name to place a bid, or '0' to go back: ");
						String itemName = scanner.next();
						if (itemName.equals("0")) {
							break;
						}

						System.out.print("Enter a bid amount: Rs ");
						double amount = scanner.nextDouble();

						System.out.println("\n1. Incremental Bidding");
						System.out.println("2. Automatic Bidding");
						System.out.print("Choose a bidding strategy: ");
						int strategyOption = scanner.nextInt();

						BiddingStrategy biddingStrategy;
						if (strategyOption == 1) {
							biddingStrategy = new IncrementalBiddingStrategy();
						} else {
							biddingStrategy = new AutomaticBiddingStrategy(item.getCurrentHighestBid(), amount);
						}

						Item selectedItem = null;
						for (Item item : searchResults) {
							if (item.getName().equalsIgnoreCase(itemName)) {
								selectedItem = item;
								break;
							}
						}
						if (selectedItem != null) 
						{
							User user = userManagmentService.getCurrentUser();
							double newBidAmount = biddingStrategy.bid(selectedItem, user, amount);
							System.out.println("\nBid placed successfully. Your bid amount: Rs " + newBidAmount);
						} else {
							System.out.println("\nItem not found.");
						}

						break;

					case 3:
						List<Bid> biddingHistory = itemManagementService.viewBiddingHistory(currentUser);
						System.out.println("\nYour bidding history:");
						int historyIndex = 1;
						for (Bid bid : biddingHistory) {
							System.out.println(historyIndex + ". " + bid.getItem().getName() + " - "
									+ bid.getItem().getDescription() + " - Bid amount: Rs " + bid.getAmount()
									+ " - Winning bid: " + bid.isWinningBid());
							historyIndex++;
						}
						break;
					case 4:
						loggedIn = true;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
						break;
					}
				}
			}
		} catch (InvalidCredentialsException e) {
			System.out.println("Login failed. " + e.getMessage());
		} catch (UserNotFoundException e) {
			System.out.println("Login failed. " + e.getMessage());
		}

	}
}