package force.commands;

import java.io.IOException;

public class LogoutCommand extends UserCommand {

	@Override
	public void process() {
		// sends the ersUsersId back to the requester
		sesh.invalidate();
		try {
			response.sendRedirect("/Project1-0304/HTML/login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
