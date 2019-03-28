package force.commands;

import java.io.IOException;

public class UnknownCommand extends UserCommand {

	@Override
	public void process() {
		logger.info("A user tried to execute an unknown command");
		try {
			response.sendRedirect("/Project1-0304/HTML/login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e);
		}
	}

}
