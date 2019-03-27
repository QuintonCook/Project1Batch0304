package force.Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import force.commands.UnknownCommand;
import force.commands.UserCommand;

public class ControllerTest extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if there is no current session, send the user to the login screen
		// also make sure that they are an admin
		
		System.out.println("IM HIT");

		try {
			System.out.println("MADE IT HERE");
				UserCommand command = getCommand(request);
				System.out.println("Got Command");
				command.initForTesting(getServletContext(), request, response);
				System.out.println("OUT OF INIT");
				command.process();
		} catch (NullPointerException e) {
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
			e.printStackTrace();
			return new UnknownCommand();
		}
	}
	
}
