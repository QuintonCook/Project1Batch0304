package force.adminDAO;

import java.util.ArrayList;

import force.TransferObjects.ConnectionInformation;
import force.TransferObjects.Reimbursement;

public interface AdminDAO extends ConnectionInformation{
	
	//if status is -1 it will return all reimbursements in the database
	ArrayList<Reimbursement> viewReimbursementsByStatus(int status, int ersUsersId);
	int updateReimbursement(int reimbId, int adminId, int status);
}
