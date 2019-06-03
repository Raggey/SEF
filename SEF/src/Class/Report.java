package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.*;

public class Report {
	private File SaleRecords = new File("Sale_Record.txt");
		private LinkedList<Product> products;
		private LinkedList<Customer> customers;
		private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		private Date currentDate = new Date();
		
		String salesInfo [];
	public Report(LinkedList<Product> productList,LinkedList<Customer> customersList) {
			this.products = productList;
			this.customers = customersList;
	}
		
		public void getRevenuePRReport() {
			// Go through products and calculate their profits by using consumption x price
			// List from top to bottom
			// require: productArray
			File fileName = new File("Sale_Record.txt");
			Scanner sc;
			try {
				sc = new Scanner(fileName);
				System.out.print("Report for Items with most Revenue: " + dateFormat.format(currentDate));
			
				String[] pNames = new String[products.size()];
				float[] pRevenues = new float[products.size()];
				
				for(int i = 0; i < products.size();i++) {
					pNames[i] = products.get(i).getProductName();
				}
				while(sc.hasNextLine()) {
					salesInfo = sc.nextLine().split(",");
					String productName = salesInfo[1];
					int quantity = Integer.parseInt(salesInfo[2]);
					float price = Float.parseFloat(salesInfo[3]);
					
					for(int i = 0;i<pNames.length;i++) {
						if(pNames[i].matches(productName)) {
							float totalCost = quantity*price;
							pRevenues[i]+= totalCost;
						}
					}
				}
			
				for(int j = 0; j<pNames.length;j++) {
					System.out.println("Product :"+pNames[j]+" Pulled in a revenue of :  "+pRevenues[j]);
						
				}
			
				System.out.println("Report generate successfully");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}







			//	
				public void getPostcodeReport(){
					
					File fileName = new File("Sale_Record.txt");
					Scanner sc;
					try {
						sc = new Scanner(fileName);
						System.out.println("Report for Postcode with most Revenue: " + dateFormat.format(currentDate));
					
						ArrayList<Integer> postcodes = new ArrayList<Integer>();
						
						for(int i = 0; i<customers.size();i++) {
							int currentPostcode = customers.get(i).getPostCode();
							if(!postcodes.contains(currentPostcode)) 
								postcodes.add(currentPostcode);
						}
						
						
						float[] pRevenues = new float[postcodes.size()];
						
					
						while(sc.hasNextLine()) {
							salesInfo = sc.nextLine().split(",");
							String custID = salesInfo[4];
							int quantity = Integer.parseInt(salesInfo[2]);
							float price = Float.parseFloat(salesInfo[3]);
							int custPostcode = 0;
							for(int i = 0; i<customers.size();i++) {
								if(customers.get(i).getID().matches(custID)) {
									custPostcode = customers.get(i).getPostCode();
									
								}
							}
							
							for(int i = 0;i<postcodes.size();i++) {
								if(postcodes.get(i).equals(custPostcode)) {
									float totalCost = quantity*price;
									pRevenues[i]+= totalCost;
								}
							}
						}
					
						for(int j = 0; j<postcodes.size();j++) {
							System.out.println("Postcode :"+postcodes.get(j)+" Pulled in a revenue of :  "+pRevenues[j]);
								
						}
					
						System.out.println("Report generate successfully");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						/* Will call method from system to get Location collection,
						 * can be simplify later by calling from other classes.
						 * */
					
					
					
				}
				
				
				
				
		public void getItemOPReport() {
					// Sort by stock
			File fileName = new File("Sale_Record.txt");
			Scanner sc;
			try {
				sc = new Scanner(fileName);
				System.out.print("Report for Items with most Revenue: " + dateFormat.format(currentDate));
			
				String[] pNames = new String[products.size()];
				int[] pRevenues = new int[products.size()];
				
				for(int i = 0; i < products.size();i++) {
					pNames[i] = products.get(i).getProductName();
				}
				while(sc.hasNextLine()) {
					salesInfo = sc.nextLine().split(",");
					String productName = salesInfo[1];
					int quantity = Integer.parseInt(salesInfo[2]);
					
					
					for(int i = 0;i<pNames.length;i++) {
						if(pNames[i].matches(productName)) {
							int totalCost = quantity;
							pRevenues[i]+= totalCost;
						}
					}
				}
			
				for(int j = 0; j<pNames.length;j++) {
					System.out.println("Product :"+pNames[j]+" Sold  :  "+pRevenues[j]+" many items.");
						
				}
			
				System.out.println("Report generate successfully");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					
				}
				
				public void getPeriodReport( ) throws ParseException {
					File fileName = new File("Sale_Record.txt");
					Scanner sc;
					Scanner scn = new Scanner(System.in);
					System.out.println("Please Input date:");
					String input = scn .nextLine();
					Date dateInput = new SimpleDateFormat("dd/MM/yyyy").parse(input);
					System.out.println();
					try {
						sc = new Scanner(fileName);
						
						String[] pNames = new String[products.size()];
						int[] pRevenues = new int[products.size()];
						int[] pQuantity = new int[products.size()];
						while(sc.hasNextLine()) {
							salesInfo = sc.nextLine().split(",");
							String productName = salesInfo[1];
							int quantity = Integer.parseInt(salesInfo[2]);
							float price = Float.parseFloat(salesInfo[3]);
							Date dateOfSale = new SimpleDateFormat("dd/MM/yyyy").parse(salesInfo[5]);
							
							ArrayList<Date> saleDate = new ArrayList<Date>();
							for(int i = 0; i < products.size();i++) {
								pNames[i] = products.get(i).getProductName();
							}
							if(dateInput.compareTo(dateOfSale)<=0) {
							for(int i = 0;i<pNames.length;i++) {
								
								if(pNames[i].matches(productName)) {
									float totalCost = price*quantity;
									pRevenues[i]+= totalCost;
									pQuantity[i] += quantity;
									
									
								}
								}
							}
							
							
							
							
							}
						System.out.println("Report for Dates " + dateFormat.format(dateInput));
							for(int i = 0;i<pNames.length;i++) {
								if(pQuantity[i]>0) {
								System.out.println("Product : "+pNames[i]+" Sold this many items : "+pQuantity[i]+" For this much revenue : "+pRevenues[i]+" From this date onwards : "+dateInput);
								}
							}
							
						System.out.println("Report generate successfully");
				
					
					}
						catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					System.out.println("Report generate successfully");
				}
				
				public void getSupplyReport() {
					File fileName = new File("Sale_Record.txt");
					Scanner sc;
					try {
						sc = new Scanner(fileName);
						System.out.println("Report for Supplier with most Revenue: " + dateFormat.format(currentDate));
					
						ArrayList<String> supplier = new ArrayList<String>();
						
						for(int i = 0; i<products.size();i++) {
							String currentSupplier = products.get(i).getSupplierName();
							if(!supplier.contains(currentSupplier)) 
								supplier.add(currentSupplier);
						}
						
						
						float[] pRevenues = new float[supplier.size()];
						
					
						while(sc.hasNextLine()) {
							salesInfo = sc.nextLine().split(",");
							String productName = salesInfo[1];
							int quantity = Integer.parseInt(salesInfo[2]);
							float price = Float.parseFloat(salesInfo[3]);
							String suppName = "";
							for(int i = 0; i<products.size();i++) {
								if(products.get(i).getProductName().matches(productName)) {
									suppName = products.get(i).getSupplierName();
									
								}
							}
							
							for(int i = 0;i<supplier.size();i++) {
								if(supplier.get(i).equals(suppName)) {
									float totalCost = quantity*price;
									pRevenues[i]+= totalCost;
								}
							}
						}
					
						for(int j = 0; j<supplier.size();j++) {
							System.out.println("Items from Supplier :"+supplier.get(j)+" Pulled in a revenue of :  "+pRevenues[j]);
								
						}
					
						System.out.println("Report generate successfully");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						/* Will call method from system to get Location collection,
						 * can be simplify later by calling from other classes.
						 * */
					
					
					
					
				}
}

