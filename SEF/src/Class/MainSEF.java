package Class;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class MainSEF {

	Scanner scn = new Scanner(System.in);

	private static final int MANAGER = 3;
	private static final int WAREHOUSE = 2;
	private static final int SALESTAFF = 1;
	boolean newRun = true;
	private String id;
	private String password;
	private String input;

	LinkedList<Employee> employees = new LinkedList<Employee>();
	LinkedList<Product> productList = new LinkedList<Product>();
	LinkedList<Customer> customers = new LinkedList<Customer>();
	IOmachine iom = new IOmachine();
	Customer currentCustomer = null;
	Employee currentEmployee = null;
	int [][] orders = new int[10][2];

	public void run() {

		loadSystemData();
		login();
	}



	private void loadSystemData() {
		employees = iom.readInEmployee() ;
		productList = iom.readInProducts() ;
		customers = iom.readInCustomer() ;
		orders[0][0] = 1;
		orders[0][1] = 100;
		orders[1][0] = 2;
		orders[1][1] = 80;
		orders[2][0] = 4;
		orders[2][1] = 100;
	}



	public void login() {
		if (employees.isEmpty()) {
			System.out.println("ERROR: No Employees Registered!\nGOODBYE!!"  );
			System.exit(0);
		}

		if (newRun) {
			System.out.println("*- - - - - WELCOME - - - - -*");
		}
		newRun = false;
		System.out.print("Enter ID: ");
		id = stringCheck("Enter ID: ", id);

		//Used only for Administrator.
		if (id.equalsIgnoreCase("quit")) {
			System.exit(0);
		}

		/* All EMPLOYEE ID start with 'E' */
		if (id.charAt(0) == 'E') {
			System.out.print("Enter Password: ");
			password = stringCheck("Enter Password: ", password);

			if (validateEmployee()) {
				switch (currentEmployee.GetLevel()) {

				case SALESTAFF: displaySalesMenu();
				break;

				case WAREHOUSE: displayWarehouseMenu();
				break;

				case MANAGER: displayManagerMenu();
				break;
				}
			}
			else {
				System.out.println("Invalid Employee ID or Password, please try again!\n");
				login();
			}
		}
		/* All CUSTOMER ID start with 'c' */
		else if (id.charAt(0) == 'C') {
			if (validateCustomer()) {
				currentCustomer.logIn(); //Customer visit counter is increased here.
				displayCustomerMenu();
			}
			else {
				System.out.println("Customer ID doesn't exist..\n");
				login();
			}

		}
		//If failed to login to system.
		else if (currentEmployee == null && currentCustomer == null) {
			System.out.println("Invalid login input detected, please try again..\n");
			login();
		}
	}


	//Method to check who signed in. (currentEmployee)
	private boolean validateEmployee() {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i) != null) {
				if (employees.get(i).GetID().equals(id) && employees.get(i).GetPassword().equals(password)) {
					System.out.println("\n*- - - - - Log in Succuessful - - - - -*" + "\n");
					currentEmployee = employees.get(i);
					return true;
				}
			}
		}
		return false;
	}


	//Method to check which customer. (currentCustomer)
	private boolean validateCustomer() {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i) != null) {
				if (customers.get(i).getID().equals(id)) {
					currentCustomer = customers.get(i);
					return true;
				}
			}
		}
		return false;

	}

	//Method to check for blank input.
	//Prompt what you want code to re-prompt for input.
	private String stringCheck(String prompt, String input) {
		input = scn.nextLine();
		while (input.equals("")) {
			System.out.print(prompt);
			input = scn.nextLine();
		}
		return input;
	}


	private Product searchProductById(int productId)	{
		Product product = null;
		for (Product element : productList)	{
			if (productId == element.getProductId())	{
				product = element;
				return product;
			}
		}
		return null;
	}


	private void backToMenu()
	{
		System.out.println("Hit ENTER to return to the Main Menu");
		scn.nextLine();

		//Customer Priority
		if (currentCustomer != null) {
			displayCustomerMenu();
		}
		else {
			switch (currentEmployee.GetLevel()) {

			case SALESTAFF: displaySalesMenu();
			break;

			case WAREHOUSE: displayWarehouseMenu();
			break;

			case MANAGER: displayManagerMenu();
			break;
			}
		}
	}




	// - - - - - - - - - - CUSTOMER METHOD - - - - - - - - - -
	private void displayCustomerMenu() {
		System.out.println("-- - -- - -- - -- MAIN MENU -- - -- - -- - --" );
		System.out.printf("%15s's Loyalty Points: %d\n", currentCustomer.getID(), currentCustomer.getPoint());
		System.out.println("1. View Full Product List");
		System.out.println("2. Search for Product (Using ID)");
		System.out.println("3. View All Discounts");
		System.out.println("4. Search for Discounts (Using ID)");
		System.out.println("5. View Cart" );
		System.out.println("6. Toggle Subscription");
		System.out.println("7. Check Out\n");
		System.out.println("8. Quit\n");

		System.out.print("Select Option: " );

		try {
			input = stringCheck("Select Option: ", input);
			int choice = Integer.parseInt(input);


			switch(choice) {
			case 1: //View Product List
				displayProductListMenu();
				System.out.println();
				addProductInCart();
				break;

			case 2: //Search for Product (Using ID)
				System.out.print("Please enter Product ID: ");
				boolean correct = false;

				while(!correct)	{
					try {
						Product resultProduct = searchProductById(Integer.parseInt(scn.nextLine()));
						if (resultProduct != null)	{
							System.out.println("Here's the product you are looking for:\n");
							System.out.println(resultProduct.getProductName() + "\t"
									+ resultProduct.getProductPrice() + "\n");
							System.out.println("Do you want to add this product into your cart? (Y/N)");
							input = stringCheck("Input Y/N\n", input);

							if (input.equalsIgnoreCase("Y")) {
								currentCustomer.addProduct(resultProduct);
								System.out.println("Item added successfully.");
							}
							else {
								System.out.println("Item not added to cart.");
							}
							correct = true;
						}
						else	{
							System.out.println("Sorry, this item doesn't exist.");
							break;
						}
					}
					catch(NumberFormatException e) {
						System.out.println("This is not a product id, the product id should be a series of number.");
						break;
					}
				}
				break;

			case 3: //View All Discounts
				boolean haveDiscount = false;

				System.out.printf("%70s\n", "-- - -- - -- - -- CURRENT DISCOUNTS -- - -- - -- - --");
				for (Product eaProduct : productList) {
					if (eaProduct.getDiscountPrice() != eaProduct.getProductPrice()) {
						System.out.printf("%-35s     Original Price: $%.2f | Discounted Price: $%.2f \n", eaProduct.getProductName(), eaProduct.getProductPrice(), eaProduct.getDiscountPrice());
						haveDiscount = true;
					}
					if (eaProduct.getBulkAmount() != 0) {
						System.out.printf("%-35s     (Buy %d and get $%.2f OFF!)\n", eaProduct.getProductName(), eaProduct.getBulkAmount(), eaProduct.getbulkDiscount());
						haveDiscount = true;
					}
				}
				if (!haveDiscount) {
					System.out.println("Sorry! No discounts currently available..");
				}
				break;

			case 4: //Search for Discounts (Using ID)
				boolean searchMatch = false;
				haveDiscount = false;
				System.out.print("Input ID of item: ");
				input = stringCheck("Input ID of item: ", input);
				int searchId = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if(searchId == eaProduct.getProductId()) {
						searchMatch = true;
						System.out.printf("%60s\n", "-- - -- - -- - -- CURRENT DISCOUNTS -- - -- - -- - --");
						if (eaProduct.getDiscountPrice() != eaProduct.getProductPrice()) {
							System.out.printf("%-35s     Original Price: $%.2f | Discounted Price: $%.2f \n", eaProduct.getProductName(), eaProduct.getProductPrice(), eaProduct.getDiscountPrice());
							haveDiscount = true;

						}
						if (eaProduct.getBulkAmount() != 0) {
							System.out.printf("%-35s     (Buy %d and get $%.2f OFF!)\n", eaProduct.getProductName(), eaProduct.getBulkAmount(), eaProduct.getbulkDiscount());
							haveDiscount = true;
						}
					}
				}

				if (!searchMatch) {
					System.out.println("Invalid ID, please try again..");
				}
				else if (!haveDiscount) {
					System.out.println("SORRY! No discount on this item currently available..");
				}

				break;

			case 5: //View Cart
				displayCart();
				break;

			case 6: //Toggle Subscription
				currentCustomer.subscribe();
				break;

			case 7: // CheckOut
				checkOut();
				break;

			case 8:
				quit();
				break;

			default:
				System.out.println("Please select a valid choice!\n");
				displayCustomerMenu();
				break;

			}
			backToMenu();
		}
		catch (Exception e){
			System.out.println("Please input a number only..");
			backToMenu();
		}

	}

	/* Method that Just Displays a Menu */
	private void displayProductListMenu() {

		System.out.println("-- - -- - -- - -- PRODUCTS -- - -- - -- - --");
		for(int i = 0; i < productList.size(); i++) {
			if (productList.get(i) != null) {
				String product = (i+1) + ". " + productList.get(i).getProductName();
				double price = productList.get(i).getProductPrice();
				String menu = String.format("%-30s %.2f", product, price);
				System.out.println(menu);
			}
		}

	}

	/* Adds product to Customer's "Basket"*/
	private void addProductInCart()
	{
		int prodNumber = 0;
		boolean quit = false;

		System.out.println("Select product to add to cart(enter the number) or enter 'q' to exit.");
		while(!quit) {

			input = stringCheck("Select product to add to cart(enter the number) or enter 'q' to exit.\n", input);
			if (input.equalsIgnoreCase("q")) {
				displayCart();
				quit = true;
			}
			else if (input.equalsIgnoreCase("remove")) {
				login();
			}
			else {

				try {
					prodNumber = Integer.parseInt(input);


					if(prodNumber < 1 || prodNumber > productList.size()) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					if (productList.get(prodNumber - 1) == null) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					//Might or might not insert out of stock
					else {
						currentCustomer.addProduct(productList.get(prodNumber - 1));
						//						displayProductListMenu();
						System.out.println();
						System.out.println("Item added successfully!");
						System.out.println("Total number of '" + productList.get(prodNumber - 1).getProductName()
								+ "' in cart: "
								+ productList.get(prodNumber - 1).getNumberInCart()
								);
						System.out.println("Select another product or enter 'q' to exit (call staff for removal of items)");
					}
				}
				catch(InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/* Displays Cart, no other function */
	private void displayCart() {
		Product[] customerCart = currentCustomer.getCart();
		String name, amount, cart  = "";
		int id = 0;

		System.out.println("- WHAT'S IN YOUR CART: - ");
		for (int i = 0; i < customerCart.length; i++)
		{
			if (customerCart[i] != null)
			{
				name = (customerCart[i].getProductName());
				amount = Integer.toString(customerCart[i].getNumberInCart());
				id = customerCart[i].getProductId();
				cart += String.format("%-30s %d %25s\n", name, id, amount);
			}
		}
		if (cart.equals(""))
		{
			System.out.println("Your cart is empty!");
		}
		else
		{
			System.out.printf("%-30s %s %32s", "NAME", "ID", "NUMBER OF ITEMS\n");
			System.out.println(cart + "\n");
		}
	}

	/* Finishes a Sale Process */
	private void checkOut() {
		Sale checkout = new Sale(currentCustomer);
		checkout.CheckPrice();
		System.out.println("Do you want to proceed? (Y/N)");
		input = stringCheck("Do you want to proceed? (Y/N)", input);
		
		if (input.equalsIgnoreCase("Y")) {
			checkout.PerformSale();
			iom.saveCustomer(customers);
			System.out.println("Thank you for shopping with us.");
			System.out.println("Goodbye!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reLogin();
		}
		
		if(input.equalsIgnoreCase("N")) {
			System.out.println("Do you want to keep this sale? (Y/N)");
			input = stringCheck("Do you want to keep this sale? (Y/N)", input);
			if (input.equalsIgnoreCase("Y")) {
				backToMenu();
			}
			else {
				quit();
			}
		}
		else {
			System.out.println("Sorry invalid input" + '\n');
			checkOut();
		}

		//		currentCustomer.emptyCart();
//		currentCustomer = null;
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(" " + '\n' + '\n' + '\n' + '\n' + '\n' + '\n' + '\n');
//		login();
	}

	// - - - - - - - - - - WAREHOUSE METHOD - - - - - - - - - -
	private void displayWarehouseMenu() {
		System.out.println("-- - -- - -- - -- WAREHOUSE MENU -- - -- - -- - --" );
		System.out.println("1. View Stock Level");
		System.out.println("2. Replenish Stock Level" );
		System.out.println("3. Logout (Re-Login)\n");
		System.out.println("4. Quit\n");
		System.out.print("Select Option: ");

		try {
			input = stringCheck("Select Option", input);

			int choice = Integer.parseInt(input);
			switch(choice) {

			// 1. View Stock Level
			// 2. Replenish Stock Level
			// 3. Logout (Re-Login)
			// 4. Quit

			case 1:
				displayProductStock();
				break;
			case 2:
				replenishStock();
				break;
			case 3:
				reLogin();
				break;
			case 4:
				quit();
			default:
				System.out.println("Please select a valid choice!\n");
				displayWarehouseMenu();
				break;
			}
			backToMenu();
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			displayWarehouseMenu();
		}
	}

	// - - - - - - - - - - WAREHOUSE METHOD - - - - - - - - - -
	private void displayProductStock() {
		String productName, menu = "";
		int productStock, productId = 0;

		System.out.printf("%-30s %s %30s\n", "ITEM NAME" , "ID" ,"CURRENT STOCK" );

		for(int i = 0; i < productList.size(); i++) {
			if (productList.get(i) != null) {
				productName = (i+1) + ". " + productList.get(i).getProductName();
				productStock = productList.get(i).getProductStock();
				productId = productList.get(i).getProductId();
				menu = String.format("%-30s %s %25d", productName, productId, productStock);
				System.out.println(menu);
			}
		}
	}

	// - - - - - - - - - - WAREHOUSE METHOD - - - - - - - - - -
	private void replenishStock() {
		String productName, input = "";
		int productId, replenishAmount = 0;

		System.out.print("Input the ID of the item you'd like to replenish or enter 'q' to cancel: ");

		input = stringCheck("Input the ID of the item you'd like to replenish or enter 'q' to cancel: ", input);
		if (input.equals("q")) {
			displayWarehouseMenu();
			System.out.println("");
		}

		productId = Integer.parseInt(input);
		for(int i = 0; i < productList.size(); i++) {
			if (productList.get(i) != null) {
				productName = productList.get(i).getProductName();

				if (productId == productList.get(i).getProductId()) {
					System.out.println("\nProduct selected: " + productName);
					System.out.print("Input amount of stock to replenish: ");
					replenishAmount = Integer.parseInt(scn.nextLine());
					productList.get(i).increaseStock((replenishAmount));
					System.out.println(productName + "'s stock successfully increased by " + replenishAmount + ".");
					backToMenu();
				}
			}
			else {
				System.out.println("Invalid product ID, please try again.");
				replenishStock();

			}
		}
	}


	// - - - - - - - - - - SALES STAFF METHOD - - - - - - - - - -
	public void displaySalesMenu( ) {
		System.out.println("*- - - - - WELCOME " + currentEmployee.GetName() + "- - - - -*");
		System.out.println("1. Remove Item");
		System.out.println("2. Stop Removing Items\n");
		System.out.println("3. Quit\n");

		System.out.print("Select Option: ");
		try {
			input = stringCheck("Select Option: ", input);

			int choice = Integer.parseInt(input);
			System.out.println();
			switch(choice) {

			// 1. Remove Item
			// 2. Stop Removing Items
			// 3. Quit

			case 1:
				removeItem();
				break;
			case 2:
				break;
			case 3:
				quit();
			default:
				System.out.println("Please select a valid choice!\n");
				displaySalesMenu();
				break;

			}
			backToMenu();
		}
		catch (NumberFormatException e) {
			System.out.println("Input number only..");
			displaySalesMenu();
		}
	}

	//	- - - - - - - - - - SALES STAFF METHOD - - - - - - - - - -
	public void removeItem() {

		boolean removed = false;
		int inputId, removeAmount = 0;
		Product[] customerCart = currentCustomer.getCart();

		displayCart();

		System.out.println("Enter ID of item to remove or enter 'q' to cancel: ");
		input = stringCheck("Enter ID of item to remove or enter 'q' to cancel: \n", input);
		// to quit removing items
		if (input.equals("q")) {
			displaySalesMenu();
		}

		inputId = Integer.parseInt(input) - 1;
		if (customerCart[inputId] != null ) {
			// Removal of item starts here
			System.out.println("Amount to remove: ");
			input = scn.nextLine();
			removeAmount = Integer.parseInt(input);

			while (removeAmount < 0 || removeAmount > customerCart[inputId].getNumberInCart()) {
				System.out.println("Invalid amount, try again");
				input = scn.nextLine();
				removeAmount = Integer.parseInt(input);
			}

			// Removal of Item
			currentCustomer.removeProduct(customerCart[inputId] , removeAmount);
			System.out.println("Item succesfully removed.");
			removed = true;
		}

		if (!removed) {
			System.out.println("Item selected is not in cart\n");
			removeItem();
		}
		else {
			System.out.println("Would you like to remove another item? Y/N");
			input = scn.nextLine();

			if (input.equalsIgnoreCase("y")) {
				removeItem();
			}
			else {
			}
		}
	}

	// ***** GENERAL METHOD *****
	public void quit() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
				+ "Thank you for shopping with us..\n"
				+ "See you soon! :D\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.exit(0);
	}

	// ***** GENERAL METHOD *****
	public void reLogin() {
		currentCustomer.emptyCart();
		currentCustomer = null;
		currentEmployee = null;
		newRun = true;
		login();
		id = "";
		password = "";
		// set everything to null
		// start a new
	}
	//	- - - - - - - - - - MANAGER METHOD - - - - - - - - - -
	public void displayManagerMenu()
	{

		System.out.println("-- - -- - -- - -- MANAGER MENU -- - -- - -- - --" );
		System.out.println("1. Display Report Menu");
		System.out.println("2. Manage Staff");
		System.out.println("3. Manage Products");
		System.out.println("4. Auto Ordering");
		System.out.println("5. Logout (Re-Login)\n");
		System.out.println("6. Quit\n");

		System.out.print("Select Option: " );
		try {

			input = stringCheck("Select Option: ", input);

			int choice = Integer.parseInt(input);
			System.out.println();
			switch(choice) {

			case 1:
				displayReportMenu();
				break;
			case 2:
				manageStaff();
				break;
			case 3:
				manageProducts();
				break;
			case 4:
				autoOrdering();
				break;
			case 5:
				reLogin();

			case 6:
				quit();

			default:
				System.out.println("Please select a valid choice!\n");
				displayManagerMenu();
				break;
			}
			backToMenu();
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			displayManagerMenu();
		}
	}

	private void autoOrdering(){
		System.out.println("-- - -- - -- - -- AUTO ORDERING-- - -- - -- - --" );
		System.out.println("1. View current Auto Ordering");
		System.out.println("2. Set up new Auto Order");
		System.out.println("3. Change an Auto Order");

		System.out.print("Select Option: " );
		try {
			input = stringCheck("Select Option: ", input);

			int choice = Integer.parseInt(input);
			int i = 0;
			int productID;
			switch(choice) {
			case 1: //view current auto order
				i=0;
				for(i =0; i < orders.length; i++){
					if(orders[i][1] != 0){
						String name = productList.get(orders[i][0] - 1).getProductName();
						String supplier = productList.get(orders[i][0] - 1).getSupplierName();
						System.out.println("Product " + name + " is topping up to " + orders[i][1] +  " from " + supplier );
					}
				}
				/*
				for(i =0; i < orders.length; i++){
					System.out.println(orders[i][0]+" : " + orders[i][1]);
				}
				 */
				break;
			case 2: //set up new auto order
				i=0;
				System.out.println("Enter Product ID");
				productID = Integer.parseInt(scn.nextLine());
				System.out.println("Enter amount it should top up to");
				int newAmount = Integer.parseInt(scn.nextLine());
				while(orders[i][0] != 0){
					i++;
				}
				orders[i][0] = productID;
				orders[i][1] = newAmount;
				int j = 0;
				while(productID != productList.get(j).getProductId()){
					j++;
				}
				String name = productList.get(j).getProductName();
				String supplier = productList.get(j).getSupplierName();
				System.out.println("Product " + name + " is now going to top up to " + orders[i][1] +  " from " + supplier );
				/*
				for(i =0; i < orders.length; i++){
					System.out.println(orders[i][0]+" : " + orders[i][1]);
				}
				 */
				break;
			case 3: //change auto order
				i=0;
				System.out.println("Enter Product ID");
				productID = Integer.parseInt(scn.nextLine());
				System.out.println("Enter the number you want it to top up to");
				int newNumber = Integer.parseInt(scn.nextLine());
				while(productID != productList.get(i).getProductId()){
					i++;
				}
				orders[i][1] = newNumber;
				name = productList.get(i).getProductName();
				supplier = productList.get(i).getSupplierName();
				System.out.println("Product " + name + " is now topping up to " + orders[i][1] +  " from " + supplier );
				break;
			default:
				System.out.println("Please select a valid choice!\n");
				displayManagerMenu();
				break;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			autoOrdering();
		}
	}


	// ***** MANAGER METHOD *****
	private void manageProducts() {
		System.out.println("-- - -- - -- - -- MANAGE PRODUCTS -- - -- - -- - --" );
		System.out.println("1. Add Product");
		System.out.println("2. Change Product Name");
		System.out.println("3. Change Product Price");
		System.out.println("4. Change Product Details");
		System.out.println("5. Set a Discount");
		System.out.println("6. Remove a Sale");
		System.out.println("7. Set a Bulk Discount");
		System.out.println("8. Remove a Bulk Discount");
		System.out.println("9. View Stock");
		System.out.println("10. View Supplier Details");
		System.out.println("11. Return to menu..");
		System.out.print("Select Option: " );
		try {
			input = stringCheck("Select Option: ", input);

			int choice = Integer.parseInt(input);
			System.out.println();
			int productID = 0;
			String productName = null;
			String changeName = null;
			String changeDetails = null;
			double changeDiscount = 0;
			int changeAmount = 0;
			double changeBulkDiscount = 0;
			boolean success = false;
			switch(choice) {

			case 1: //Add Product

				System.out.print("Enter Product ID: ");
				input = stringCheck("Enter Product ID: ", input);
				productID = Integer.parseInt(input);


				System.out.print("Enter Product NAME: ");
				productName = null;
				productName = stringCheck("Enter Product NAME: ", productName);


				System.out.print("Enter Product PRICE: ");
				input = stringCheck("Enter Product PRICE: ", input);
				double productPrice = Double.parseDouble(input);

				System.out.print("Enter Product STOCK level: ");
				input = stringCheck("Enter Product STOCK level: ", input);
				int productStock = 0;
				productStock= Integer.parseInt(input);

				System.out.print("Enter Supplier Name: ");
				input = stringCheck("Enter Supplier Name: ", input);
				String supplierName = null;
				supplierName = input;



				productList.add(new Product(productID, productName , productPrice, productStock, supplierName));
				System.out.println("You made a new Product: ID = " + productID + ", Name = " + productName + ", Price = " + productPrice + ", Stock = " + productStock);
				iom.saveProducts(productList);

				break;
			case 2: //Change Product Name

				System.out.print("Enter the ID of product for NAME change: ");
				input = stringCheck("Enter the ID of product for NAME change: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						System.out.println("Enter the new name");
						changeName = stringCheck("Enter the new NAME: ", changeName);
						eaProduct.setProductName(changeName);
						System.out.println("NAME Succesfully Changed!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;
			case 3: //Change Product Price
				System.out.print("Enter the ID of product for PRICE change: ");
				input = stringCheck("Enter the ID of product for PRICE change: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						System.out.println("Enter the new PRICE");
						double changePrice = 0;
						input = stringCheck("Enter the new Price: ", input);
						changePrice = Double.parseDouble(input);
						eaProduct.setProductPrice(changePrice);
						System.out.println("NAME Succesfully Changed!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;

			case 4: //Change Product Details
				System.out.print("Enter the ID of product for DETAILS change: ");
				input = stringCheck("Enter the ID of product for DETAILS change: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						System.out.println("Enter the new DETAILS");
						input = stringCheck("Enter the new DETAILS: ", input);
						changeDetails = input;
						eaProduct.setProductDetails(changeDetails);
						System.out.println("DETAILS Succesfully Changed!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;

			case 5: //Set a discount
				System.out.print("Enter the ID of product to set DISCOUNT: ");
				input = stringCheck("Enter the ID of product to set DISCOUNT: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						System.out.println("Enter the new discount as a number between 0 and 1");
						input = stringCheck("Enter the new discount as a number between 0 and 1 ", input);
						changeDiscount = Double.parseDouble(input);
						eaProduct.setDiscountPrice(changeDiscount);
						System.out.println("DISCOUNT Succesfully Changed!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;
			case 6: //Remove a Discount
				System.out.print("Enter the ID of product to REMOVE DISCOUNT: ");
				input = stringCheck("Enter the ID of product to REMOVE DISCOUNT: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						productList.get(productID).setDiscountPrice(1);
						System.out.println("DISCOUNT Succesfully REMOVED!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;
			case 7: //Add a bulk discount
				System.out.print("Enter the ID of product to set BULK DISCOUNT: ");
				input = stringCheck("Enter the ID of product to set BULK DISCOUNT: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						System.out.println("Enter the amount needed to trigger the bulk discount");
						input = stringCheck("Enter the amount needed to trigger the bulk discount", input);
						changeAmount = Integer.parseInt(input);

						System.out.println("Enter the new discount (the products will be reduced by this flat number)");
						input = stringCheck("Enter the new discount (the products will be reduced by this flat number)", input);
						changeBulkDiscount = Double.parseDouble(input);

						eaProduct.setBulkAmount(changeAmount);
						eaProduct.setbulkDiscount(changeBulkDiscount);
						System.out.println("DISCOUNT Succesfully Changed!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;
			case 8://remove a bulk discount
				System.out.print("Enter the ID of product to REMOVE BULK DISCOUNT: ");
				input = stringCheck("Enter the ID of product to REMOVE BULK DISCOUNT: ", input);
				productID = Integer.parseInt(input);
				for (Product eaProduct : productList) {
					if (eaProduct.getProductId() == productID) {
						eaProduct.setBulkAmount(0);
						eaProduct.setbulkDiscount(0);
						System.out.println("BULK DISCOUNT Succesfully REMOVED!");
						success = true;
					}
				}
				if (!success) {
					System.out.println("Product does not exist..");
				}
				else {
					iom.saveProducts(productList);
				}
				break;
			case 9:
				displayProductStock();
				break;
			case 10:
				for (Product eaProduct: productList){
					System.out.println(eaProduct.getSupplierName());
				}
				break;
			case 11:
				backToMenu();
				break;
			default:
				System.out.println("Please select a valid choice!\n");
				displayManagerMenu();
				break;

			}
			manageProducts();
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			manageProducts();
		}

	}


	// ***** MANAGER METHOD *****
	private void manageStaff() {
		System.out.println("-- - -- - -- - -- MANAGE STAFFS -- - -- - -- - --" );
		System.out.println("1. Add Staff");
		System.out.println("2. Change Staff Name");
		System.out.println("3. Change Staff Password");
		System.out.println("4. Change Staff Level");
		System.out.println("5. Return to menu..");

		System.out.print("Select Option: " );
		try {
			input = stringCheck("Select Option: ", input);

			int choice = Integer.parseInt(input);
			System.out.println();
			String staffID = null , staffPassword = null , staffName = null;
			String changeName = null;
			String changePassword = null;
			int staffLevel = 0;
			int changeLevel = 0;
			boolean bool = false;
			switch(choice) {

			case 1: //Add Staff
				do {
					System.out.print("Enter Staff ID (FORMAT: E000): ");
					staffID = stringCheck("Enter Staff ID (FORMAT: E000): ", staffID);
				}while (staffID.equals(null) || !staffID.toUpperCase().startsWith("E"));

				System.out.println("Enter Staff NAME");
				staffName = stringCheck("Enter Staff NAME: ", staffName);

				System.out.println("Enter Staff PASSWORD");
				staffPassword = stringCheck("Enter Staff PASSWORD: ", staffPassword);

				do {
					System.out.println("Enter Staff LEVEL\n \nSale Staff = 1 \nWarehouse = 2 \nManager = 3\n:");
					input = stringCheck("Enter Staff LEVEL: ", input);
					staffLevel = Integer.parseInt(input);
				}while (staffLevel > 1 || staffLevel < 3);

				employees.add(new Employee(staffID, staffName ,staffPassword, staffLevel));
				System.out.println("You made a new Employee: \nID = " + staffID + "\nName = " + staffName + "\nPassword = " + staffPassword + "\n1Level = " + staffLevel);

				try {
					iom.saveEmployees(employees);
				} catch (IOException e) {
					System.out.println("Failed to load from menu.");
				}
				break;
			case 2: //Change Staff Name
				changeName = null;
				System.out.print("Enter the ID of staff for NAME change: ");
				staffID = stringCheck("Enter the ID of staff for NAME change: ", staffID);

				for (Employee eaEmployee : employees) {
					if (eaEmployee.GetID().equals(staffID)) {
						System.out.print("Enter the new NAME: ");
						changeName = stringCheck("Enter the new NAME: ", changeName);
						eaEmployee.SetName(changeName);
						System.out.println("NAME Succesfully Changed!");
						bool = true;
					}
				}
				if (bool) {
					try {
						iom.saveEmployees(employees);
					} 
					catch (IOException e) {
						System.out.println("Failed to load from menu.");
					}
				}
				else {
					System.out.println("Staff does not exist..");
				}
				break;
			case 3: //Change Staff Password
				System.out.print("Enter the ID of staff for PASSWORD change: ");
				staffID = stringCheck("Enter the ID of staff for PASSWORD change: ", staffID);

				for (Employee eaEmployee : employees) {
					if (eaEmployee.GetID().equals(staffID)) {
						System.out.print("Enter the new PASSWORD: ");
						changePassword = stringCheck("Enter the new PASSWORD: ", changePassword);
						eaEmployee.SetPassword(changePassword);
						System.out.println("PASSWORD Succesfully Changed!");
						bool = true;
					}
				}
				if (bool) {
					try {
						iom.saveEmployees(employees);
					} catch (IOException e) {
						System.out.println("Failed to load from menu.");
					}
				}
				else {
					System.out.println("Staff does not exist..");
				}
				break;
			case 4: //Change Staff Level
				System.out.print("Enter the ID of staff for LEVEL change: ");
				staffID = stringCheck("Enter the ID of staff for LEVEL change: ", staffID);

				for (Employee eaEmployee : employees) {
					if (eaEmployee.GetID().equals(staffID)) {
						System.out.print("Enter the new LEVEL: ");
						input = stringCheck("Enter the new LEVEL: ", input);
						changeLevel = Integer.parseInt(input);
						eaEmployee.SetLevel(changeLevel);
						System.out.println("LEVEL Succesfully Changed!");
						bool = true;
					}
				}
				if (bool) {
					try {
						iom.saveEmployees(employees);
					} catch (IOException e) {
						System.out.println("Failed to load from menu.");
					}
				}
				else {
					System.out.println("Staff does not exist..");
				}
				break;
			case 5:
				backToMenu();
				break;
			default:
				System.out.println("Please select a valid choice!\n");
				displayManagerMenu();
				break;

			}
			manageStaff();
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			manageStaff();
		}
	}

	// ***** MANAGER METHOD *****
	private void displayReportMenu() {
		System.out.println("-- - -- - -- - -- REPORT MENU -- - -- - -- - --" );
		System.out.println("1. View |Fastest Moving Item (base on value)| Report");
		System.out.println("2. View |Time Specific| Report");
		System.out.println("3. View |Location| Report");
		System.out.println("4. View |Most Popular Items| Report");
		System.out.println("5. View |Supply| Report");
		System.out.println("6. Return to menu..");

		System.out.print("Select Option: ");
		try {
			input = stringCheck("Select Option: ", input);

			Report report = new Report(productList, customers);
			int choice = Integer.parseInt(input);
			System.out.println();
			switch(choice) {

			case 1:
				report.getRevenuePRReport();

				break;
			case 2:
				try {
					report.getPeriodReport();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				report.getPostcodeReport();
				break;
			case 4:
				report.getItemOPReport();
				break;
			case 5:
				report.getSupplyReport();
				break;
			case 6:
				backToMenu();
				break;
			default:
				System.out.println("Please select a valid choice!\n");
				displayReportMenu();
				break;
			}
			displayReportMenu();
		}
		catch (NumberFormatException e) {
			System.out.println("Please input a number only..");
			displayReportMenu();
		}
	}

}
