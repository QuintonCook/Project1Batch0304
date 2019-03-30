package force.mvcLogin;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import force.TransferObjects.Employee;
import force.authenticateDAO.AuthenticateDAOImpl;

public class Authenticate extends AuthenticationStep {

	@Override
	public void process() {
		// get the parameters from the post request body
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			// check the credentials against the database
			AuthenticateDAOImpl login = new AuthenticateDAOImpl();
			Employee placeHolder = login.authenticate(userName, password);

			if (placeHolder != null) {
				// initialize a session object for the employee associate the employee object
				// with the session
				HttpSession session = request.getSession();
				session.setAttribute("employee", placeHolder);

				// log the event
				logger.info("A user logged in");

				// pass the session to the next node to generate the view
				next.init(context, request, response, session);
				next.process();
			} else {
				// if the user was unable to authenticate, redirect them back to the login page
				response.sendRedirect("/Project1-0304/HTML/login.html?authentication=failed");
			}

		} catch (IOException e) {
			logger.warn(e);
		} catch (Exception e) {
			logger.warn(e);
		}

	}

}
