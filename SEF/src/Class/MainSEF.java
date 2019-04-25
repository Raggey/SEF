package Class;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class MainSEF {

	Scanner scn = new Scanner(System.in);
	private String id;
	private String password;
	//	int choice;
	Product[] productList = new Product[20];
	Customer[] customers = new Customer[50];
	Employee[] empolyees = new Employee[20];
	Customer currentCustomer = new Customer("c001", "Will", 3050);

	public void run() {

		addTenProduct(); //For the sake of Demo
		login();	


	}


	private void addTenProduct()
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
		System.out.println("***** LOGIN *****");
		System.out.print("Input ID: ");
		id = scn.nextLine();

		if (id.charAt(0) != 'c') {
			System.out.print("Input Password: ");
			password = scn.nextLine();
			//Verify Password Done Later
			displayManagerMenu();
		}
		else {
			//currentCustomer = CUSTOMERBASED ON ID ENTERED
			displayCustomerMenu();
		}

	}

	private void displayCustomerMenu() {

		System.out.println("***** MAIN MENU *****" );
		System.out.println("Current Loyalty Points: " + currentCustomer.getPoint() + "\n");
		System.out.println("1. View Product List");
		System.out.println("2. View Cart" );
		System.out.println("3. Toggle Subscription");

		System.out.print("Select Option: " );
		int choice = Integer.parseInt(scn.nextLine());
		switch(choice) {

		case 1: //1. View Product List
			displayProductListMenu();
			break;
		case 2: //2. View Cart
			displayCart();
			break;
		case 3: //3. Subscription
			currentCustomer.subscribe();
			break;

		default:
			System.out.println("Please select a valid choice!\n");
			displayCustomerMenu();
			break;
		}

	}

	public void displayProductListMenu() {
		for(Product product : productList) {
			if (product != null) {
				System.out.println(product.getProductName());
			}
		}

	}
	
	public void displayCart() {
		
		System.out.println(currentCustomer.getCart());
	//	currentCustomer.getCart(); 
		
	}
	//	 
	//	 public void displaySalesMenu( )
	//	    {
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

		System.out.println("Manager Main menu" );
		System.out.println( "1. Access Report Menu" );
		System.out.println( "2. Manage Staff" );
		System.out.println("3. Manage Sales" );
		System.out.print("Enter Choice : " );
		int choice = scn.nextInt( );
		scn.nextLine( );
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
			System.out.println("Choice : "+choice+" Is an invalid choice");
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
		// TODO Auto-generated method stub
		System.out.println("Manager Main menu" );
		System.out.println( "1. Access Report Menu" );
		System.out.println( "2. Manage Staff" );
		System.out.println("3. Manage Sales" );

	}

}
