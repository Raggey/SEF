package Class;

public class supplier {
	
	private int supplierID;
	private String supplierName;
	
	public supplier (int supplierID, String supplierName) {
		this.supplierID = supplierID;
		this.supplierName = supplierName;
	}
	
	public void SetSupplierID(int newID) {
		supplierID = newID;
		return;
	}//end setSupplierID
	
	public void SetSupplierName(String newName) {
		supplierName = newName;
		return;
	}//end setSupplierName
	
	public int GetSupplierID() {
		return supplierID;
	}//end getSupplieriD

	public String GetSupplierName() {
		return supplierName;
	}//end getSupplierName
	
}
