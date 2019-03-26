package force.authenticateDAO;

import force.TransferObjects.ConnectionInformation;
import force.TransferObjects.Employee;

public interface AuthenticateDAO extends ConnectionInformation {
	Employee authenticate(String username, String password);
}
