package Class;


public class Employee {
	private String name;
	private String number;
	private String password;
	private int level;

	public Employee(String staffID, String staffName, String staffPassword, int staffLevel) {
		number = staffID;
		name = staffName;
		password = staffPassword;
		level = staffLevel; // this possibly doesn't need a setter
	}
	
	// Level 1 = SalesStaff,
	// Level 2 = WareHouse,
	// Level 3 = Manager

	public void SetName(String newName) {
		name = newName;
		return;

	}// end SetName

	public void SetNumber(String newNumber) {
		number = newNumber;
		return;

	}// end SetNumber

	public void SetPassword(String newPassword) {
		password = newPassword;
		return;

	}// end SetPassword

	public void SetLevel(int newLevel){
		level = newLevel;
	}
	// this needs more work
	public boolean Login(String CheckPass) {
		if (CheckPass == password) {
			return true;
		}
		return false;

	}// end Login

	public String GetName() {
		return name;

	}// end GetName

	public String GetID() {
		return number;

	}// end GetID

	public String GetPassword() {
		return password;

	}// end GetPassword

	public int GetLevel() {
		return level;
	}// end GetLevel
}
