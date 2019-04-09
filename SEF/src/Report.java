import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

	/**
	 * This class aims to generate different kinds of reports, including:
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

/*
 * 	QUESTION:
 * 	Difference between  Most Revenue Product Report
 * and Item Order Priority Report??
 * 
 */

/*
 * Suggest:
 * Product class: 
 * 	toString()			return String detail of product
 * 	int consumption		to record consumption
 * 	getConsumption		return consumption
 * Manager class:
 * 	getId()				return Manager id
 * System interfaceImpl:
 * 	mostPopularItem() 	return Collection<Product>
 * 
 */

public class Report {
	
	private File report;
	private FileWriter writer = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date = new Date();
	private Scanner sc;
	private int dateNumber;
	
	/* According to the user story, only Manager have authority
	 * to generate the report, so all of the methods should take 
	 * a Manager object or Manager id.
	 * */
	
	public void getRevenuePRReport(Manager manager, SystemImpl system) {
		try {
			report = new File("Most_Revenue_Product_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
			
			/* Will call method from system to pick out
			 * 5(?) most revenue products and put them into an array
			 * iterate their toString method.
			 * */
		try {
			writer.write("Most Revenue Product Report\n");
			writer.write("Name\tID\tConsumption");
			for (Product element : system.mostPopularItem())
			{
				writer.write(element.getName() + "\t" + 
						element.getId() + "\t" + element.getConsumption());
			}
			// Record manager id(can be delete if it's unnecessary)
			writer.write("\n"manager.getId());
			// Record report generate date
			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getPostcodeReport(Manager manager, SystemImpl system) {
		
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
			for (Location element : system.getLocationArray())
			{
				writer.write(element.getPostcode + "\t" + 
						element.getNumber());
			}
			// Record manager id(can be delete if it's unnecessary)
			writer.write("\n"manager.getId());
			// Record report generate date
			writer.write(dateFormat.format(date));			
			writer.close();
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getItemOPReport(Manager manager, SystemImpl system) {
		try {
			report = new File("Item_Order_Priority_Report.txt");
			writer = new FileWriter(report);
		} catch (IOException e) {
			System.out.println("Fail to create the Report!");
		}
		
		try {
			// Incmplete
		} catch (IOException e) {
			System.out.println("Fail to output the Report!");
		}
		System.out.println("Report generate successfully");
		
	}
	
	public void getPeriodReport(Manager manager, SystemImpl system) {
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
	
	public void getSupplyReport(Manager manager, SystemImpl system) {
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
