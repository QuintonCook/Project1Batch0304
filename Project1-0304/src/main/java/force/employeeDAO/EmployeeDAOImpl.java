package force.employeeDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import force.TransferObjects.Reimbursement;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection conn;

	public EmployeeDAOImpl() {
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
	public int addReimbursementRequest(Reimbursement rmb) {
		String statement = "{call INSERT_REIMBURSEMENT(?,?,?)}";
		int result = -1;

		try {
			CallableStatement cStmt = conn.prepareCall(statement);

			cStmt.setDouble(1, rmb.reimbAmmount);
			cStmt.setString(2, rmb.description);
			cStmt.setInt(3, rmb.reimbAuthor);

			result = cStmt.executeUpdate();
			
		} catch (SQLException e) {
			// return failure print stack trace
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<Reimbursement> viewReimbursements(int reimbAuthor) {
		String statement = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
		ArrayList<Reimbursement> result = null;

		try {
			// setup the query and execute
			PreparedStatement ps = conn.prepareCall(statement);
			ps.setInt(1, reimbAuthor);
			ResultSet rs = ps.executeQuery();

			// if there are any problems with the result set, catch them so that the
			// arrayList returns null
			rs.next();

			result = new ArrayList<Reimbursement>();

			// populate the result array
			do {
				Reimbursement tmp = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));

				result.add(tmp);
			} while (rs.next());

		} catch (SQLException e) {
			// if something goes wrong, log it and return null
			System.out.println("WOO");
		}

		return result;
	}

}
