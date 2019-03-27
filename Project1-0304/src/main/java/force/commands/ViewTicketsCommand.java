package force.commands;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import force.TransferObjects.Reimbursement;

public class ViewTicketsCommand extends UserCommand {

	@Override
	public void process() {
		
		//get the reimbursements from the database
		ArrayList<Reimbursement> result = employee.viewReimbursements(user.ersUsersId);
		
		// convert the result to json
		String json = new Gson().toJson(result);

		// send it back to the requester
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//log the event
		logger.info("The database was queried");
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e);
		}catch (NullPointerException e) {
			logger.warn(e);
		}

	}

}
