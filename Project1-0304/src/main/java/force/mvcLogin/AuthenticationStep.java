package force.mvcLogin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AuthenticationStep {
	AuthenticationStep next;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public void setNext(AuthenticationStep next) {
		this.next = next;
	}

	public abstract void process();

	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
	}
	
	

}
