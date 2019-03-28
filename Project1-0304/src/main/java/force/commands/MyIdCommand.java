package force.commands;

import java.io.IOException;

public class MyIdCommand extends UserCommand {

	@Override
	public void process() {
		// sends the ersUsersId back to the requester
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(Integer.toString(user.ersUsersId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e);
		}
	}

}
