package Class;
import java.util.*;

public class MainSEF {
	
	boolean newRun = true;
	Scanner scn = new Scanner(System.in);
	private static final int MANAGER = 3;
	private static final int WAREHOUSE = 2;
	private static final int SALESTAFF = 1;
	private static final int CART_MAX = 50;
	private String id;
	private String password;
	Product[] productList = new Product[20];
	Customer[] customers = new Customer[50];
	Employee[] employees = new Employee[20];
	Customer currentCustomer = null;
	Employee currentEmployee = null;

	public void run() {

		demoInitialise();
		login();	
	}


	private void demoInitialise() //Used for DEMO
	{
		Product apple = new Product(1, "Apple", 0.89, 100);
		Product biscuits = new Product(2, "ANZAC biscuits", 3.99, 100);
		Product mints = new Product(3, "Eclipse Spearmint Suger", 1.99, 100);
		Product pen = new Product(4, "Four colours Ball pen", 1.5, 100);
		Product notebook = new Product(5, "A5 Notebook", 0.79, 100);
		Product milk = new Product(6, "Pure milk", 2, 100);
		Product bread = new Product(7, "Sandwich bread", 0.99, 100);
		Product chicken = new Product(8, "Drumstick", 9, 100);
		Product broccoli = new Product(9, "Broccoli", 0.69, 100);
		Product pasta = new Product(10, "Delicious pasta", 0.89, 100);
		productList[0] = apple;
		productList[1] = biscuits;
		productList[2] = mints;
		productList[3] = pen;
		productList[4] = notebook;
		productList[5] = milk;
		productList[6] = bread;
		productList[7] = chicken;
		productList[8] = broccoli;
		productList[9] = pasta;


		Employee manager = new Employee("e1", "Tom", "password1", MANAGER);
		Employee warehouse = new Employee("e2", "Dick", "password2", WAREHOUSE);
		Employee salestaff = new Employee("e3", "Harry", "password3", SALESTAFF);

		employees[0] = manager;
		employees[1] = warehouse;
		employees[2] = salestaff;

		Customer one = new Customer("c001", "Will", 3050);
		Customer two = new Customer("c002", "Jack", 3450);

		customers[0] = one;
		customers[1] = two;

		one.calculatePoints(150); //spent 150 dollars get 15 points


	}




