package force.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import force.TransferObjects.Employee;
import force.adminDAO.AdminDAOImpl;
import force.employeeDAO.EmployeeDAOImpl;

public abstract class UserCommand {

	protected AdminDAOImpl admin;
	protected EmployeeDAOImpl employee;
	protected HttpSession sesh;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Employee user;
	final static Logger logger = LogManager.getLogger(UserCommand.class);

	public void initForTesting(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {

		// user = new Employee(1, "Quinton", "Quinton", "Cook", "1234",
		// "quinton@email.com", 0);
		user = new Employee(2, "Yousef", "Yousef", "Muhammed", "1234", "yousef@email.com", 1);
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;

		sesh = request.getSession();
		// admin = new AdminDAOImpl();
		// employee = new EmployeeDAOImpl();
		System.out.println("INITIALIZED THE USER COMMAND FOR TESTING");
	}

	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		//store the request context and response and request
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
		sesh = request.getSession();

		// get the user object from the session
		user = (Employee) sesh.getAttribute("employee");

		//initialize our connection to the database
		admin = new AdminDAOImpl();
		employee = new EmployeeDAOImpl();
	}
	
	//a method that all the children must implement
	public abstract void process();

}
