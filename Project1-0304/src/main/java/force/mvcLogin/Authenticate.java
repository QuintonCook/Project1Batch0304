package force.mvcLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import force.TransferObjects.ConnectionInformation;

public class Authenticate extends AuthenticationStep implements ConnectionInformation{

	Connection conn;

	public Authenticate() {
		try {
			// initiates the connection to the database
			conn = DriverManager.getConnection(url, username, passwordDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void process() {
		String statement = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			PreparedStatement ps = conn.prepareStatement(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
