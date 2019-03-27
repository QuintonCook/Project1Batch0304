package force.commands;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import force.TransferObjects.Employee;
import force.TransferObjects.Reimbursement;

public class ViewReimbursementsByStatusCommand extends UserCommand {

	@Override
	public void process() {

		if (user.userRoleId == Employee.ADMINROLEID) {
			// make the call to the database with the status id
			try {
				int status = Integer.parseInt(request.getParameter("reimbStatusId"));
				ArrayList<Reimbursement> result = admin.viewReimbursementsByStatus(status);

				// get the current users employee number. If they have any outstanding requests,
				// remove them
				System.out.println(user.ersUsersId);
				for (Reimbursement r : result) {
					if (r.reimbAuthor == user.ersUsersId) {
						result.remove(r);
					}
				}

				// convert the result to json
				String json = new Gson().toJson(result);

				// send it back to the requester
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				
				//log the fact that the database was queried
				logger.info("Database items were retrieved");
				
			} catch (IOException e) {
				logger.warn(e);
			} catch (NumberFormatException e) {
				logger.info("USER DID NOT PUT IN THE STATUS PARAMETER");
			}
		} else {
			// TODO log the fact that an employee with a certian ID tried to send admin
			// commands
			logger.info("A non admin tried to execute an admin command");
		}
	}

}
