package logic;

import java.io.Serializable;

// This class is used to represent
// a logged-in user and contains properties such as `id`, `firstName`, `lastName`, `userName`, and
// `role`. It also has getter and setter methods for these properties.

public class LoggedUsers implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private int role;

	public LoggedUsers(int id, String firstName, String lastName, String userName, int role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;

		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

}