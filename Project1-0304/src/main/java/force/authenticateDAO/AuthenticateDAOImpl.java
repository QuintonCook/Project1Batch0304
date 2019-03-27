package force.authenticateDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import force.TransferObjects.Employee;

public class AuthenticateDAOImpl implements AuthenticateDAO {

	Connection conn;

	public AuthenticateDAOImpl() {
		try {
			//sets up the JDBC driver!
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			// initiates the connection to the database
			conn = DriverManager.getConnection(url, username, passwordDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Employee authenticate(String userName, String password) {
		// get the parameters from the post request body
		String statement = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?";
		Employee authenticated = null;

		try {
			// put the username and password into the database query
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setString(1, userName);
			ps.setString(2, password);

			// get the result and create a new employee object if possible
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				authenticated = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7));
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return authenticated;

	}
}
