package Class;
import java.io.*;
import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Scanner;
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

	//	private File file;
	private FileWriter writer = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	//	private Scanner sc;
	private int recordCounter = 0;
	private int dateNumber;

	/* According to the user story, only Manager have authority
	 * to generate the report, so all of the methods should take 
	 * a Manager object or Manager id.
	 * */

	//	private void replaceSelected(String replaceWith, int number) {
	//	    try {
	//	        // input the file content to the StringBuffer "input"
	//	        BufferedReader file = new BufferedReader(new FileReader("Product_Consumption.txt"));
	//	        StringBuffer inputBuffer = new StringBuffer();
	//	        String line;
	//
	//	        while ((line = file.readLine()) != null) {
	//	            inputBuffer.append(line);
	//	            inputBuffer.append('\n');
	//	        }
	//	        file.close();
	//	        String inputStr = inputBuffer.toString();
	//	        String productConList[] = inputStr.split("\n");
	//	        String singleLine[];
	//	        inputStr = "";
	//	        for (int i = 0; i != productConList.length; i++)
	//	        {
	//	        	if (productConList[i].contains(replaceWith))	{
	//	        		singleLine = productConList[i].split(",");
	//	        		productConList[i] = productConList[i].replace(singleLine[3], 
	//	        				String.valueOf(Integer.parseInt(singleLine[3]) + number));
	//	        	}
	//	        	inputStr += productConList[i] + "\n";
	//	        }
	//	        System.out.println(inputStr); // display the original file for debugging
	//
	//	        // logic to replace lines in the string (could use regex here to be generic)
	//	        inputStr = inputStr.replace(replaceWith, replaceWith + "0"); 
	//
	//	        // display the new file for debugging
	//	        System.out.println("----------------------------------\n" + inputStr);
	//
	//	        // write the new string with the replaced line OVER the same file
	//	        FileOutputStream fileOut = new FileOutputStream("Product_Consumption.txt");
	//	        fileOut.write(inputStr.getBytes());
	//	        fileOut.close();
	//
	//	    } catch (Exception e) {
	//	        System.out.println("Problem reading file.");
	//	    }
	//	}
	//	
	public LinkedList<Employee> readInEmployee()	{
		try {
			File fileName = new File("/Users/DANIEL/Downloads/Employees.txt");
			LinkedList<Employee> employees = new LinkedList<Employee>();
			String employeeInfo[];
			Employee employee = null;
			Scanner sc = new Scanner(fileName);
			while (sc.hasNextLine())	{
				employeeInfo = sc.nextLine().split(",");
				employee = new Employee(employeeInfo[0],employeeInfo[1],employeeInfo[2],Integer.parseInt(employeeInfo[3]));
				employees.add(employee);
				System.out.println(employee.GetID());
			}	
			return employees;
		}
		catch (Exception e) {
			System.out.println("Problem reading file.");
			return null;
		}
	}
	//		LinkedList<Employee> employees;
	//		String employeeInfo[];
	//		sc = new Scanner("Employees.txt");
	//		while (sc.hasNextLine())	{
	//			employeeInfo = sc.nextLine().split(",");
	//			employees.add(new Employee(employeeInfo[0],employeeInfo[1],employeeInfo[2],Integer.parseInt(employeeInfo[3])));
	//		}
	//		return employees;
	//		}
	//	
	public LinkedList<Customer> readInCustomer()	{
		try {
			File fileName = new File("/Users/DANIEL/Downloads/Customers.txt");
			LinkedList<Customer> customers = new LinkedList<Customer>();
			String customerInfo[];
			Customer customer = null;
			Scanner sc = new Scanner(fileName);
			while (sc.hasNextLine())	{
				customerInfo = sc.nextLine().split(",");
				customer = new Customer(customerInfo[0],customerInfo[1],Integer.parseInt(customerInfo[4]));
				System.out.println(customer.getID());
				customer.setMoneySpent(Integer.parseInt(customerInfo[2]));
				customer.setPoints(Integer.parseInt(customerInfo[3]));
				customer.setTimevisited(Integer.parseInt(customerInfo[5]));
				if(customerInfo[6] == "0")	{
					customer.subscribe();
				}
				customers.add(customer);
			}
			return customers;
		}
		catch (Exception e) {
			System.out.println("Problem reading file.");
			return null;
		}
	}
	//	
	//	public LinkedList<Product> readInProducts()	{
	//		sc = new Scanner("Products.txt");
	//		while (sc.hasNextLine())	{
	//			String productIfo[] = sc.nextLine().split(",");
	//			// Incomplete
	//			// Still need deeper understand about the values
	//		}
	//	}
	//	
	//	public void recordConsumption(LinkedList<Product> allProducts)	{
	//		String replaceWith = "";
	//		File temp = new File("Product_Consumption.txt");
	//		if(temp.createNewFile()){
	//			for (Product element : allProducts)	{
	//				replaceWith += element.getProductId() + "," + element.getProductName() + ","
	//						+ String.valueOf(element.getConsumption() + "\n");
	//			}
	//			try {
	//				writer = new FileWriter(temp);
	//	            writer.write(replaceWith);
	//	            writer.close();
	//			} catch (IOException e) {
	//				System.out.println("Fail to create the file!");
	//			}
	//        }
	//		else	{
	//			for (Product element : allProducts)	{
	//				replaceWith += element.getProductId() + "," + element.getProductName() + ",";
	//				replaceSelected(replaceWith, element.getConsumption());
	//			}
	//		}
	//	}
	//	
	//	public void recordSale(Customer customer, LinkedList<Double> report)	{
	//		//need to be test
	//		String filePath = "Sale_Record.txt";
	//		String record = recordCounter + ",";
	//		for(int i = 0; customer.getCart()[i] != null || i != 50; i++)	{
	//			record += "["+ customer.getCart()[i].getProductName() + "," 
	//					+ customer.getCart()[i].getNumberInCart() + ","
	//					+ report.get(i)/customer.getCart()[i].getNumberInCart() + ",";
	//		}
	//		record += "]" + customer.getID() + "," + dateFormat.format(date);
	//		File temp = new File(filePath);
	//		if(temp.createNewFile()){
	//			try {
	//				writer = new FileWriter(temp);
	//	            writer.write(record);
	//	            writer.close();
	//			} catch (IOException e) {
	//				System.out.println("Fail to create the file!");
	//			}
	//        }
	//		else	{
	//			try {
	//				writer = new FileWriter(temp, true);
	//				writer.write("\n");
	//				writer.write(record);
	//				writer.close();
	//			} catch (IOException e) {
	//				System.out.println("Fail to write file!");
	//			}
	//		}
	//	}
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
