package Class;
public class Sale {
	private double profit = 0;
	private Customer person;
	private Product[] productID = new Product[50];
	public Sale(Customer person){
		this.person = person;
		productID = person.getCart();
	}
	
	//does the actual sale
	public void PerformSale(){
		int i = 0;
		while(productID[i] != null){
			productID[i].setProductStock(productID[i].getProductStock()-/*getnumber in cart)*/);
			profit = profit + (productID[i].getProductPrice()*/*getnumberInCart*/);
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
