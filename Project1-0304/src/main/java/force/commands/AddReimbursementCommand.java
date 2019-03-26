package force.commands;

import force.TransferObjects.Reimbursement;

public class AddReimbursementCommand extends UserCommand {

	@Override
	public void process() {
		//get the request parameters
		double ammount = (double)request.getAttribute("ammount");
		String description = (String)request.getAttribute("description");
		
		//create a reimbursement object
		Reimbursement rmb = new Reimbursement(ammount,description,user.ersUsersId);
		
		//send it to the database
		employee.addReimbursementRequest(rmb);
	}

}
