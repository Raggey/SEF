package Class;
import java.util.*;

public class MainSEF {

	Scanner scn = new Scanner(System.in);
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


		Employee manager = new Employee("e1", "Tom", "password1", 3);
		Employee warehouse = new Employee("e2", "Dick", "password2", 2);
		Employee salestaff = new Employee("e3", "Harry", "password3", 1);

		employees[0] = manager;
		employees[1] = warehouse;
		employees[2] = salestaff;

		Customer one = new Customer("c001", "Will", 3050);
		Customer two = new Customer("c002", "Jack", 3450);

		customers[0] = one;
		customers[1] = two;
		
		one.calculatePoints(100);



	}




	public void login() {
		System.out.println("********** WELCOME **********");
		System.out.print("Enter ID: ");

		id = scn.nextLine();
		while (id.equals("")) {
			System.out.print("Enter ID: ");
			id = scn.nextLine();
		}




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

				case 1: displaySalesMenu();
				break;

				case 2: displayWarehouseMenu();
				break;

				case 3: displayManagerMenu();
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
//		try {
//			System.out.println("Taking you back to the main menu...");
//			System.out.println();
//			Thread.sleep(2200);
//		} catch (InterruptedException e) {
//			System.out.println("Fail to load waiting time.");
//		}
		System.out.println("Press ENTER to return to the Main Menu");
		scn.nextLine();
		displayCustomerMenu();
	}

	private void displayCustomerMenu() {

		System.out.println("***** MAIN MENU *****" );
		System.out.println("Current Loyalty Points: " + currentCustomer.getPoint() + "\n");
		System.out.println("1. View Product List");
		System.out.println("2. View Cart" );
		System.out.println("3. Toggle Subscription");
		System.out.println("4. PROCEED TO CHECKOUT");
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
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displayCustomerMenu();
			break;

		}

	}

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

	private void addProductInCart()
	{
		int prodN = 0; 
		boolean quit = false;
		String answer = "";

		System.out.println("Select product to add to cart(enter the number) or press ENTER to exit.");

		while(!quit) {

			answer = scn.nextLine();
			if (answer.isEmpty()) {
				System.out.println("\nWhat's in your cart so far: \n");
				displayCart();
				backToMenu();
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
					else {
						currentCustomer.addProduct(productList[prodN - 1]);
						displayProductListMenu();
						System.out.println();
						System.out.println("Item added successfully!");
						System.out.println("Total number of '" + productList[prodN - 1].getProductName() + "' in cart: " + productList[prodN - 1].getNumberInCart());		
						System.out.println("Select another product or press ENTER to exit");
					}
				}
				catch(InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private void displayCart() { 
		//For this moment, the Cart just have 50 index

		String cart = "";
		String left = "";
		String right = "";
		for (int i = 0; i < 50; i++)
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

	private void checkOut() {
		Sale checkout = new Sale(currentCustomer);
		checkout.CheckPrice();
		System.out.println("Do you want to proceed? (Y/N)");
		
		String input = scn.nextLine();
		
		if (input.equals("Y")) {
		checkout.PerformSale();}
		else if(input.equals("N")) {
			backToMenu();
		}
		else {
			System.out.println("Sorry invalid input");
			checkOut();
		}
		login(); //needs to change

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
			displayWarehouseMenu();
			break;
		case 2: //2. Replenish stock level
			replenishStock();
			displayWarehouseMenu();
			break;
		case 3: //4. Quit
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.out.println("|See you soon! : D|");
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displayCustomerMenu();
			break;

		}

	}

	private void displayProductStock()
	{
		System.out.printf("%-30s %s\n", "ITEM NAME" , "STOCK" );

		for(int i = 0; i < productList.length; i++) {
			if (productList[i] != null) {
				String product = (i+1) + ". " + productList[i].getProductName();
				int stock = productList[i].getProductStock();
				String menu = String.format("%-30s %d", product, stock);
				System.out.println(menu);
			}
		}
	}

	private void replenishStock()
	{
		System.out.println("Input the ID of the item you'd like to replenish:");
		int input = scn.nextInt();
		scn.nextLine();
		for(int i = 0; i < productList.length; i++) {
			if (productList[i] != null) {
				if (productList[i].getProductId() == input) {
					System.out.println("Input the amount by which you want to increase stock of the item you'd like to replenish");
					int input1 = scn.nextInt();
					scn.nextLine();
					productList[i].increaseStock(input1);
				}
			}
		}
	}

	public void displaySalesMenu( ) {}
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
			break;
		case 2:
			manageStaff();
			break;
		case 3:
			manageSale();
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displayManagerMenu();
			break;

		}
		//	        
		//	         
	}


	private void manageSale() {
		// TODO Auto-generated method stub

	}


	private void manageStaff() {
		// TODO Auto-generated method stub

	}


	private void displayReportMenu() {
		System.out.println("*** REPORT MENU ***");
		System.out.println("1. ...REPORT");
		System.out.println("1. ...REPORT");
		System.out.println("1. ...REPORT");

	}

}
