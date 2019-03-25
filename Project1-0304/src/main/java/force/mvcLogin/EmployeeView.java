package force.mvcLogin;

public class EmployeeView extends AuthenticationStep {

	@Override
	public void process() {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
	}

}
