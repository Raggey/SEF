package Class;

import java.util.LinkedList;

public class Sale {
	private double profit = 0;
	private Customer person;
	private double totalPrice = 0;
	private Product[] productCart = new Product[50];
	private LinkedList<Double> individualPrice = new LinkedList<Double>();
	private IOmachine report = new IOmachine();
	
	public Sale(Customer person) {
		this.person = person;
		productCart = person.getCart();
	}
	
	//checks the price of the cart and calculates discounts
	public void CheckPrice(){
		double tempPrice = 0;
		int i = 0;
		while(productCart[i] != null){
			tempPrice = tempPrice + CalculateItemPrice(i);
			individualPrice.add(CalculateItemPrice(i));
			i++;
		}
		//check the points discount. It is false because we are only checking the price rather than confirming the sale
		totalPrice = tempPrice - person.checkDiscount(tempPrice, false);	
		System.out.printf("The total price is: $%.2f\n", totalPrice );
		
	}
	
	
	//reduces Stock
	public Customer PerformSale(){
		int i = 0;
		person.checkDiscount(totalPrice, true);
		person.calculatePoints(totalPrice);
		System.out.printf("You have spent: $%.2f \n", totalPrice);
		
		while(productCart[i] != null){
			productCart[i].setProductStock(productCart[i].getProductStock()-productCart[i].getNumberInCart());
			productCart[i].addConsumption(productCart[i].getNumberInCart());
			i++;
		}
		report.recordSale(person, individualPrice);
		return person;
	}

	public double CalculateItemPrice(int i) {
		double price = 0;
		productCart = person.getCart();
		price = productCart[i].getDiscountPrice()*productCart[i].getNumberInCart();
		//check if there is a bulk discount
		if(productCart[i].getbulkDiscount() != 0){
			if(productCart[i].getNumberInCart() >= productCart[i].getBulkAmount()){
				//using integer division, divide the number in the cart by the bulkAmount to see how many times the bulk discount should be applied
				price = price - (productCart[i].getbulkDiscount()*(productCart[i].getNumberInCart()/productCart[i].getBulkAmount()));
			}
		}
		return price;
	}
}