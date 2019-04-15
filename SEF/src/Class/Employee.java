
public class Employee {
	private String name;
	private int number;
	private String password;
	private int level;

	public Employee(int staffID, String staffName, String staffPassword, int staffLevel) {
		number = staffID;
		name = staffName;
		password = staffPassword;
		level = staffLevel; // this possiblyy doesn't need a setter
	}

	public void SetName(String newName) {
		name = newName;
		return;

	}// end SetName

	public void SetNumber(int newNumber) {
		number = newNumber;
		return;

	}// end SetNumber

	public void SetPassword(String newPassword) {
		password = newPassword;
		return;

	}// end SetPassword

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

	public int GetID() {
		return number;

	}// end GetID

	public String GetPassword() {
		return password;

	}// end GetPassword

	public int GetLevel() {
		return level;
	}// end GetLevel
}
