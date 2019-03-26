package force.mvcLogin;

import java.io.IOException;

import force.TransferObjects.Employee;

public class EmployeeView extends AuthenticationStep {

	@Override
	public void process() {
		Employee emp = (Employee) authenticated.getAttribute("employee");

		try {

			if (emp.userRoleId == 0) {
				response.sendRedirect("/Project1-0304/HTML/employee.html");
			} else {
				next.init(context, request, response, authenticated);
				next.process();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
