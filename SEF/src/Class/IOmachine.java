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

	/* According to the user story, only Manager have authority
	 * to generate the report, so all of the methods should take 
	 * a Manager object or Manager id.
	 * */

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
	public void saveCustomer(LinkedList<Customer> customers) {
		try	{
			writer = new FileWriter(customerFile);
			for(Customer element : customers)	{
					writer.write(element.getID() + "," + element.getName() + ","
								+ element.getMoneySpent() + "," + element.getPoint() + ","
								+ element.getPostCode() + "," + element.getTimeVisited() + "," 
								+ element.getCreditCard().getFirst().getNumber() + ","
								+ element.getSubscription()+ "\n");
			}
			writer.close();
		}
		catch(IOException e)	{
			System.out.println("Problem reading file.");
		}
	}

	
	// - - - - - - - - - - LOADING IN PRODUCTS - - - - - - - - - -
	public LinkedList<Product> readInProducts()	{
		try {
			File fileName = new File(productFile);
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
		try	{
			writer = new FileWriter(employeeFile);
			for(Employee element : employees)	{
					writer.write(element.GetID() + "," + element.GetName() + ","
								+ element.GetPassword() + "," + element.GetLevel() + "\n");
			}
			writer.close();
		}
		catch(IOException e)	{
			System.out.println("Problem reading file.");
		}
	}
	// - - - - - - - - - - SAVE PRODUCTS - - - - - - - - - -
	public void saveProducts(LinkedList<Product> products) {
		try	{
			writer = new FileWriter(productFile);
			for(Product element : products)	{
					writer.write(element.getProductId() + "," + element.getProductName() + ","
								+ element.getProductDetails() + "," + element.getProductStock() + ","
								+ element.getDiscountPrice()/element.getProductPrice() + ","
								+ element.getProductPrice() + "," + element.getBulkAmount() + ","
								+ element.getbulkDiscount() + "," + element.getSupplierName() + "\n");
			}
			writer.close();
		}
		catch(IOException e)	{
			System.out.println("Problem reading file.");
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
						 recordCounter = Integer.parseInt(sc.nextLine().split(",")[0]) + 1;
					}
					for(int i = 0; customer.getCart()[i] != null && i != 50; i++)	{
						
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
}
