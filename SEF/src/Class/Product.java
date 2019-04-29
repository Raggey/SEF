package Class;

public class Product {
	
	private int productId;
	private String productName;
	private String productDetails;
	private int productStock;
	private double productDiscount;
	private double productPrice;
	private int numberInCart;
	private int consumption;
	
	
	public Product (int productId, String productName, double productPrice, int productStock) {
		this.productId = productId;
		this.productName = productName;
		this.productDetails = "";			//Set as when needed
		this.productStock = productStock;	
		this.productPrice = productPrice;
		this.numberInCart = 0;
		this.consumption = 0;
		//this.productDiscount = 0;			//No discount initially
	}

	public int getProductId() {
		return productId;
	}

	/*If required to get productName based on given ID */
	public String getProductName() {
		return productName;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public int getProductStock() {
		return productStock;
	}
	/* Used to set once stock is updated */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

//	public double getProductDiscount() {
//		return productDiscount;
//	}
//
//	public void setProductDiscount(double productDiscount) {
//		this.productDiscount = productDiscount;
//	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double producPrice) {
		this.productPrice = producPrice;
	}
	
	public void reduceStock(int i) {
		this.productStock -= i;
	}

	public void increaseStock(int i) {
		this.productStock += i;
	}
	
	public int getNumberInCart()
	{
		return numberInCart;
	}
	
	public void setNumberInCart(int numberInCart)
	{
		this.numberInCart = numberInCart;
	}

	public void addConsumption(int consumption)
	{
		this.consumption += consumption;
	}
	
	public String detailDisplay()
	{
		return "ID: " + productId + "\nName: " + productName + "\nPrice: " + productPrice + "\n Stock: " + productStock + "\n" ;
	}
}
