package force.commands;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import force.TransferObjects.Reimbursement;

public class ViewReimbursementsByStatusCommand extends UserCommand {

	@Override
	public void process() {

		// make the call to the database with the status id
		try {
			int status = Integer.parseInt(request.getParameter("reimbStatusId"));
			ArrayList<Reimbursement> result = admin.viewReimbursementsByStatus(status,user.ersUsersId);

			// convert the result to json
			Gson gson = new GsonBuilder().serializeNulls().create();
			String json = gson.toJson(result);

			// send it back to the requester
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// log the event
			logger.info("The database was queried");

			response.getWriter().write(json);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
}
