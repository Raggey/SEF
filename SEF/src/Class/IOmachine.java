package Class;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class aims to read in product info and generate different kinds of reports, including:
 *  Most Revenue Product Report
 *  Supply Report
 *  Customer Address/Postcode Report
 *  Item Order Priority Report
 *  Period Report
 *  
 *  @author 	Gloria
 *  @since		9/4/2019
 *  @version	1.0
 */



public class IOmachine {
	
	private String customerFile = "Customers.txt";
	private String employeeFile = "Employees.txt";
	private String productFile = "Products.txt";
	private File file;
	private FileWriter writer = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	private Scanner sc;
	private int recordCounter = 0;
	private int dateNumber;

	/* According to the user story, only Manager have authority
	 * to generate the report, so all of the methods should take 
	 * a Manager object or Manager id.
	 * */

	private void replaceSelected(String replaceWith, String stuff, int kind) {
		// kind 1 is record consumption
		// kind 2 is update staff 
		// kind 3 is sth...??
		try {
			// input the file content to the StringBuffer "input"
			BufferedReader file = new BufferedReader(new FileReader("Product_Consumption.txt"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				inputBuffer.append(line);
				inputBuffer.append('\n');
			}
			file.close();
			String inputStr = inputBuffer.toString();
			String list[] = inputStr.split("\n");
			String singleLine[];
			inputStr = "";
			for (int i = 0; i != list.length; i++)
			{
				if (list[i].contains(replaceWith))	{
					singleLine = list[i].split(",");
					if (kind == 1)	{
						list[i] = list[i].replace(singleLine[3], 
						String.valueOf(Integer.parseInt(singleLine[3]) + Integer.parseInt(stuff)));
					}
					else if (kind == 2)	{
						list[i] = list[i].replace(replaceWith, stuff);
					}
					else if (kind == 3)	{
						//
					}
				}
				inputStr += list[i] + "\n";
			}
			System.out.println(inputStr); // display the original file for debugging

			// logic to replace lines in the string (could use regex here to be generic)
			inputStr = inputStr.replace(replaceWith, replaceWith + "0"); 

			// display the new file for debugging
			System.out.println("----------------------------------\n" + inputStr);

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("Product_Consumption.txt");
			fileOut.write(inputStr.getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}

	// - - - - - - - - - - LOADING IN EMPLOYEE - - - - - - - - - -
	public LinkedList<Employee> readInEmployee()	{
		try {
			File fileName = new File("Employees.txt");
			LinkedList<Employee> employees = new LinkedList<Employee>();
			String employeeInfo[];
			Employee employee = null;
			sc = new Scanner(fileName);
			while (sc.hasNextLine())	{
				employeeInfo = sc.nextLine().split(",");
				employee = new Employee(employeeInfo[0],employeeInfo[1],employeeInfo[2],Integer.parseInt(employeeInfo[3]));
				employees.add(employee);
				System.out.println(employee.GetID());
			}	
			return employees;
		}
		catch (Exception e) {
			System.out.println("Error in reading file..");
			return null;
		}
	}
	
	
	// - - - - - - - - - - LOADING IN CUSTOMER - - - - - - - - - -
	public LinkedList<Customer> readInCustomer()	{
		try {
			file = new File(customerFile);
			LinkedList<Customer> customers = new LinkedList<Customer>();
			String customerInfo[];
			Customer customer = null;
			sc = new Scanner(file);
			while (sc.hasNextLine())	{
				customerInfo = sc.nextLine().split(",");
				customer = new Customer(customerInfo[0],customerInfo[1],Integer.parseInt(customerInfo[4]));
				System.out.println(customer.getID());
				customer.setMoneySpent(Integer.parseInt(customerInfo[2]));
				customer.setPoints(Integer.parseInt(customerInfo[3]));
				customer.setTimevisited(Integer.parseInt(customerInfo[5]));
				customer.addCard(new CreditCard(Integer.parseInt(customerInfo[6]), customer.getName()));
				// Add card
				if(customerInfo[6] == "0")	{
					customer.subscribe();
				}
				customers.add(customer);
			}
			return customers;
		}
		catch (Exception e) {
			System.out.println("Error in reading file..");
			return null;
		}
	}
	// - - - - - - - - - - SAVE CUSTOMERS - - - - - - - - - -
//	public void saveCustomer() {
//		/* C0001,Craig,36,5,3171,5,1234,1 */
//		/* ID| NAME| TOTALMONEYSPENT | POINTS| POSTCODE | TIMESVISITED | CREDITCARD NO. | SUBSCRIPTION */
//		MainSEF main = new MainSEF();
//		LinkedList<Customer> cus
//	tomers = new LinkedList<Customer>();
//		customers = main.getCustomers();
//	}

	
	// - - - - - - - - - - LOADING IN PRODUCTS - - - - - - - - - -
	public LinkedList<Product> readInProducts()	{
		try {
			File fileName = new File("Products.txt");
			LinkedList<Product> products = new LinkedList<Product>();
			String productInfo[];
			Product product = null;
			sc = new Scanner(fileName);
			while (sc.hasNextLine())	{
				productInfo = sc.nextLine().split(",");
				product = new Product(Integer.parseInt(productInfo[0]),productInfo[1],Double.parseDouble(productInfo[5]),
										Integer.parseInt(productInfo[3]), productInfo[8]);
				product.setBulkAmount(Integer.parseInt(productInfo[6]));
				product.setbulkDiscount(Double.parseDouble(productInfo[7]));
				product.setDiscountPrice(Double.parseDouble(productInfo[4]));
				product.setProductDetails(productInfo[2]);
				products.add(product);
			}
			return products;
		}
		catch (Exception e) {
			System.out.println("Problem reading file.");
			return null;
		}
	}

	// - - - - - - - - - - SAVE EMPLOYEE - - - - - - - - - -
	public void saveEmployees(LinkedList<Employee> employees) throws IOException {
		sc = new Scanner(employeeFile);
		String current = "";
		while(sc.hasNextLine())	{
			current = sc.nextLine();
			for(Employee element : employees)	{
				if (element.GetID() == current.split(",")[0])	{
					replaceSelected(current, element.GetID() + "," + element.GetName() + ","
							+ element.GetPassword() + "," + element.GetLevel(), 2);
				}
				else {
					writer = new FileWriter(employeeFile, true);
					writer.write(element.GetID() + "," + element.GetName() + ","
								+ element.GetPassword() + "," + element.GetLevel());
					writer.close();
				}
			}
		}
		
	}
	// - - - - - - - - - - SAVE PRODUCTS - - - - - - - - - -
	public void saveProducts() {

	}

	public void recordConsumption(LinkedList<Product> allProducts) throws IOException	{
		String replaceWith = "";
		File temp = new File("Product_Consumption.txt");
		if(temp.createNewFile()){
			for (Product element : allProducts)	{
				replaceWith += element.getProductId() + "," + element.getProductName() + ","
						+ String.valueOf(element.getConsumption() + "\n");
			}
			try {
				writer = new FileWriter(temp);
				writer.write(replaceWith);
				writer.close();
			} catch (IOException e) {
				System.out.println("Fail to create the file!");
			}
		}
		else	{
			for (Product element : allProducts)	{
				replaceWith += element.getProductId() + "," + element.getProductName() + ",";
				replaceSelected(replaceWith, String.valueOf(element.getConsumption()), 1);
			}
		}
	}

	public void recordSale(Customer customer, LinkedList<Double> report)	{
		//need to be test
		String filePath = "Sale_Record.txt";
		File temp = new File(filePath);
		try {
			if(temp.createNewFile()){
				try {
					String record = "";
					for(int i = 0; customer.getCart()[i] != null && i != 50; i++)	{
						System.out.println(i);
						record += "0," + customer.getCart()[i].getProductName() + "," 
								+ customer.getCart()[i].getNumberInCart() + ","
								+ report.get(i)/customer.getCart()[i].getNumberInCart() + "," 
								+ customer.getID() + "," + dateFormat.format(date);
						if(i + 1 != 50 && customer.getCart()[i + 1] != null)	{
							record += "\n";
						}
					}
					writer = new FileWriter(temp);
					writer.write(record);
					writer.close();
				} catch (IOException e) {
					System.out.println("Fail to create the file!");
				}
			}
			else	{
				try {
					String record = "";
					sc = new Scanner(temp);
					while(sc.hasNextLine())	{
						System.out.println();
						 recordCounter = Integer.parseInt(sc.nextLine().split(",")[0]) + 1;
					}
					for(int i = 0; customer.getCart()[i] != null && i != 50; i++)	{
						System.out.println(i);
						
						record += recordCounter+ "," + customer.getCart()[i].getProductName() + "," 
								+ customer.getCart()[i].getNumberInCart() + ","
								+ report.get(i)/customer.getCart()[i].getNumberInCart() + "," 
								+ customer.getID() + "," + dateFormat.format(date);
						if(i + 1 != 50 && customer.getCart()[i + 1] != null)	{
							record += "\n";
						}
					}
					writer = new FileWriter(temp, true);
					writer.write("\n");
					writer.write(record);
					writer.close();
				} catch (IOException e) {
					System.out.println("Fail to write file!");
				}
			}
			recordCounter++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//	
	//	
	//	public void getRevenuePRReport(Employee manager, LinkedList<Product> products) {
	//		// Go through products and calculate their profits by using consumption x price
	//		// List from top to bottom
	//		// require: productArray
	//		String fileName = "Most_Revenue_Product_Report_" + date.getDay() + date.getMonth() + date.getYear() +".txt";
	//		try {
	//			file = new File(fileName);
	//			writer = new FileWriter(file);
	//		} catch (IOException e) {
	//			System.out.println("Fail to create the Report!");
	//		}
	//			
	//			/* Will call method from system to pick out
	//			 * 5(?) most revenue products and put them into an array
	//			 * iterate their toString method.
	//			 * */
	//		try {
	//			writer.write("Most Revenue Product Report\n");
	//			writer.write("Name\tID\tProfit");
	//			// incomplete
	//			for (Product element : products)
	//			{
	//				writer.write(element.getProductName() + "\t" + 
	//						element.getProductId() + "\t" + element.getRevenue());
	//			}
	//			// Record manager id(can be delete if it's unnecessary)
	//			writer.write("\n" + manager.GetID());
	//			// Record report generate date
	//			writer.write(dateFormat.format(date));			
	//			writer.close();
	//		} catch (IOException e) {
	//			System.out.println("Fail to output the Report!");
	//		}
	//		System.out.println("Report generate successfully");
	//		
	//	}
	//	
	//	public void getPostcodeReport(Employee manager, SystemImpl system) {
	//		
	//		try {
	//			report = new File("Customer_Postcode_Report.txt");
	//			writer = new FileWriter(report);
	//		} catch (IOException e) {
	//			System.out.println("Fail to create the Report!");
	//		}
	//			
	//			/* Will call method from system to get Location collection,
	//			 * can be simplify later by calling from other classes.
	//			 * */
	//		try {
	//			writer.write("Customer Postcode Report\n");
	//			writer.write("Postcode\tCustomer Number");
	////			String locationList[] = new String[system.getLocation().size()];
	////			String temp;
	////			for ( String element : system.getLocation().getkeySet())
	////			{
	////				if(locationList == null)
	////				{
	////					locationList[0] = system.getLocation().get(element);
	////				}
	////				else
	////				{
	////					for(int i = 0; i != locationList.length; i++)
	////					{
	////						if (locationList[i] != null)
	////						{
	////							if(i == 0)
	////							{
	////								if(system.getLocation().get(element) > system.getLocation().get(locationList[i]))
	////								{
	////									temp = locationList[i];
	////									locationList[i] = system.getLocation().get(element);
	////									locationList[i + 1] = temp;
	////								}
	////							}
	////							else
	////							{
	////								if(system.getLocation().get(element) > system.getLocation().get(locationList[i]) || system.getLocation().get(element) < system.getLocation().get(locationList[i - 1]))
	////								{
	////									locationList[i] 
	////								}
	////							}
	////						}
	////						else
	////						{
	////							
	////						}
	////					}
	////				}
	//				
	//				writer.write(element.getPostcode + "\t" + 
	//						element.getNumber());
	//			}
	//			// Record manager id(can be delete if it's unnecessary)
	//			writer.write("\n"+ manager.GetID());
	//			// Record report generate date
	//			writer.write(dateFormat.format(date));			
	//			writer.close();
	//		} catch (IOException e) {
	//			System.out.println("Fail to output the Report!");
	//		}
	//		System.out.println("Report generate successfully");
	//		
	//	}
	//	
	//	public void getItemOPReport(Employee manager) {
	//		// Sort by stock
	//		try {
	//			String fileName = "Item_Order_Priority_Report_" + date.getDay() + date.getMonth() + date.getYear() +".txt";
	//			file = new File(fileName);
	//			writer = new FileWriter(file);
	//		} catch (IOException e) {
	//			System.out.println("Fail to create the Report!");
	//		}
	//		
	//		try {
	//			BufferedReader file = new BufferedReader(new FileReader("Product_Consumption.txt"));
	//	        StringBuffer inputBuffer = new StringBuffer();
	//	        String line;
	//
	//	        while ((line = file.readLine()) != null) {
	//	            inputBuffer.append(line);
	//	            inputBuffer.append('\n');
	//	        }
	//	        file.close();
	//	        HashMap<Integer, String> sortProduct;
	//	        String inputStr = inputBuffer.toString();
	//	        String[] productConList = inputStr.split("\n");
	//	        String singleLine[];
	//	        inputStr = "";
	//	        for (int i = 0; i != productConList.length; i++)
	//	        {
	//	        	singleLine = productConList[i].split(",");
	//	        	sortProduct.put(Integer.parseInt(singleLine[3]), productConList[i]);
	////	        	if (productConList[i].contains(replaceWith))	{
	////	        		productConList[i] = productConList[i].replace(singleLine[3], 
	////	        				String.valueOf(Integer.parseInt(singleLine[3]) + number));
	////
	////	        	inputStr += productConList[i] + "\n";
	//	        }
	//	        //sortProduct.keySet().toArray();
	//	        
	//			writer.write("Item Order Priority Report\n");
	//			writer.write("Name\tID\tConsumption");
	//			
	//			for (Product element : system.mostPopularItem())
	//			{
	//				writer.write(element.getName() + "\t" + 
	//						element.getId() + "\t" + element.getConsumption());
	//			}
	//			// Record manager id(can be delete if it's unnecessary)
	//			writer.write("\n" + manager.GetID());
	//			// Record report generate date
	//			writer.write(dateFormat.format(date));			
	//			writer.close();
	//		} catch (IOException e) {
	//			System.out.println("Fail to output the Report!");
	//		}
	//		System.out.println("Report generate successfully");
	//		
	//	}
	//	
	//	public void getPeriodReport(Employee manager, SystemImpl system) {
	//		// Will need database in the area
	//		System.out.println("Please enter the start day:(dd/mm/yyyy)");
	//		sc = new Scanner(System.in);
	//		try {
	//			//incomplete
	//		} catch (NumberFormatException e)
	//		{
	//			System.out.println("Invaild input date!");
	//		}
	//		
	//		try {
	//			report = new File("Period_Report.txt");
	//			writer = new FileWriter(report);
	//		} catch (IOException e) {
	//			System.out.println("Fail to create the Report!");
	//		}
	//		
	//		try {
	////			writer.write("Customer Postcode Report\n");
	////			writer.write("Postcode\tCustomer Number");
	////			for (Location element : system.getLocationArray())
	////			{
	////				writer.write(element.getPostcode + "\t" + 
	////						element.getNumber());
	////			}
	////			// Record manager id(can be delete if it's unnecessary)
	////			writer.write("\n"manager.getId());
	////			// Record report generate date
	////			writer.write(dateFormat.format(date));			
	//			writer.close();
	//		} catch (IOException e) {
	//			System.out.println("Fail to output the Report!");
	//		}
	//		System.out.println("Report generate successfully");
	//	}
	//	
	//	public void getSupplyReport(Employee manager, SystemImpl system) {
	//		try {
	//			report = new File("Supply_Report.txt");
	//			writer = new FileWriter(report);
	//		} catch (IOException e) {
	//			System.out.println("Fail to create the Report!");
	//		}
	//		
	//		try {
	////			writer.write("Customer Postcode Report\n");
	////			writer.write("Postcode\tCustomer Number");
	////			for (Location element : system.getLocationArray())
	////			{
	////				writer.write(element.getPostcode + "\t" + 
	////						element.getNumber());
	////			}
	////			// Record manager id(can be delete if it's unnecessary)
	////			writer.write("\n"manager.getId());
	////			// Record report generate date
	////			writer.write(dateFormat.format(date));			
	////			writer.close();
	//		} catch (IOException e) {
	//			System.out.println("Fail to output the Report!");
	//		}
	//		System.out.println("Report generate successfully");
	//	}
}
