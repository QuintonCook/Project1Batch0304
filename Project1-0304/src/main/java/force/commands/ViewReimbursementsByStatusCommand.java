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
			int status = (int) request.getAttribute("reimbStatusId");
			ArrayList<Reimbursement> result = admin.viewReimbursementsByStatus(status);

			// get the current users employee number. If they have any outstanding requests,
			// remove them
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

			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			//TODO log the fact that an employee with a certian ID tried to send admin commands
		}
	}

}
