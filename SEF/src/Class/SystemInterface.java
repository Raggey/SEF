package Class;
import java.io.IOException;
import java.util.Scanner;

public class SystemInterface {
	Scanner kb = new Scanner(System.in);
	String User;
	String Pass;
	int choice;
	Collection<User> Users = new ArrayList<User>;
	Collection<Product> Products = new ArrayList<Product>;
	public void main(String[] args) {
		
		while(choice != 0) {
			run();
		}

	}
	
	
	public  void run ( )
	    {
	         System.out.println("Login:" );
	         System.out.print("Input ID: " );
             User = kb.nextLine( );
             kb.nextLine( );
             System.out.print("Input Password: " );
             Pass = kb.nextLine( );
             kb.nextLine( );
             if(/*add check*/) {
            	 displayCustomerMenu(User);
            	 
             }
             else if(/* Add check for ID here*/) {
            	 displaySalesMenu(User);
            	 
             }
             else if(/* Add check for ID here*/) {
            	 displayManagerMenu(User);
            	 
             }
	        
	         
	         
	    }
	 
	 public void displayCustomerMenu( )
	    {
		 	User = id;
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
            	 User.getCart(id);
            	 break;
             case 3:
            	 Customers.remove(user);
            	 break;
             case 4:
            	 User.getLoyaltyPoints();
            	 break;
             
             default:
            	 System.out.println("Choice : "+choice+" Is an invalid choice");
            	 displayCustomerMenu(User);
            	 break;
             	
             }
	         
	         
	    }
	 
	 public void displayProductListMenu( )
	    {
	         for(Product product : Products) {
	        	 System.out.println(product);
	        	 
	         }
	         
	    }
	 
	 public void displaySalesMenu(String id )
	    {
	         System.out.println("Sales Assistant Main menu" );
	         System.out.println( "1. Remove item from sale " );
	         System.out.print("Enter Choice : " );
             choice = kb.nextInt( );
             kb.nextLine( );
             switch(choice) {
             
             	case 1:
             		Sales.removeItem();
            	 break;
             	default:
             		System.out.println("Choice : "+choice+" Is an invalid choice");
             		
             		displaySalesMenu(id);
            	 break;
            	 
             }
	        
	         
	    }
	 
	 public void displayManagerMenu(String id)
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
             		displayManagerMenu(id);
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
