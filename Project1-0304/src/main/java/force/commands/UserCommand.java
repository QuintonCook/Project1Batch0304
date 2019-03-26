package force.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import force.TransferObjects.Employee;
import force.adminDAO.AdminDAOImpl;
import force.employeeDAO.EmployeeDAOImpl;

public abstract class UserCommand {

	protected AdminDAOImpl admin;
	protected EmployeeDAOImpl employee;
	private HttpSession sesh;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Employee user;
	
	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
		sesh = request.getSession();
		
		//get the user object from the session
		user = (Employee)sesh.getAttribute("employee");
	}

	public abstract void process();

}
