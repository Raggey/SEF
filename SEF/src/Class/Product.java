package Class;

public class Product extends mainsef {
	
	private int productId;
	private String productName;
	private String productDetails;
	private int productStock;
	private double productDiscount;
	private double producPrice;
	
	public Product (int productId, String productName, double productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productDetails = "";			//Set as when needed
		this.productStock = 0;				//Updated by Supplier
		this.producPrice = productPrice;
		//this.productDiscount = 0;			//No discount initially
	}

	public int getProductId() {
		return productId;
	}

	/*If required to get productName based on given ID */
//	public String getProductName() {
//		return productName;
//	}

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
		return producPrice;
	}

	public void setProductPrice(double producPrice) {
		this.producPrice = producPrice;
	}
	
	public void reduceStock(int i) {
		this.productStock -= i;
	}

	public void increaseStock(int i) {
		this.productStock += i;
	}
	

	
}
