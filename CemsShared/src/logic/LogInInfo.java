package logic;
//
///////
////
import java.io.Serializable;

// This class has two private instance
// variables `userName` and `password`, a constructor that initializes these variables, and two getter
// methods to retrieve the values of these variables.

public class LogInInfo implements Serializable  {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;// to avoid warnings
	private String userName;
	private String password;
	public LogInInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}

}
