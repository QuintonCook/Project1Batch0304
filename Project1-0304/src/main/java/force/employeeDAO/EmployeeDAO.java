package force.employeeDAO;

import java.util.ArrayList;

import force.TransferObjects.ConnectionInformation;
import force.TransferObjects.Reimbursement;

public interface EmployeeDAO extends ConnectionInformation{

	ArrayList<Reimbursement> viewReimbursements(int reimbAuthor);
	int addReimbursementRequest(Reimbursement rmb);	
}
