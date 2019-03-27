package force.mvcLogin;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public abstract class AuthenticationStep {
	protected AuthenticationStep next;
	protected HttpSession authenticated;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	//the logger for all the children to use
	protected final static Logger logger = LogManager.getLogger(AuthenticationStep.class);
	
	public void setNext(AuthenticationStep next) {
		this.next = next;
	}

	//base method that all the children must implement
	public abstract void process();

	//load the step with the servlet context the request and the response and session
	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse, HttpSession authenticated) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
		this.authenticated = authenticated;
	}
	
	//load the step with the servlet context the request and the response
	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
	}
	
	

}
