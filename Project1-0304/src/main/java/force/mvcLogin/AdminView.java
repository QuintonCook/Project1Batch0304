package force.mvcLogin;

import java.io.IOException;

import force.TransferObjects.Employee;

public class AdminView extends AuthenticationStep {

	@Override
	public void process() {
		Employee emp = (Employee) authenticated.getAttribute("employee");
		try {
			if (emp.userRoleId == 1) {
				response.sendRedirect("/Project1-0304/HTML/admin.html");
			} else {
				response.sendRedirect("/Project1-0304/HTML/login.html?authentication=failed");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
