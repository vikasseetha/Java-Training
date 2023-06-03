package com.rgt.training.session2basics.multipleaccounts;

import java.util.Scanner;

public class MultipleAccounts {
	public static void main(String[] args) {
		ATM atm = new ATM();
		Scanner scanner = new Scanner(System.in);
		Account[] accounts = new Account[10];
		int choice;
		int accountCount = 0;

		while (true) {
			System.out.println("Welcome to the ATM");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				if (accountCount >= accounts.length) {
					System.out.println("Maximum number of accounts reached.");
					break;
				}
				System.out.print("Enter account number: ");
				int accountNumber = scanner.nextInt();
				System.out.print("Enter PIN: ");
				int pin = scanner.nextInt();
				System.out.print("Enter initial balance: ");
				double balance = scanner.nextDouble();
				accounts[accountCount] = new Account(accountNumber, pin, balance);
				System.out.println("Account created successfully!");
				accountCount++;
				break;

			case 2:
				System.out.print("Enter account number: ");
				int accountNumberInput = scanner.nextInt();
				System.out.print("Enter PIN: ");
				int pinNumberInput = scanner.nextInt();
				Account currentAccount = null;
				for (int i = 0; i < accountCount; i++) {
					Account account = accounts[i];
					if (account.getAccountNumber() == accountNumberInput && account.getPin() == pinNumberInput) {
						currentAccount = account;
						break;
					}
				}

				if (currentAccount != null) {
					System.out.println("Login successful!");

					do {
						System.out.println("Welcome to the ATM");
						System.out.println("1. Check Balance");
						System.out.println("2. Deposit");
						System.out.println("3. Withdraw");
						System.out.println("4. Exit");
						System.out.print("Choose an option: ");
						choice = scanner.nextInt();
						switch (choice) {
						case 1:
							System.out.println("Current balance: $" + currentAccount.getBalance());
							break;
						case 2:
							System.out.print("Enter deposit amount: $");
							double depositAmount = scanner.nextDouble();
							double balance2 = currentAccount.getBalance();
							double bb = balance2 + depositAmount;
							currentAccount.setBalance(bb);
							atm.deposit(currentAccount, depositAmount);
							break;
						case 3:
							System.out.print("Enter withdrawal amount: $");
							double withdrawalAmount = scanner.nextDouble();
							currentAccount.setBalance(currentAccount.getBalance() - withdrawalAmount);
							atm.withdraw(currentAccount, withdrawalAmount);
							break;
						case 4:
							System.out.println("Goodbye!");
							break;
						default:
							System.out.println("Invalid option. Please try again.");
						}
						System.out.println();
					} while (choice != 4);
				} else {
					System.out.println("Invalid account number or PIN.");
				}
				break;

			case 3:
				System.out.println("Goodbye!");
				scanner.close();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}

			System.out.println();
		}
	}

}
