package Class;

public class Product {

	private int productId;
	private String productName;
	private String productDetails;
	private int productStock;
	private double productDiscount; // Flat Discount in %
	private double productPrice; 	// Original Price
	private int numberInCart;
	private int consumption;
	private double bulkDiscount;
	private int bulkAmount;
	private String supplierName;


	public Product (int productId, String productName, double productPrice, int productStock, String supplierName) {
		this.productId = productId;
		this.productName = productName;
		this.productDetails = "";			//Set as when needed
		this.productStock = productStock;	
		this.productPrice = productPrice;
		this.numberInCart = 0;
		this.consumption = 0;
		this.bulkDiscount = 0;
		this.bulkAmount = 0;
		this.productDiscount = 1;
		this.supplierName = supplierName;
	}

	public int getProductId() {
		return productId;
	}

	public String getSupplierName(){
		return supplierName;
	}

	/*If required to get productName based on given ID */
	public String getProductName() {
		return productName;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductName(String newName){
		productName = newName;
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

	public double getbulkDiscount() {
		return bulkDiscount;
	}

	public void setbulkDiscount(double discount) {
		this.bulkDiscount = discount;
	}

	public int getBulkAmount() {
		return bulkAmount;
	}

	public void setBulkAmount(int amount) {
		this.bulkAmount = amount;
	}

	public double getDiscountPrice() {
		return productPrice * productDiscount;
	}

	public void setDiscountPrice(double price) {
		if (price > 0 && price <= 1) {
			this.productDiscount = price;
		}
		else {
			System.out.println("Error: Input must be between 0 and 1");
		}
	}
	// Daniel Made. not needed for now
	//	public double getDiscountAmount() {
	//		return this.productDiscount;
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

	public int getConsumption()	{
		return consumption;
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
