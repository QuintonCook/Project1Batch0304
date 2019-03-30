package force.commands;

import java.util.Enumeration;

public class ReviewReimbursementCommand extends UserCommand {

	@Override
	public void process() {

		// get the (APPROVE|DENY) parameter from the request

		try {
			int updatedStatus = Integer.parseInt(request.getParameter("newStatus"));
			Enumeration<String> params = request.getParameterNames();

			// While the request has the id's of different reimbursements, update the
			// database
			// with the new reimbursement statuses
			while (params.hasMoreElements()) {

				// get the parameter from the request
				String token = params.nextElement();

				// if it is a reimbursement id, update the database record
				if (token.contains("id")) {
					admin.updateReimbursement(updatedStatus, user.ersUsersId,
							Integer.parseInt(request.getParameter(token)));

					// log the event
					logger.info("reimbursement " + request.getParameter(token) + " has been updated");
				}
			}

		} catch (NumberFormatException e) {
			logger.info("USER DID NOT INCLUDE THE newStatus parameter");
		} catch (Exception e) {
			logger.info("Parameter was not a id");
		}
	}
}
