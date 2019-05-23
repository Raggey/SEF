package Class;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

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
	
	private FileWriter writer = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date = new Date();
	private Scanner sc;
	private int dateNumber;
	
	/* According to the user story, only Manager have authority
	 * to generate the report, so all of the methods should take 
	 * a Manager object or Manager id.
	 * */
	
	public LinkedList<Product> readInProducts()	{
		
	}
	
	public void getRevenuePRReport(Employee manager, LinkedList<Product> products) {
		// Go through products and calculate their profits by using consumption x price
		// List from top to bottom
		// require: productArray
		try {
			writer = new FileWriter("file/Most_Revenue_Product_Report.txt");
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
			
			/* Will call method from system to pick out
			 * 5(?) most revenue products and put them into an array
			 * iterate their toString method.
			 * */
		try {
			writer.write("Most Revenue Product Report\n");
			writer.write("Name\tID\tProfit");
			// incomplete
			for (Product element : products)
			{
				writer.write(element.getProductName() + "\t" + 
						element.getProductId() + "\t" + element.getRevenue());
			}
			// Record manager id(can be delete if it's unnecessary)
			writer.write("\n" + manager.GetID());
			// Record report generate date
			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getPostcodeReport(Employee manager, SystemImpl system) {
		
		try {
			report = new File("Customer_Postcode_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
			
			/* Will call method from system to get Location collection,
			 * can be simplify later by calling from other classes.
			 * */
		try {
			writer.write("Customer Postcode Report\n");
			writer.write("Postcode\tCustomer Number");
//			String locationList[] = new String[system.getLocation().size()];
//			String temp;
//			for ( String element : system.getLocation().getkeySet())
//			{
//				if(locationList == null)
//				{
//					locationList[0] = system.getLocation().get(element);
//				}
//				else
//				{
//					for(int i = 0; i != locationList.length; i++)
//					{
//						if (locationList[i] != null)
//						{
//							if(i == 0)
//							{
//								if(system.getLocation().get(element) > system.getLocation().get(locationList[i]))
//								{
//									temp = locationList[i];
//									locationList[i] = system.getLocation().get(element);
//									locationList[i + 1] = temp;
//								}
//							}
//							else
//							{
//								if(system.getLocation().get(element) > system.getLocation().get(locationList[i]) || system.getLocation().get(element) < system.getLocation().get(locationList[i - 1]))
//								{
//									locationList[i] 
//								}
//							}
//						}
//						else
//						{
//							
//						}
//					}
//				}
				
				writer.write(element.getPostcode + "\t" + 
						element.getNumber());
			}
			// Record manager id(can be delete if it's unnecessary)
			writer.write("\n"+ manager.GetID());
			// Record report generate date
			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getItemOPReport(Employee manager, SystemImpl system) {
		// Sort by stock
		try {
			report = new File("Item_Order_Priority_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
		
		try {
			writer.write("Item Order Priority Report\n");
			writer.write("Name\tID\tConsumption");
			for (Product element : system.mostPopularItem())
			{
				writer.write(element.getName() + "\t" + 
						element.getId() + "\t" + element.getConsumption());
			}
			// Record manager id(can be delete if it's unnecessary)
			writer.write("\n" + manager.GetID());
			// Record report generate date
			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getPeriodReport(Employee manager, SystemImpl system) {
		// Will need database in the area
		System.out.println("Please enter the start day:(dd/mm/yyyy)");
		sc = new Scanner(System.in);
		try {
			//incomplete
		} catch (NumberFormatException e)
		{
			System.out.println("Invaild input date!");
		}
		
		try {
			report = new File("Period_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
		
		try {
//			writer.write("Customer Postcode Report\n");
//			writer.write("Postcode\tCustomer Number");
//			for (Location element : system.getLocationArray())
//			{
//				writer.write(element.getPostcode + "\t" + 
//						element.getNumber());
//			}
//			// Record manager id(can be delete if it's unnecessary)
//			writer.write("\n"manager.getId());
//			// Record report generate date
//			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
	}
	
	public void getSupplyReport(Employee manager, SystemImpl system) {
		try {
			report = new File("Supply_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
		
		try {
//			writer.write("Customer Postcode Report\n");
//			writer.write("Postcode\tCustomer Number");
//			for (Location element : system.getLocationArray())
//			{
//				writer.write(element.getPostcode + "\t" + 
//						element.getNumber());
//			}
//			// Record manager id(can be delete if it's unnecessary)
//			writer.write("\n"manager.getId());
//			// Record report generate date
//			writer.write(dateFormat.format(date));			
//			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
	}
}
