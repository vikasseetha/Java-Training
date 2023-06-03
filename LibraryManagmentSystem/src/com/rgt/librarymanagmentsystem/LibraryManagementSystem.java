package com.rgt.librarymanagmentsystem;

import java.util.Scanner;

public class LibraryManagementSystem {
	private static final int MAX_BOOKS = 100;
	private static final int MAX_PATRONS = 100;

	private String[] bookTitle = new String[MAX_BOOKS];;
	private String[] authors = new String[MAX_BOOKS];;
	private String[] patrons = new String[MAX_PATRONS];;
	private String[] borrowedBooks = new String[MAX_BOOKS];;
	private boolean[] bookAvailability = new boolean[MAX_BOOKS];

	private int numBooks = 0;
	private int numPatrons = 0;
	private int numBorrowedBooks = 0;

	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
		libraryManagementSystem.library();
	}

	public void library() {
		System.out.println("Welcome to the Library !");

		while (true) {
			System.out.println("1. Add Book");
			System.out.println("2. Add Patron");
			System.out.println("3. Borrow Book");
			System.out.println("4. Return Book");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter the title of the book: ");
				String title = scanner.nextLine();
				bookTitle[numBooks] = title;

				System.out.print("Enter the author of the book: ");
				String author = scanner.nextLine();
				authors[numBooks] = author;

				if (numBooks == MAX_BOOKS) {
					System.out.println("Maximum number of books reached.");
					return;
				}

				bookAvailability[numBooks] = true;
				numBooks++;
				System.out.println("Book added successfully.");
				break;

			case 2:
				System.out.print("Enter the name of the patron: ");
				String name = scanner.nextLine();

				if (numPatrons == MAX_PATRONS) {
					System.out.println("Maximum number of patrons reached.");
					return;
				}
				patrons[numPatrons] = name;
				numPatrons++;
				System.out.println("Patron added successfully.");
				break;

			case 3:
				if (numBooks == 0) {
					System.out.println("No books available in the library.");
					return;
				}

				System.out.print("Enter the title of the book to borrow: ");
				String borrowBookTitle = scanner.nextLine();
				int bookIndex = findBookIndex(borrowBookTitle);
				if (bookIndex == -1) {
					System.out.println("Book not found in the library.");
					return;
				}

				System.out.print("Enter the name of the patron : ");
				String patronName = scanner.nextLine();
				int patronIndex = findPatronIndex(patronName);
				if (patronIndex == -1) {
					System.out.println("Patron not found in the library.");
					return;
				}

				if (!bookAvailability[bookIndex]) {
					System.out.println("Book is currently borrowed by another patron.");
					return;
				}

				borrowedBooks[numBorrowedBooks] = borrowBookTitle;
				bookAvailability[bookIndex] = false;
				numBorrowedBooks++;
				System.out.println("Book borrowed successfully.");
				break;

			case 4:
				System.out.print("Enter the title of the book to return: ");
				String booktitle = scanner.next();
				int bookindex = findBorrowedBookIndex(booktitle);
				if (bookindex == -1) {
					System.out.println("Book is currently borrowed by another patron.");
					return;
				}

				System.out.print("Enter the Patron to return: ");
				String patron = scanner.next();
				int patronindex = findPatronIndexByBorrowedBook(patron);
				if (patronindex == -1) {
					System.out.println("Patron not found in the library.");
					return;
				}
				bookAvailability[bookindex] = true;
				bookAvailability[patronindex] = true;
				for (int i = bookindex; i < numBorrowedBooks - 1; i++) {
					borrowedBooks[i] = borrowedBooks[i + 1];
				}
				for (int i = patronindex; i < numBorrowedBooks - 1; i++) {
					borrowedBooks[i] = borrowedBooks[i + 1];
				}

				borrowedBooks[numBorrowedBooks - 1] = null;
				numBorrowedBooks--;

				System.out.println("Book returned successfully.");
				break;
			case 5:
				System.out.println("Thank you for using the Library");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private int findBookIndex(String title) {
		for (int i = 0; i < numBooks; i++) {
			if (bookTitle[i].equalsIgnoreCase(title)) {
				return i;
			}
		}
		return -1;
	}

	private int findPatronIndex(String name) {
		for (int i = 0; i < numPatrons; i++) {
			if (patrons[i].equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	private int findBorrowedBookIndex(String title) {
		for (int i = 0; i < numBorrowedBooks; i++) {
			if (borrowedBooks[i].equalsIgnoreCase(title)) {
				return i;
			}
		}
		return -1;
	}

	private int findPatronIndexByBorrowedBook(String title) {
		for (int i = 0; i < numPatrons; i++) {
			if (patrons[i].equalsIgnoreCase(title)) {
				return i;
			}
		}
		return -1;
	}
}
