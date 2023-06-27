package com.rgt.onlineshopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ShoppingSystem {

	private static final String PRODUCT_FILE = "product.txt";
	private static final String ORDER_HISTORY_FILE = "order.txt";
	private static Scanner scanner = new Scanner(System.in);
	private static ProductCatalog productCatalog = new ProductCatalog();
	private static ShoppingCart shoppingCart = new ShoppingCart();
	private static OrderHistory orderHistory = new OrderHistory();

	public static void main(String[] args) {

		System.out.println("-----------Welcome to the online shopping system----------------------");
		loadOrCreateProductFile();
		boolean exit = false;
		while (!exit) {
			displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				browseProducts();
				break;
			case 2:
				addToCart();
				break;
			case 3:
				viewCart();
				break;
			case 4:
				removeFromCart();
				break;
			case 5:
				placeOrder();
				break;
			case 6:
				loadOrderHistoryFile();
				viewOrderHistory();
				break;
			case 7:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
		System.out.println("Thank you for using the Online Shopping System.");
	}

	/**
	 * This method will load the exisisting producct or it will create new product
	 * file
	 */
	private static void loadOrCreateProductFile() {
		System.out.print("Do you want to load an existing product file? (y/n) : ");
		String choice = scanner.nextLine().trim().toLowerCase();
		if (choice.equals("y")) {
			System.out.print("Enter the file name: ");
			String filename = scanner.nextLine();
			productCatalog.loadProducts(filename);
		} else {
			createNewProductFile();
		}
	}

	/**
	 * In this method we can create number of products to the inventory
	 */
	private static void createNewProductFile() {
		System.out.print("Enter the file name to save the product file : ");
		String filename = scanner.nextLine();

		System.out.print("Enter the number of products you want to add : ");
		int numProducts = scanner.nextInt();
		scanner.nextLine();

		for (int i = 1; i <= numProducts; i++) {
			System.out.println("Enter details for Product " + i + ":");
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Description: ");
			String description = scanner.nextLine();
			System.out.print("Price: ");
			double price = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();

			Product product = new Product(name, description, price, quantity);
			productCatalog.addProduct(product);
		}

		saveProductFile(filename);
	}

	/**
	 * This method will save the product file
	 * 
	 * @param filename
	 */
	private static void saveProductFile(String filename) {
		productCatalog.saveProducts(filename);
		System.out.println("-------Product file saved successfully.----------");
	}

	@SuppressWarnings("unused")
	private static void saveProductFile() {
		saveProductFile(PRODUCT_FILE);
	}

	/**
	 * This method will save the OrderHistory after completing the order
	 */
	private static void saveOrderHistoryFile() 
	{
	
		orderHistory.saveOrderHistory(ORDER_HISTORY_FILE);
		System.out.println("Order history file saved successfully.");
	}

	/**
	 * This method will load the order history of previous orders 
	 */
	private static void loadOrderHistoryFile() {
		orderHistory.loadOrderHistory(ORDER_HISTORY_FILE);
		System.out.println("Order history file loaded successfully.");
	}

	private static void displayMenu() {
		System.out.println("\n----------------Menu: -----------------------");
		System.out.println("1. Browse Products");
		System.out.println("2. Add to Cart");
		System.out.println("3. View Cart");
		System.out.println("4. Remove from Cart");
		System.out.println("5. Place Order");
		System.out.println("6. View Order History");
		System.out.println("7. Exit");
		System.out.print("Enter your choice: ");
	}

	/**
	 * In this method we will get all the available products that are available in inventory
	 */
	private static void browseProducts() {
		List<Product> products = productCatalog.getAllProducts();
		System.out.println("---------------\nAvailable Products:-------------");
		for (Product product : products) {
			System.out.println(product);
			System.out.println("----------------------------------------");
		}
	}
	
	/**
	 * In this method we will add the particular items of what we want to add from inventory with the product name  and quantity
	 * IF we quantity more than the quantity in inventory we will get Invalid quantity
	 */
	private static void addToCart() {
		System.out.println("-------------Add items to the cart--------------");
		System.out.print("Enter the name of the product to add: ");
		String productName = scanner.next();
		int quant = 0;
		Product product = productCatalog.getProduct(productName);
		Map<Product, Integer> displayCart = shoppingCart.getcart();
		for (Entry<Product, Integer> prd : displayCart.entrySet()) {
			if (prd.getKey().getName().equals(productName)) {
				quant = prd.getValue();
			}
		}
//		quant = displayCart.getOrDefault(product, quant);
		if (product != null) {
			System.out.print("Enter the quantity to add the cart : ");
			int quantity = scanner.nextInt();

			if (quantity > 0 && quantity + quant <= product.getQuantity()) {
				shoppingCart.addItem(product, quantity);
				System.out.println("Product added to cart with Quantity " + quantity);
			} else {
				System.out.println("Invalid quantity please try again");
			}
		} else {
			System.out.println("Product not found. Please try again.");
		}
	}
   
	/**
	 * In this method we can view the cart item that we added to the store 
	 * we will get the name of Prodcuct and quantity
	 * Total price
	 */
	private static void viewCart() {
		System.out.println("----------View Cart-------------");
		shoppingCart.displayCart();
		System.out.println("Total Price: $" + shoppingCart.getTotalPrice());
	}
    
	/**
	 * This  method will remove the product from the cart
	 * In this we will get the products by name if product are not there Product not found in cart
	 * If the name name doesnot matches in the inventory will get product name not found in cart
	 */
	private static void removeFromCart() {
		System.out.println("----------Remove item from Cart-------------");
		System.out.print("Enter the product to remove from cart : ");
		String productName = scanner.nextLine();
		Product product = productCatalog.getProduct(productName);
		if (product != null) {
			if (shoppingCart.getItems().containsKey(product)) {
				shoppingCart.removeItem(product);
				System.out.println(productName + " remove from the cart ");
			} else {
				System.out.println(productName + " is not in the cart ");
			}
		} else {
			System.out.println(" product not found in the cart ");
		}
	}
	
	/**
	 * In thiis method we will place the order of the total items in the cart
	 * Once the order is placed it will deduct from the inventory   
	 */
	private static void placeOrder() {
		if (shoppingCart.getItems().isEmpty()) {
			System.out.println("The cart is empty. Please add items to the cart.");
			return;
		}
		double totalPrice = shoppingCart.getTotalPrice();
		Map<Product, Integer> cartItems = new HashMap<>(shoppingCart.getItems());
		Order order = new Order(cartItems, totalPrice);
		orderHistory.addOrder(order);
		shoppingCart.clearCart();
		for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			product.setQuantity(product.getQuantity() - quantity);
		}
		System.out.println("Order placed successfully! Order Confirmation Number: " + order.getConfirmationNumber());
		saveOrderHistoryFile();
	}
	
	/**
	 * In this method we will the OrderHistory of we ordered  
	 * Total price,Product name  and quantity
	 */
	
	private static void viewOrderHistory() {
		List<Order> orders = orderHistory.getOrders();
		if (orders.isEmpty()) {
			System.out.println("No orders placed yet");
		} else {
			for (Order order : orders) 
			{
				System.out.println("--------Order History------------");
				System.out.println("Total Price : $" + order.getTotalPrice());
				System.out.println("Confirmation Number :" + order.getConfirmationNumber());
				System.out.print("Items :");
				for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
					Product product = entry.getKey();
					int quantity = entry.getValue();
					System.out.println(product.getName() + " (Quantity: " + quantity + ")");
				}
				System.out.println();
			}
		}
	}
}