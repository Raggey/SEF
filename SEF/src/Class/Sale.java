package Class;
public class Sale {
	private double profit = 0;
	private Customer person;
	private double totalPrice = 0;
	private Product[] productCart = new Product[50];
	public Sale(Customer person) {
		this.person = person;
		productCart = person.getCart();
	}
	
	//checks the price of the cart and calculates discounts
	public void CheckPrice(){
		double tempPrice = 0;
		int i = 0;
		while(productCart[i] != null){
			tempPrice = tempPrice + (productCart[i].getDiscountedPrice()*productCart[i].getNumberInCart());
			//check if there is a bulk discount
			if(productCart[i].bulkDiscount() != null){
				if(productCart[i].getNumberInCart() => productCart[i].bulkAmount()){
					//using interger division, divide the number in the cart by the bulkAmount to see how many times the bulk discount should be applied
					tempPrice = tempPrice - (productCart[i].bulkDiscount()*(productCart[i].getNumberInCart/productCart[i].bulkAmount()));
				}
			}
			i++;
		}
		//check the points discount. It is false because we are only checking the price rather than confirming the sale
		totalPrice = tempPrice - person.checkDiscount(tempPrice, false);	
		System.out.println("The total price is %d" + totalPrice);
		
	}
	
	
	//reduces Stock
	public void PerformSale(){
		int i = 0;
		person.checkDiscount(totalPrice, true);
		System.out.println("You have spent $%d" + totalPrice);
		
		while(productCart[i] != null){
			System.out.println("The Stock was %d" + productCart[i].getProductStock());  //comment out later
			productCart[i].setProductStock(productCart[i].getProductStock()-productCart[i].getNumberInCart());
			
			System.out.println("The Stock is now %d" + productCart[i].getProductStock()); //comment out later
			productCart[i].addConsumption(productCart[i].getNumberInCart());
			i++;
		}
	}
	
	//report does something with turning the totalPrice into a more concrete 'profit' variable
	/*
	public double ApplyDiscounts(double productPrice) {
		double newPrice;
		newPrice = productPrice*discount;
		return newPrice;
	}
	
	public void SetSaleAmount(double newAmount) {
		discount = newAmount;
		return;
	}
	*/
}
