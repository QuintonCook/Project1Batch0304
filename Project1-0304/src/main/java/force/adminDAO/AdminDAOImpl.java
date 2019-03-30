package force.adminDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import force.TransferObjects.Reimbursement;

public class AdminDAOImpl implements AdminDAO {

	Connection conn;

	public AdminDAOImpl() {

		try {
			// sets up the JDBC driver!
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
	public ArrayList<Reimbursement> viewReimbursementsByStatus(int status, int ersUsersId) {

		ResultSet rs = null;
		ArrayList<Reimbursement> result = null;
		String statement1 = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = ? AND REIMB_AUTHOR <> ?";
		String statement2 = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR <> ?";

		try {
			PreparedStatement cStmt = null;

			// either select reimb's with a specific status, or get them all
			if (status >= 0) {
				cStmt = conn.prepareStatement(statement1);
				cStmt.setInt(1, status);
				cStmt.setInt(2, ersUsersId);
			} else {
				cStmt = conn.prepareStatement(statement2);
				cStmt.setInt(1, ersUsersId);
			}

			rs = cStmt.executeQuery();

			// move the pointer to the first row, if this works then we can initialize the
			// result array list
			rs.next();

			result = new ArrayList<Reimbursement>();

			// populate the result array
			do {
				Reimbursement tmp = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBlob(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));

				result.add(tmp);
			} while (rs.next());

		} catch (SQLException e) {
			// If something goes wrong, log it and return null
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int updateReimbursement(int status, int adminId, int reimbId) {

		String statement = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ? WHERE REIMB_ID = ?";
		int result = -1;

		try {
			PreparedStatement cStmt = conn.prepareStatement(statement);

			cStmt.setInt(1, status);
			cStmt.setInt(2, adminId);
			cStmt.setInt(3, reimbId);

			result = cStmt.executeUpdate();

		} catch (SQLException e) {
			// return failure and print the stack trace
			e.printStackTrace();
		}

		return result;
	}

}
