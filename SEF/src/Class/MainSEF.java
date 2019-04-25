package Class;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class MainSEF {
	Scanner kb = new Scanner(System.in);
	String User;
	String Pass;
	int choice;
//	ArrayList<Customer> customers = new ArrayList<Customer>();
//	Customer cust = new Customer("1234", "Saad Jaber", 3064);
//	Product product1 = new Product(1, "apple", 2.99);
//	Product product2 = new Product(2, "oranges", 5.99);
//	Product product3 = new Product(3, "Lemon", 3.99);
//	Product[] products = {product1, product2, product3};
	
	Product[] productStock = new Product[20];
	Customer[] customers = new Customer[20];
	Customer currentCustomer;
	
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
		productStock[0] = apple;
		productStock[1] = biscuits;
		productStock[2] = mints;
		productStock[3] = pen;
		productStock[4] = notebook;
		productStock[5] = milk;
		productStock[6] = bread;
		productStock[7] = chicken;
		productStock[8] = broccoli;
		productStock[9] = pasta;
	}
	
	
	
	
	public  void run ( )
	    {
	         System.out.println("Login:" );
	         System.out.print("Input ID: " );
             User = kb.nextLine( );
//             kb.nextLine( );
             System.out.print("Input Password: " );
             Pass = kb.nextLine( );
//             kb.nextLine( );
             String id = cust.getID();
             if( id.equals("1234") ) {
            	 displayCustomerMenu();
            	 
             }
             /*else if() {
            	 displaySalesMenu();
            	 
             }
             else if() {
            	 displayManagerMenu();
            	 
             }*/
	        
	         
	         
	    }
	 
	 public void displayCustomerMenu( )
	    {
		
	         System.out.println("Customer Main menu" );
	         System.out.println( "1. View Product List" );
	         System.out.println( "2. View Cart" );
	         System.out.println("3. Remove Subscription" );
	         System.out.println("4. Check Loyalty Points" );
	         
	         System.out.print("Enter Choice : " );
             choice = kb.nextInt( );
             kb.nextLine( );
             
             switch(choice) {
             
             case 1:
            	 displayProductListMenu();
            	 break;
             case 2:
            	 
            	 break;
             case 3:
            	
            	 break;
             case 4:
            	
            	 break;
             
             default:
            	 System.out.println("Choice : "+choice+" Is an invalid choice");
            	 displayCustomerMenu();
            	 break;
             	
             }
	         
	         
	    }
	 
	 public void displayProductListMenu( )
	    {
	         for(Product product : products) {
	        	 System.out.println(product);
	        	 
	         }
	         
	    }
	 
	 public void displaySalesMenu( )
	    {
	         System.out.println("Sales Assistant Main menu" );
	         System.out.println( "1. Remove item from sale " );
	         System.out.print("Enter Choice : " );
             choice = kb.nextInt( );
             kb.nextLine( );
             switch(choice) {
             
             	case 1:
             		
            	 break;
             	default:
             		System.out.println("Choice : "+choice+" Is an invalid choice");
             		
             		displaySalesMenu();
            	 break;
            	 
             }
	        
	         
	    }
	 
	 public void displayManagerMenu()
	    {
		 
	         System.out.println("Manager Main menu" );
	         System.out.println( "1. Access Report Menu" );
	         System.out.println( "2. Manage Staff" );
	         System.out.println("3. Manage Sales" );
	         System.out.print("Enter Choice : " );
             choice = kb.nextInt( );
             kb.nextLine( );
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
