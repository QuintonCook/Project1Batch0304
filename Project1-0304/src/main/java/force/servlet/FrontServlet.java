package force.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import force.commands.UnknownCommand;
import force.commands.UserCommand;

public class FrontServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LogManager.getLogger(FrontServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("A post request was recieved");
		// initialize the command off of the request parameters
		UserCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.process();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// initialize the command off of the request parameters
		UserCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.process();
	}

	@Override
	public void init() throws ServletException {
		logger.info("Servlet " + this.getServletName() + " has started");
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
