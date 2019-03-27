package force.commands;

import force.mvcLogin.AdminView;
import force.mvcLogin.Authenticate;
import force.mvcLogin.AuthenticationStep;
import force.mvcLogin.EmployeeView;

public class LoginCommand extends UserCommand {

	@Override
	public void process() {
		//initialize the login chain
		AuthenticationStep step1 = new Authenticate();
		AuthenticationStep step2 = new EmployeeView();
		AuthenticationStep step3 = new AdminView();
		step1.setNext(step2);
		step2.setNext(step3);
		step1.init(context, request, response);
		
		//begin the chain
		step1.process();
	}

}
