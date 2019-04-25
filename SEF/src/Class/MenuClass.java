package Class;

import java.util.*;

public class MenuClass {

	Scanner scn = new Scanner(System.in);
	private String id;
	private String password;

	public void login() {
		System.out.println("LOGIN");
		System.out.print("Input ID: ");
		id = scn.nextLine();
		System.out.print("Input Password: ");
		password = scn.nextLine();
	}

	public void customerMenu() {

		System.out.println("Customer Main menu" );
		System.out.println("1. View Product List");
		System.out.println("2. View Cart" );
		System.out.println("3. Remove Subscription");
		System.out.println("4. Check Loyalty Points");

	}

	//	String id = cust.getID();
	//	if( id.equals("1234") ) {
	//		displayCustomerMenu();

	/*else if() {
            	 displaySalesMenu();

             }
             else if() {
            	 displayManagerMenu();

             }*/

	//
	//
	//	}
	//
	//	public void displayCustomerMenu( )
	//	{
	//
	//		System.out.println("Customer Main menu" );
	//		System.out.println( "1. View Product List" );
	//		System.out.println( "2. View Cart" );
	//		System.out.println("3. Remove Subscription" );
	//		System.out.println("4. Check Loyalty Points" );
	//
	//		System.out.print("Enter Choice : " );
	//		choice = kb.nextInt( );
	//		kb.nextLine( );
	//
	//		switch(choice) {
	//
	//		case 1:
	//			displayProductListMenu();
	//			break;
	//		case 2:
	//
	//			break;
	//		case 3:
	//
	//			break;
	//		case 4:
	//
	//			break;
	//
	//		default:
	//			System.out.println("Choice : "+choice+" Is an invalid choice");
	//			displayCustomerMenu();
	//			break;
	//
	//		}
	//
	//
	//	}
	//
	//	public void displayProductListMenu( )
	//	{
	//		for(Product product : products) {
	//			System.out.println(product);
	//
	//		}
	//
	//	}
	//
	//	public void displaySalesMenu( )
	//	{
	//		System.out.println("Sales Assistant Main menu" );
	//		System.out.println( "1. Remove item from sale " );
	//		System.out.print("Enter Choice : " );
	//		choice = kb.nextInt( );
	//		kb.nextLine( );
	//		switch(choice) {
	//
	//		case 1:
	//
	//			break;
	//		default:
	//			System.out.println("Choice : "+choice+" Is an invalid choice");
	//
	//			displaySalesMenu();
	//			break;
	//
	//		}
	//
	//
	//	}
	//
	//	public void displayManagerMenu()
	//	{
	//
	//		System.out.println("Manager Main menu" );
	//		System.out.println( "1. Access Report Menu" );
	//		System.out.println( "2. Manage Staff" );
	//		System.out.println("3. Manage Sales" );
	//		System.out.print("Enter Choice : " );
	//		choice = kb.nextInt( );
	//		kb.nextLine( );
	//		switch(choice) {
	//
	//		case 1:
	//			displayReportMenu();
	//			break;
	//		case 2:
	//			manageStaff();
	//			break;
	//		case 3:
	//			manageSale();
	//			break;
	//
	//		default:
	//			System.out.println("Choice : "+choice+" Is an invalid choice");
	//			displayManagerMenu();
	//			break;
	//
	//		}
	//
	//
	//	}
	//
	//
	//	private void manageSale() {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//
	//	private void manageStaff() {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//
	//	private void displayReportMenu() {
	//		// TODO Auto-generated method stub
	//		System.out.println("Manager Main menu" );
	//		System.out.println( "1. Access Report Menu" );
	//		System.out.println( "2. Manage Staff" );
	//		System.out.println("3. Manage Sales" );
	//
	//	}
	//
}
