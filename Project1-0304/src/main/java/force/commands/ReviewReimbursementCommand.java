package force.commands;

import java.util.Enumeration;

import force.TransferObjects.Employee;

public class ReviewReimbursementCommand extends UserCommand {

	@Override
	public void process() {

		//make sure the request came from an admin
		if (user.userRoleId == Employee.ADMINROLEID) {
			//get the (APPROVE|DENY) parameter from the request
			int updatedStatus = (int) request.getAttribute("newStatus");
			Enumeration<String> params = request.getParameterNames();

			//While the request has the id's of different reimbursements, update the database
			//with the new reimbursement statuses
			while (params.hasMoreElements()) {
				admin.updateReimbursement(updatedStatus, user.ersUsersId,
						(int) request.getAttribute(params.nextElement()));
			}
		}else {
			//TODO log the fact that a user tried to make admin commands
		}
	}

}
