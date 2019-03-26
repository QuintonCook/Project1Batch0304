package force.mvcLogin;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	AuthenticationStep start;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        start.init(getServletContext(), request, response);
        start.process();

	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
		System.out.println("Initializing Authentication Chain");
		AuthenticationStep step1 = new Authenticate();
		AuthenticationStep step2 = new EmployeeView();
		AuthenticationStep step3 = new AdminView();
		
		start = step1;
		step1.setNext(step2);
		step2.setNext(step3);
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}

}
