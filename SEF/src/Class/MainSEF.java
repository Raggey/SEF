package Class;
import java.util.*;

public class MainSEF {

	boolean newRun = true;
	Scanner scn = new Scanner(System.in);
	private static final int MANAGER = 3;
	private static final int WAREHOUSE = 2;
	private static final int SALESTAFF = 1;

	private String id;
	private String password;

	LinkedList<Employee> employees = new LinkedList<Employee>();
	LinkedList<Product> productList = new LinkedList<Product>();
	LinkedList<Customer> customers = new LinkedList<Customer>();
//	IOmachine iom = new IOmachine();
	Customer currentCustomer = null;
	Employee currentEmployee = null;

	public void run() {
		
		//loadSystemData();
		demoInitialise();
		login();	
	}

	private void loadSystemData() {
//		employees = iom.readIn ;
//		productList = iom ;
//		customers = iom ;
	}


	private void demoInitialise() //Used for DEMO
	{
		Product apple = new Product(1, "Apple", 0.89, 100,"sup1");
		Product biscuits = new Product(2, "ANZAC biscuits", 3.99, 100,"sup2");
		Product mints = new Product(3, "Eclipse Spearmint Suger", 1.99, 100,"sup2");
		Product pen = new Product(4, "Four colours Ball pen", 1.5, 100,"sup3");
		Product notebook = new Product(5, "A5 Notebook", 0.79, 100,"sup3");
		Product milk = new Product(6, "Pure milk", 2, 100,"sup2");
		Product bread = new Product(7, "Sandwich bread", 0.99, 100,"sup2");
		Product chicken = new Product(8, "Drumstick", 9, 100,"sup1");
		Product broccoli = new Product(9, "Broccoli", 0.69, 100,"sup2");
		Product pasta = new Product(10, "Delicious pasta", 0.89, 100,"sup2");


		productList.add(apple);
		productList.add(biscuits);
		productList.add(mints);
		productList.add(pen);
		productList.add(notebook);
		productList.add(milk);
		productList.add(bread);
		productList.add(chicken);
		productList.add(broccoli);
		productList.add(pasta);

		Employee manager = new Employee("e1", "Tom", "password1", MANAGER);
		Employee warehouse = new Employee("e2", "Dick", "password2", WAREHOUSE);
		Employee salestaff = new Employee("e3", "Harry", "password3", SALESTAFF);

		employees.add(manager);
		employees.add(warehouse);
		employees.add(salestaff);


		Customer one = new Customer("c001", "Will", 3050);
		Customer two = new Customer("c002", "Jack", 3450);

		customers.add(one);
		customers.add(two);

		one.calculatePoints(150); //spent 150 dollars get 15 points


	}




