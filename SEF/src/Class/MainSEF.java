package Class;
import java.util.*;

public class MainSEF {

	Scanner scn = new Scanner(System.in);
	private String id;
	private String password;
	Product[] productList = new Product[20];
	Customer[] customers = new Customer[50];
	Employee[] empolyees = new Employee[20];
	Customer currentCustomer = null;

	public void run() {

		currentCustomer = new Customer("c001", "Will", 3050);
		addTenProduct(); //For the sake of Demo
		login();	
	}


	private void addTenProduct() //Used for DEMO
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


	}




	public void login() {
		System.out.println("********** WELCOME **********");
		System.out.print("Enter ID: ");
		
		id = scn.nextLine();

		if (id.charAt(0) != 'c') {
			System.out.print("Enter Password: ");
			password = scn.nextLine();
			//Verify Password Done Later
			System.out.println();
			displayManagerMenu();
		}
		else { 
			//currentCustomer = CUSTOMERBASED ON ID ENTERED
			// If customer Exist if Customer dont exist
			
//			currentCustomer = currentCustomer.getCustomer(id);
			System.out.println();
			displayCustomerMenu();
		}

	}

	private void backToMenu()
	{
		try {
			System.out.println("Taking you back to the main menu...");
			System.out.println();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Fail to load waiting time.");
		}
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
		int choice = Integer.parseInt(scn.nextLine());
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
				System.out.println("\nWhat's in your cart so far:");
				displayCart();
				System.out.println("Press ENTER to continue..");
				scn.nextLine();
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
						System.out.println("Total number of " + productList[prodN - 1].getProductName() + " in cart: " + productList[prodN - 1].getNumberInCart());		
						System.out.println("Select another product or press ENTER to exit");
					}
				}
				catch(InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		//		int prodN = -1; // WHY IS THIS NEGATIVE ONE?
		//		boolean intN = false;
		//		boolean quit = false;
		//		boolean valid = false;
		//		String answer = "";
		//
		//		while(!quit)
		//		{
		//			System.out.println("\nPlease select the product you want to buy(enter the number).");
		//			while(!intN)
		//			{
		//				// Input control between 1 to 10 for now.
		//				try {
		//					prodN = scn.nextInt();
		//					if(prodN < 1 || prodN > 10)
		//					{
		//						throw new InputMismatchException("Please enter a valid input for your select product.\n");
		//					}
		//					else
		//					{
		//						intN = true;
		//					}
		//				}
		//				catch(InputMismatchException e)
		//				{
		//					System.out.println(e.getMessage());
		//				}
		//			}
		//			currentCustomer.addProduct(productList[prodN - 1]);
		//			System.out.println("Add item successfully! ");
		//			
		//			
		//			
		//			valid = false;
		//			System.out.println("Is there anything else you want to add to you shopping cart?(Y/N)");
		//			while(!valid)
		//			{
		//				try {
		//					//NEED HELP HERE!!!! RUN THE PROGRAM, SELECT A PRODUCT AND YOU WILL KNOW WHERE I NEED HELP!!! 
		//					answer = scn.nextLine();	
		//				}
		//				catch(InputMismatchException e){
		//					System.out.println("Please enter a valid input(Y/N):");
		//				}
		//				if (answer.equals("Y") || answer.equals("y"))
		//				{
		//					valid = true;
		//					intN = false;
		//				}
		//				else if(answer.equals("N") || answer.equals("n"))
		//				{
		//					quit = true;
		//					valid = true;
		//				}
		//				else
		//				{
		//					System.out.println("Please enter a valid input(Y/N):");
		//				}
		//				
		//			}
		//		}
		//		System.out.println("\nWhat's in your cart so far:");
		//		displayCart();
	}

	private void displayCart() { 
		//For this moment, the Cart just have 50 index
		//		String product = (i+1) + ". " + productList[i].getProductName();
		//		double price = productList[i].getProductPrice();
		//		String menu = String.format("%-30s %.2f", product, price);
		//		System.out.println(menu);
		String cart = "";
		String left = "";
		String right = "";
		int count = 0;
		for (int i = 0; i < 50; i++)
		{
			if (currentCustomer.getCart()[i] != null)
			{
				left = (currentCustomer.getCart()[i].getProductName() + "\n");
				right = Integer.toString(currentCustomer.getCart()[i].getNumberInCart()) + "\n";

				cart += (currentCustomer.getCart()[i].getProductName() + "\t\t" + currentCustomer.getCart()[i].getNumberInCart() + "\n");
				
				count++;
			}
		}
		if (cart.equals(""))
		{
			System.out.println("Opps, it seems like you haven't add anything yet.");
		}
		else
		{
//			System.out.printf("%-25s %s", "NAME", "NUMBER OF ITEMS" + "\n");
//			System.out.printf("%-25s %d", left, right);

			System.out.println("Name\t\tNumber");
			System.out.println(cart + "\n");
		}
	}

	private void checkOut() {
		Sale checkout = new Sale(currentCustomer);
		checkout.PerformSale();
		

	}


	//	 public void displaySalesMenu( )
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
