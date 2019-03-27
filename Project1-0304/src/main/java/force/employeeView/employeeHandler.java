package force.employeeView;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import force.commands.UnknownCommand;
import force.commands.UserCommand;

public class EmployeeHandler extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("IM HIT");
		//if there is no current session, send the user to the login screen
		if(request.getSession(false) == null) {
			UserCommand command = getCommand(request);
			command.init(getServletContext(), request, response);
			command.process();
		}else {
			response.sendRedirect("/HTML/login.html");
		}		
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");		
	}

	private UserCommand getCommand(HttpServletRequest request) {
		try {
			Class<?> type = Class.forName(String.format("force.commands.%sCommand",
					request.getParameter("command")));
			return (UserCommand) type.asSubclass(UserCommand.class).newInstance();
		} catch (Exception e) {
			return new UnknownCommand();
		}
	}

	
}
