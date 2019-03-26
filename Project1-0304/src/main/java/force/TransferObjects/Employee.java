package force.TransferObjects;

public class Employee {
	public int ersUsersId;
	public String ersUserName;
	public String password;
	public String userFirstName;
	public String userLastName;
	public String userEmail;
	public int userRoleId;
	public static final int ADMINROLEID = 1;
	public static final int EMPLOYEEROLEID = 0;
	
	public Employee(int ersUsersId, String ersUserName, String password, String userFirstName, String userLastName,
			String userEmail, int userRoleId) {
		super();
		this.ersUsersId = ersUsersId;
		this.ersUserName = ersUserName;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}
	
	
}