	public void login() {
		if (newRun) {
		System.out.println("********** WELCOME **********");
		}
		System.out.print("Enter ID: ");

		id = scn.nextLine();
		while (id.equals("")) {
			System.out.print("Enter ID: ");
			id = scn.nextLine();
		}
		//EXTRA for testing
		if (id.equalsIgnoreCase("quit")) {
			System.exit(0);
		}
		/* Doesn't start with 'c' means its employee */
		if (id.charAt(0) != 'c') {
			if (employees[0] == null) {
				System.out.println("Error: No Employees Registered");
				System.out.println();
				login();
			}
			else {
				System.out.print("Enter Password: ");
				password = scn.nextLine();
				while (id.equals("")) {
					System.out.print("Enter Password: ");
					id = scn.nextLine();
				}
			}
			//Account Validation
			for (int i = 0; i < employees.length; i++) {
				if (employees[i] != null) {
					if (employees[i].GetID().equals(id) && employees[i].GetPassword().equals(password)) {
						System.out.println("*Log in Succuessful*" + "\n");
						currentEmployee = employees[i];
					}
				}
			}
			if (currentEmployee == null) {

				System.out.println("Invalid login, please try again!");
				System.out.println();
				login();
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
		else { 
			for (int i = 0; i < customers.length; i++) {
				if (customers[i] != null) {
					if (customers[i].getID().equals(id)) {
						currentCustomer = customers[i];
						currentCustomer.logIn();					 //Customer visit count increased here
						System.out.println();
						newRun = false;
						displayCustomerMenu();
					}
				}
			}
			if (currentCustomer == null) {
				System.out.println("Customer ID doesn't exist..");
				System.out.println();
				login();
			}
		}
	}




	private void backToMenu()
	{
		System.out.println("Press ENTER to return to the Main Menu");
		scn.nextLine();

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





	private void displayCustomerMenu() {

		System.out.println("***** MAIN MENU *****" );
		System.out.println("Current Loyalty Points: " + currentCustomer.getPoint() + "\n");
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

		case 1: //1. View Product List
			displayProductListMenu();
			System.out.println();
			addProductInCart();
			break;
		case 2: //2. View Cart
			displayCart();
			backToMenu();
			break;
		case 3: //3. Subscription
			currentCustomer.subscribe();
			backToMenu();
			break;
		case 4: //3. Subscription
			checkOut();
			break;
		case 5: //4. Quit
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.out.println("|See you soon! : D|");
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.exit(0);
			

		default:
			System.out.println("Please select a valid choice!\n");
			displayCustomerMenu();
			break;

		}

	}

	/* Method that Just Displays a Menu */
	private void displayProductListMenu() {

		System.out.println("***** PRODUCTS *****");
		for(int i = 0; i < productList.length; i++) {
			if (productList[i] != null) {
				String product = (i+1) + ". " + productList[i].getProductName();
				double price = productList[i].getProductPrice();
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

					if(prodN < 1 || prodN > productList.length) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					if (productList[prodN - 1] == null) {
						throw new InputMismatchException("Please enter a valid input for your select product.");
					}
					//Need to insert out of stock 
					else {
						currentCustomer.addProduct(productList[prodN - 1]);
						displayProductListMenu();
						System.out.println();
						System.out.println("Item added successfully!");
						System.out.println("Total number of '" + productList[prodN - 1].getProductName() 
								+ "' in cart: " 
								+ productList[prodN - 1].getNumberInCart()
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
		String left, right, cart  = "";

		System.out.println("What's in your cart: \n");
		for (int i = 0; i < CART_MAX; i++)
		{
			if (currentCustomer.getCart()[i] != null)
			{
				left = (currentCustomer.getCart()[i].getProductName());
				right = Integer.toString(currentCustomer.getCart()[i].getNumberInCart());
				cart += String.format("%-25s %s\n", left, right);
			}
		}
		if (cart.equals(""))
		{
			System.out.println("Opps, it seems like you haven't add anything yet.");
		}
		else
		{
			System.out.printf("%-25s %s", "NAME", "NUMBER OF ITEMS" + "\n");
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

		//currentCustomer.clear to add in customer class
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





	private void displayWarehouseMenu() {
		System.out.println("***** WAREHOUSE MENU *****" );
		System.out.println("1. View stock level");
		System.out.println("2. Replenish the stock level" );
		System.out.println();
		System.out.println("3. Quit");
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

		case 1: //1. View stock level
			displayProductStock();
			System.out.println();
			backToMenu();
			break;
		case 2: //2. Replenish stock level
			replenishStock();
			System.out.println();
			backToMenu();
			break;
		case 3: //4. Quit
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.out.println("|See you soon! : D|");
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.exit(0);
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			System.out.println();
			backToMenu();
			break;
		}
	}

	private void displayProductStock()
	{
		System.out.printf("%-30s %s %30s\n", "ITEM NAME" , "ID" ,"CURRENT STOCK" );

		for(int i = 0; i < productList.length; i++) {
			if (productList[i] != null) {
				String product = (i+1) + ". " + productList[i].getProductName();
				int stock = productList[i].getProductStock();
				int id = productList[i].getProductId();
				String menu = String.format("%-30s %s %25d", product, id, stock);
				System.out.println(menu);
			}
		}
	}

	private void replenishStock()
	{
		System.out.print("Input the ID of the item you'd like to replenish: ");
		int input = Integer.parseInt(scn.nextLine());
		for(int i = 0; i < productList.length; i++) {
			if (productList[i] != null) {
				if (productList[i].getProductId() == input) {
					System.out.println('\n' + "ID: " + input + " = " + productList[i].getProductName());
					System.out.print("Input amount of stock to replenish: ");
					int input1 = Integer.parseInt(scn.nextLine());
					productList[i].increaseStock((input1));
					System.out.println(productList[i].getProductName() + "'s stock successfully increased by " + input1);
				}
				else {
					System.out.println("Invald product ID, please try again.");
					replenishStock();
				}
			}
		}
	}

	public void displaySalesMenu( ) {
		System.out.println("saleme u");
	}
	//	h    {
	//	         System.out.println("Sales Assistant Main menu" );
	//	         System.out.println( "1. Remove item from sale " );
	//	         System.out.print("Enter Choice : " );
	//             choice = kb.nextInt( );
	//             kb.nextLine( );
	//             switch(choice) {
	//             
	//             	case 1:
	//             		
	//            	 break;
	//             	default:
	//             		System.out.println("Choice : "+choice+" Is an invalid choice");
	//             		
	//             		displaySalesMenu();
	//            	 break;
	//            	 
	//             }
	//	        
	//	         
	//	    }
	//	 
	public void displayManagerMenu()
	{

		System.out.println("***** MANAGER MENU *****");
		System.out.println("1. Display Report Menu");
		
		System.out.println("2. Manage Staff");
		System.out.println("3. Manage Sales");

		System.out.print("Select Option: " );
		int choice = Integer.parseInt(scn.nextLine());
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

		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}	         
	}


	private void manageProducts() {
		System.out.println("1. Add Product");
		System.out.println("2. Change Product Name");
		System.out.println("3. Change Product Price");
		System.out.println("4. Change Product Details");
		System.out.println("5. Set a Discount");
		System.out.println("6. Remove a Sale");
		System.out.println("7. Set a Bulk Discount");
		System.out.println("8. Remove a Bulk Discount");
		System.out.println("9. Auto Ordering");
		System.out.print("Select Option: " );
		int choice = Integer.parseInt(scn.nextLine());
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
			
			i = 0;
			while(productList[i] != null){
				i++;
			}
			productList[i] = new Product(productID, productName , productPrice, productStock);
			System.out.println("You made a new Product: ID = " + productID + ", Name =" + productName + ", Price = " + productPrice + ", Stock = " + productStock);
			break;
		case 2: //Change Product Name
//needs error prevention
			System.out.println("Enter the ID of the product you want to change the name of");
			productID = Integer.parseInt(scn.nextLine());
			System.out.println("Enter the new name");
			String changeName = scn.nextLine();
			i = 0;
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setProductName(changeName);
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
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setProductPrice(changePrice);
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
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setProductDetails(changeDetails);
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
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setDiscountPrice(changeDiscount);
				}
				i++;
			}
			break;			
		case 6: //Remove a Discount
			System.out.println("Enter the ID of the product you want to remove the discount for");
			productID = Integer.parseInt(scn.nextLine());
			i=0;
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setDiscountPrice(1);
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
			while(i < productList.length){
				if(productID == productList[i].getProductId()){
					productList[i].setBulkAmount(changeAmount);
					productList[i].setbulkDiscount(changeBulkDiscount);
				}
				i++;
			}
			break;
		case 8://remove a bulk discount
			System.out.println("Enter the ID of the product you want to remove the bulk discount for");
			productID = Integer.parseInt(scn.nextLine());
			i=0;
			while(productList[i] != null){
				if(productID == productList[i].getProductId()){
					productList[i].setBulkAmount(0);
					productList[i].setbulkDiscount(0);
				}
				i++;
			}
			break;
		case 9://auto ordering
			break;
		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}	
		

	}


	private void manageStaff() {
		System.out.println("1. Add Staff");
		System.out.println("2. Change Staff Name");
		System.out.println("3. Change Staff Password");
		System.out.println("4. Change Staff Level");
		System.out.print("Select Option: " );
		int choice = Integer.parseInt(scn.nextLine());
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
			while(employees[i] != null){
				i++;
			}
			employees[i] = new Employee(staffID, staffName ,staffPassword, staffLevel);
			System.out.println("You made a new Employee: ID = " + staffID + ", Name =" + staffName + ", Password = " + staffPassword + ", Level = " + staffLevel);
			break;
		case 2: //Change Staff Name
//needs error prevention
			System.out.println("Enter the ID of the staff you want to change the name of");
			staffID = scn.nextLine();
			System.out.println("Enter the new name");
			String changeName = scn.nextLine();
			i = 0;
			while(employees[i] != null){
				if(staffID == employees[i].GetID()){
					employees[i].SetName(changeName);
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
			while(employees[i] != null){
				if(staffID == employees[i].GetID()){
					employees[i].SetPassword(changePassword);
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
			while(employees[i] != null){
				if(staffID == employees[i].GetID()){
					employees[i].SetLevel(changeLevel);
				}
				i++;
			}
			break;
		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}	

	}


	private void displayReportMenu() {
		System.out.println("*** REPORT MENU ***");
		System.out.println("1. ...REPORT");
		System.out.println("1. ...REPORT");
		System.out.println("1. ...REPORT");

	}
}