	public void login() {
		if (newRun) {
			System.out.println("********** WELCOME **********");
		}
		newRun = false;
		System.out.print("Enter ID: ");

		id = scn.nextLine();
		while (id.equals("")) {
			System.out.print("Enter ID: ");
			id = scn.nextLine();
		}

		// USED IN TESTING, MIGHT IMPLEMENT
		if (id.equalsIgnoreCase("quit")) {
			System.exit(0);
		}

		/* All EMPLOYEE ID start with 'e' */
		if (id.charAt(0) == 'e') {
			if (employees.isEmpty()) {
				System.out.println("ERROR: No Employees Registered!\nGOODBYE!!"  );
				System.exit(0);
			}
			else {
				System.out.print("Enter Password: ");
				password = scn.nextLine();
				while (password.equals("")) {
					System.out.print("Enter Password: ");
					password = scn.nextLine();
				}
			}


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
		else if (id.charAt(0) == 'c') { 
			if (validateCustomer()) {
				// Customer visit counter is increased here.
				currentCustomer.logIn();
				displayCustomerMenu();
			}
			else {
				System.out.println("Customer ID doesn't exist..\n");
				login();
			}

		}
		// if LOGIN failed
		else if (currentEmployee == null && currentCustomer == null) {
			System.out.println("Invalid login, please try again!\n");
			login();
		}
	}

	// Method to check who signed in. (currentEmployee)
	private boolean validateEmployee() {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i) != null) {
				if (employees.get(i).GetID().equals(id) && employees.get(i).GetPassword().equals(password)) {
					System.out.println("\n*** Log in Succuessful ***" + "\n");
					currentEmployee = employees.get(i);
					return true;
				}
			}
		}
		return false;
	}

	// Method to check which customer. (currentCustomer)
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


	private void backToMenu()
	{
		System.out.println("Press ENTER to return to the Main Menu");
		scn.nextLine();

		// Customer Priority 
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




	// ***** CUSTOMER METHOD *****
	private void displayCustomerMenu() {

		System.out.println("***** MAIN MENU *****" );
		System.out.printf("%s's Loyalty Points: %d\n", currentCustomer.getID(), currentCustomer.getPoint());
		System.out.println("1. View Product List");
		System.out.println("2. View Cart" );
		System.out.println("3. Toggle Subscription");
		System.out.println("4. Check Out");
		System.out.println();
		System.out.println("5. Quit");
		System.out.println();


		System.out.print("Select Option: " );
		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}
		int choice = Integer.parseInt(input);
		System.out.println();


		switch(choice) {

		case 1: 
			//1. View Product List
			displayProductListMenu();
			System.out.println();
			addProductInCart();
			break;
		case 2: 
			//2. View Cart
			displayCart();
			backToMenu();
			break;
		case 3: 
			//3. Subscription
			currentCustomer.subscribe();
			backToMenu();
			break;
		case 4: 
			//3. Subscription
			checkOut();
			break;
		case 5: //4. Quit
			quit();


		default:
			System.out.println("Please select a valid choice!\n");
			displayCustomerMenu();
			break;

		}

	}

	/* Method that Just Displays a Menu */
	private void displayProductListMenu() {

		System.out.println("***** PRODUCTS *****");
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
		int prodN = 0; 
		boolean quit = false;
		String answer = "";

		System.out.println("Select product to add to cart(enter the number) or press ENTER to exit.");
		while(!quit) {
			//
			answer = scn.nextLine();
			if (answer.isEmpty()) {
				displayCart();
				quit = true;
				backToMenu();
			}
			else if (answer.equalsIgnoreCase("remove")) {
				login();
			}
			else {
				try {
					prodN = Integer.parseInt(answer); 
					System.out.println();

					if(prodN < 1 || prodN > productList.size()) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					if (productList.get(prodN - 1) == null) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					//Need to insert out of stock 
					else {
						currentCustomer.addProduct(productList.get(prodN - 1));
						displayProductListMenu();
						System.out.println();
						System.out.println("Item added successfully!");
						System.out.println("Total number of '" + productList.get(prodN - 1).getProductName() 
								+ "' in cart: " 
								+ productList.get(prodN - 1).getNumberInCart()
								+ '\n');
						System.out.println("Select another product or press ENTER to exit (call staff for removal of items)");
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

		System.out.println("What's in your cart: ");
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
		String input = scn.nextLine();

		if (input.equalsIgnoreCase("Y")) {
			checkout.PerformSale();
			currentCustomer.emptyCart();
			System.out.println("Thank you for shopping with us.");
			System.out.println("Goodbye!");
		}
		else if(input.equalsIgnoreCase("N")) {
			System.out.println("Have a Nice Day!");
		}
		else {
			System.out.println("Sorry invalid input" + '\n');
			checkOut();
		}

		currentCustomer.emptyCart();
		currentCustomer = null;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" " + '\n' + '\n' + '\n' + '\n' + '\n' + '\n' + '\n');
		login();
	}

	// ***** WAREHOUSE STAFF METHOD *****
	private void displayWarehouseMenu() {
		System.out.println("***** WAREHOUSE MENU *****" );
		System.out.println("1. View Stock Level");
		System.out.println("2. Replenish Stock Level" );
		System.out.println("3. Logout (Re-Login)\n");
		System.out.println("4. Quit\n");

		System.out.print("Select Option: ");

		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

		int choice = Integer.parseInt(input);
		System.out.println();
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
			break;
		default:
			System.out.println("Please select a valid choice!\n");
			displayWarehouseMenu();
			break;
		}
	}

	// ***** WAREHOUSE STAFF METHOD *****
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
		backToMenu();
	}

	// ***** WAREHOUSE STAFF METHOD *****
	private void replenishStock() {
		String productName, input = "";
		int productId, replenishAmount = 0;

		System.out.print("Input the ID of the item you'd like to replenish or enter 'q' to cancel: ");

		input = scn.nextLine();
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


	// ***** SALES STAFF METHOD *****
	public void displaySalesMenu( ) {
		System.out.println("*** Welcome " + currentEmployee.GetName() + " ***");
		System.out.println("1. Remove Item");
		System.out.println("2. Stop Removing Items\n");
		System.out.println("3. Quit\n");

		System.out.print("Select Option: ");

		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

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
			backToMenu();
			break;
		case 3:
			quit();
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displaySalesMenu();
			break;

		}
	}

	// ***** SALES STAFF METHOD *****
	public void removeItem() {

		boolean removed = false;
		String input = "";
		int inputId, removeAmount = 0;
		Product[] customerCart = currentCustomer.getCart();

		displayCart();

		System.out.println("Enter ID of item to remove or enter 'q' to cancel: ");
		input = scn.nextLine();
		// null check
		while (input.equals("")) { 
			input = scn.nextLine();
		}
		// to quit removing items
		if (input.equals("q")) {
			backToMenu();
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
				backToMenu();
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
		currentCustomer = null;
		currentEmployee = null;
		newRun = true;
		login();
		id = "";
		password = "";
		// set everything to null 
		// start a new 
		System.out.println("TODO");
	}
	// ***** MANAGER METHOD *****
	public void displayManagerMenu()
	{

		System.out.println("***** MANAGER MENU *****");
		System.out.println("1. Display Report Menu");
		System.out.println("2. Manage Staff");
		System.out.println("3. Manage Sales");
		System.out.println("4. Auto Ordering");
		System.out.println("5. Logout (Re-Login)\n");
		System.out.println("6. Quit\n");

		System.out.print("Select Option: " );
		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

		int choice = Integer.parseInt(input);
		System.out.println();
		switch(choice) {

		case 1:
			displayReportMenu();
			backToMenu();
			break;
		case 2:
			manageStaff();
			backToMenu();
			break;
		case 3:
			manageProducts();
			backToMenu();
			break;
		case 4:
			autoOrdering();
			break;
		case 5:
			reLogin();
			break;
		case 6:
			quit();
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}	         
	}

	private void autoOrdering(){
			System.out.println("1. View current Auto Ordering");
			System.out.println("2. Set up new Auto Order");
			System.out.println("3. Remove an Auto Order");
			String input = scn.nextLine();
			while (input.equals("")) {
				System.out.print("Select Option: " );
				input = scn.nextLine();		
			}
			int choice = Integer.parseInt(input);
			System.out.println();
			int i = 0;
			int productID;
			switch(choice) {
			case 1: //view current auto order
				break;
			case 2: //set up new auto order
				System.out.println("Enter Product ID");
				productID = Integer.parseInt(scn.nextLine());
				System.out.println("Enter amount it should top up to");
				
				break;
			case 3: //remove auto order
				System.out.println("Enter Product ID");
				productID = Integer.parseInt(scn.nextLine());
				break;
			default:
				System.out.println("Please select a valid choice!\n");
				displayManagerMenu();
				break;
			}
		}
	
	// ***** MANAGER METHOD *****
	private void manageProducts() {
		System.out.println("1. Add Product");
		System.out.println("2. Change Product Name");
		System.out.println("3. Change Product Price");
		System.out.println("4. Change Product Details");
		System.out.println("5. Set a Discount");
		System.out.println("6. Remove a Sale");
		System.out.println("7. Set a Bulk Discount");
		System.out.println("8. Remove a Bulk Discount");
		System.out.println("9. Return to menu..");
		System.out.print("Select Option: " );
		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

		int choice = Integer.parseInt(input);
		System.out.println();
		int i = 0;
		int productID;
		switch(choice) {

		case 1: //Add Product
			System.out.println("Enter Product ID");
			productID = Integer.parseInt(scn.nextLine());

			System.out.println("Enter Product Name");
			String productName = scn.nextLine();

			System.out.println("Enter Product Price");
			double productPrice = Double.parseDouble(scn.nextLine());

			System.out.println("Enter Stock Level");
			int productStock = Integer.parseInt(scn.nextLine());
			
			System.out.println("Enter Supplier ID");
			String supplierID = scn.nextLine();
			i = 0;
			while(productList.get(i) != null){
				i++;
			}
			productList.add(new Product(productID, productName , productPrice, productStock, supplierID));
			System.out.println("You made a new Product: ID = " + productID + ", Name =" + productName + ", Price = " + productPrice + ", Stock = " + productStock);
			break;
		case 2: //Change Product Name
			//needs error prevention
			System.out.println("Enter the ID of the product you want to change the name of");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new name");
			String changeName = scn.nextLine();
			i = 0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setProductName(changeName);
				}
				i++;
			}
			break;
		case 3: //Change Product Price
			//needs error prevention
			System.out.println("Enter the ID of the product you want to change the price of");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new price");
			double changePrice = Double.parseDouble(scn.nextLine());
			i = 0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setProductPrice(changePrice);
				}
				i++;
			}
			break;
		case 4: //Change Product Details
			//needs error prevention
			System.out.println("Enter the ID of the product you want to change the details of");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new details");
			String changeDetails = scn.nextLine();
			i = 0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setProductDetails(changeDetails);
				}
				i++;
			}
			break;
		case 5: //Set a discount
			//needs error prevention
			System.out.println("Enter the ID of the product you want to set a discount for");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new discount as a number between 0 and 1");
			double changeDiscount = Double.parseDouble(scn.nextLine());
			i = 0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setDiscountPrice(changeDiscount);
				}
				i++;
			}
			break;			
		case 6: //Remove a Discount
			System.out.println("Enter the ID of the product you want to remove the discount for");
			productID = Integer.parseInt(scn.nextLine());
			i=0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setDiscountPrice(1);
				}
				i++;
			}
			break;
		case 7: //Add a bulk discount
			//needs error prevention
			System.out.println("Enter the ID of the product you want to set a bulk discount for");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the amount needed to trigger the bulk discount");
			int changeAmount = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new discount (the products will be reduced by this flat number)");
			double changeBulkDiscount = Double.parseDouble(scn.nextLine());
			i = 0;
			while(i < productList.size()){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setBulkAmount(changeAmount);
					productList.get(i).setbulkDiscount(changeBulkDiscount);
				}
				i++;
			}
			break;
		case 8://remove a bulk discount
			System.out.println("Enter the ID of the product you want to remove the bulk discount for");
			productID = Integer.parseInt(scn.nextLine());
			i=0;
			while(productList.get(i) != null){
				if(productID == productList.get(i).getProductId()){
					productList.get(i).setBulkAmount(0);
					productList.get(i).setbulkDiscount(0);
				}
				i++;
			}
			break;
		case 9:
			backToMenu();
			break;
		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}	


	}

	// ***** MANAGER METHOD *****
	private void manageStaff() {
		System.out.println("1. Add Staff");
		System.out.println("2. Change Staff Name");
		System.out.println("3. Change Staff Password");
		System.out.println("4. Change Staff Level");
		System.out.println("5. Return to menu..");

		System.out.print("Select Option: " );
		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

		int choice = Integer.parseInt(input);
		System.out.println();
		int i = 0;
		String staffID;
		switch(choice) {

		case 1: //Add Staff
			System.out.println("Enter StaffID");
			staffID = scn.nextLine();

			System.out.println("Enter Staff Name");
			String staffName = scn.nextLine();

			System.out.println("Enter StaffPassword");
			String staffPassword = scn.nextLine();

			System.out.println("Enter Level. Sale Staff = 1, Warehouse = 2, Manager = 3");
			int staffLevel = Integer.parseInt(scn.nextLine());

			i = 0;
			while(employees.get(i) != null){
				i++;
			}
			employees.add(new Employee(staffID, staffName ,staffPassword, staffLevel));
			System.out.println("You made a new Employee: ID = " + staffID + ", Name =" + staffName + ", Password = " + staffPassword + ", Level = " + staffLevel);
			break;
		case 2: //Change Staff Name
			//needs error prevention
			System.out.println("Enter the ID of the staff you want to change the name of");
			staffID = scn.nextLine();
			System.out.println("Enter the new name");
			String changeName = scn.nextLine();
			i = 0;
			while(employees.get(i) != null){
				if(staffID == employees.get(i).GetID()){
					employees.get(i).SetName(changeName);
				}
				i++;
			}
			break;
		case 3: //Change Staff Password
			//needs error prevention
			System.out.println("Enter the ID of the staff you want to change the password of");
			staffID = scn.nextLine();
			System.out.println("Enter the new password");
			String changePassword = scn.nextLine();
			i = 0;
			while(employees.get(i) != null){
				if(staffID == employees.get(i).GetID()){
					employees.get(i).SetPassword(changePassword);
				}
				i++;
			}
			break;
		case 4:
			//needs error prevention
			System.out.println("Enter the ID of the staff you want to change the level of");
			staffID = scn.nextLine();
			System.out.println("Enter the new level");
			int changeLevel = Integer.parseInt(scn.nextLine());
			i = 0;
			while(employees.get(i) != null){
				if(staffID == employees.get(i).GetID()){
					employees.get(i).SetLevel(changeLevel);
				}
				i++;
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
	}

	// ***** MANAGER METHOD *****
	private void displayReportMenu() {
		System.out.println("***** REPORT MENU *****" );
		System.out.println("1. View |Fastest Moving Item (base on value)| Report");
		System.out.println("2. View |Time Specific| Report");
		System.out.println("3. View |Location| Report");
		System.out.println("4. View |Most Popular Items| Report");
		System.out.println("5. View |Supply| Report");
		System.out.println("6. Return to menu..");

		System.out.print("Select Option: ");

		String input = scn.nextLine();
		while (input.equals("")) {
			System.out.print("Select Option: " );
			input = scn.nextLine();
		}

		int choice = Integer.parseInt(input);
		System.out.println();
		switch(choice) {

		case 1:
			System.out.println("TODO");
			break;
		case 2: 
			System.out.println("TODO");
			break;
		case 3: 
			System.out.println("TODO");
			break;
		case 4: 
			System.out.println("TODO");
			break;
		case 5: 
			System.out.println("TODO");
			break;
		case 6: 
			backToMenu();
			break;
		default:
			System.out.println("Please select a valid choice!\n");
			displayReportMenu();
			break;
		}
	}
}
