package force.mvcLogin;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import force.TransferObjects.Employee;
import force.authenticateDAO.AuthenticateDAOImpl;

public class Authenticate extends AuthenticationStep {

	@Override
	public void process() {
		// get the parameters from the post request body
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		System.out.println("MADE IT TO THE PROCESS IN AUTHENTICATE");
		// check the credentials against the database
		AuthenticateDAOImpl login = new AuthenticateDAOImpl();
		Employee placeHolder = login.authenticate(userName, password);
		
		try {
			
			if (placeHolder != null) {
				// initialize a session object for the employee associate the employee object
				// with the session
				HttpSession session = request.getSession();
				session.setAttribute("employee", placeHolder);
				
				//log the event
				logger.info("A user logged in");

				// pass the session to the next node to generate the view
				next.init(context, request, response, session);
				next.process();
			} else {
				System.out.println("MADE IT TO THE REDIRECT");
				// if the user was unable to authenticate, redirect them back to the login page
				response.sendRedirect("/Project1-0304/HTML/login.html?authentication=failed");
			}

		} catch (IOException e) {
			logger.warn(e);
		}

	}

}
