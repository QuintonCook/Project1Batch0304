package force.adminView;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import force.TransferObjects.Employee;
import force.commands.UnknownCommand;
import force.commands.UserCommand;

public class AdminHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if there is no current session, send the user to the login screen
		// also make sure that they are an admin
		Employee e = (Employee) request.getSession(false).getAttribute("employee");
		if (request.getSession(false) == null && e.userRoleId == Employee.ADMINROLEID) {
			// initialize the command off of the request parameters
			UserCommand command = getCommand(request);
			command.init(getServletContext(), request, response);
			command.process();
		} else {
			response.sendRedirect("/HTML/login.html");
		}
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	private UserCommand getCommand(HttpServletRequest request) {
		// complex block of code that will create a new command object based on a
		// request parameter
		try {
			Class<?> type = Class.forName(String.format("force.commands.%sCommand", request.getParameter("command")));
			return (UserCommand) type.asSubclass(UserCommand.class).newInstance();
		} catch (Exception e) {
			return new UnknownCommand();
		}
	}

}
