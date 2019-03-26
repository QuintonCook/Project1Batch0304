package force.Test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import force.TransferObjects.Reimbursement;
import force.adminDAO.AdminDAOImpl;
import force.employeeDAO.EmployeeDAOImpl;

public class DatabaseCallsTest {

	@Before
	public void before() {
		System.out.println("Starting Test...");
	}
	
	@After
	public void after() {
		System.out.println("Test Complete...");
	}
	
//---------------------------------BEGIN ADMIN DAO TESTS-------------------------------	

	@Ignore
	public void updateRequestTest() {

		AdminDAOImpl a = new AdminDAOImpl();
		a.updateReimbursement(1, 2, 22);

		Connection conn;

		String url = "jdbc:oracle:thin:@atworkdb.c05sxxntbuck.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "the_manager1";
		String passwordDB = "P4SSW0RD";

		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, username, passwordDB);
			PreparedStatement cStmt = conn
					.prepareStatement("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSEMENT WHERE REIMB_ID = 21");
			;
			rs = cStmt.executeQuery();

			rs.next();
			
			assertEquals(1, rs.getInt(1));
			a.updateReimbursement(0, 2, 22);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Ignore
	public void updateRequestBadReimbId() {
		AdminDAOImpl a = new AdminDAOImpl();
		assertEquals(0,a.updateReimbursement(1, 2, 666666));
	}
	
	@Ignore
	public void updateRequestBadAdminId() {
		AdminDAOImpl a = new AdminDAOImpl();
		assertEquals(-1, a.updateReimbursement(1, 65656, 22));
	}
	
	@Ignore
	public void updateRequestBadStatusId() {
		AdminDAOImpl a = new AdminDAOImpl();
		assertEquals(-1,a.updateReimbursement(65656565, 2, 22));
	}

	@Ignore
	public void viewAllReimbursements() {
		AdminDAOImpl a = new AdminDAOImpl();

		ArrayList<Reimbursement> result = a.viewReimbursementsByStatus(-1);

		assertEquals(3, result.size());
	}

	@Ignore
	public void viewSpecificReimbursements() {
		AdminDAOImpl a = new AdminDAOImpl();
		ArrayList<Reimbursement> result = a.viewReimbursementsByStatus(1);

		assertEquals(22, result.get(0).reimbId);
	}
	
	@Ignore
	public void viewSpecificReimbursementsBadStatusId() {
		AdminDAOImpl a = new AdminDAOImpl();
		ArrayList<Reimbursement> result = a.viewReimbursementsByStatus(2222);

		System.out.println(result);
		
		assertEquals(0, result.size());

	}

	@Ignore
	public void badReimbursementStatusId() {
		AdminDAOImpl a = new AdminDAOImpl();
		ArrayList<Reimbursement> result = a.viewReimbursementsByStatus(66);

		assertEquals(0, result.size());
	}
//---------------------------------END ADMIN DAO TESTS-------------------------------	
//---------------------------------BEGIN EMPLOYEE DAO TESTS--------------------------

	@Ignore
	public void viewReimbursementsTest() {
		EmployeeDAOImpl e = new EmployeeDAOImpl();

		ArrayList<Reimbursement> result = e.viewReimbursements(1);

		assertEquals(2, result.size());
	}
	
	@Ignore
	public void viewReimbursementsBadEmployeeId() {
		EmployeeDAOImpl e = new EmployeeDAOImpl();

		ArrayList<Reimbursement> result = e.viewReimbursements(-1);

		assertEquals(0, result.size());
	}

	@Ignore
	public void addReimbReq() {
		EmployeeDAOImpl e = new EmployeeDAOImpl();

		Reimbursement rmb = new Reimbursement(55.55,"Cost of Unit Testing",1);

		e.addReimbursementRequest(rmb);

		String url = "jdbc:oracle:thin:@atworkdb.c05sxxntbuck.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "the_manager1";
		String passwordDB = "P4SSW0RD";

		ResultSet rs = null;

		try {
			Connection conn = DriverManager.getConnection(url, username, passwordDB);
			PreparedStatement cStmt = conn
					.prepareStatement("SELECT REIMB_AMMOUNT FROM ERS_REIMBURSEMENT WHERE REIMB_AMMOUNT = 55.55");
			rs = cStmt.executeQuery();
			
			rs.next();
			
			assertEquals(55.55, rs.getDouble(1), .001);
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
	
	@Ignore
	public void addReimbRequestBadEmployeeId() {
		EmployeeDAOImpl e = new EmployeeDAOImpl();

		Reimbursement rmb = new Reimbursement(55.55,"Cost of Unit Testing",666);

		assertEquals(-1,e.addReimbursementRequest(rmb));
	}

}
