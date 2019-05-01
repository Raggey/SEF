package Class;
public class Sale {
	private double profit = 0;
	private Customer person;
	private Product[] productCart = new Product[50];
	public Sale(Customer person) {
		this.person = person;
		productCart = person.getCart();
	}
	
	//does the actual sale
	public void PerformSale(){
		int i = 0;
		while(productCart[i] != null){
			System.out.println("stock was: " + productCart[i].getProductStock());
			productCart[i].setProductStock(productCart[i].getProductStock()-productCart[i].getNumberInCart());
			System.out.printf("%d... stock is now: ", productCart[i].getProductStock());
			profit = profit + (productCart[i].getProductPrice()*productCart[i].getNumberInCart());
			productCart[i].addConsumption(productCart[i].getNumberInCart());
			i++;
		}
	}
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
