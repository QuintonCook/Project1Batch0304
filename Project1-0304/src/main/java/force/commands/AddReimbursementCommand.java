package force.commands;

import force.TransferObjects.Reimbursement;

public class AddReimbursementCommand extends UserCommand {

	@Override
	public void process() {
		try {
			// get the request parameters
			double ammount = Double.parseDouble(request.getParameter("ammount"));
			String description = request.getParameter("description");

			// create a reimbursement object
			Reimbursement rmb = new Reimbursement(ammount, description, user.ersUsersId);

			// send it to the database
			employee.addReimbursementRequest(rmb);
			logger.info("A reimbursement was added");

			switch (user.userRoleId) {
			case 0:
				response.sendRedirect("/Project1-0304/HTML/employee.html");
				break;

			case 1:
				response.sendRedirect("/Project1-0304/HTML/admin.html");
				break;

			default:
				response.sendRedirect("/Project1-0304/HTML/login.html");
				break;
			}

		} catch (Exception e) {
			logger.warn(e);
		}
	}

}
