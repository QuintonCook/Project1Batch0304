package force.mvcLogin;

import java.io.IOException;

import force.TransferObjects.Employee;

public class EmployeeView extends AuthenticationStep {

	@Override
	public void process() {
		Employee emp = (Employee) authenticated.getAttribute("employee");

		try {

			if (emp.userRoleId == 0) {
				//log the event and redirect the user to the employee page
				logger.info("The user was an employee");
				response.sendRedirect("/Project1-0304/HTML/employee.html");
			} else {
				//if the user was not just an employee send them to the admin page
				next.init(context, request, response, authenticated);
				next.process();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn(e);
		}
	}

}
