package logic;

import java.io.Serializable;

// This class is used to create objects that
// represent users in a system. It contains properties such as `id`, `firstName`, `lastName`,
// `userName`, `password`, `isLogged`, `role`, and `flag`, as well as methods to get and set these
// properties.

public class Users implements Serializable {

	private  int id;
	private String firstName;
	private String lastName;
	private String  userName;
	private String  password;
	private int isLogged ;
	private int role ;
	int flag=0;

	public Users (int id, String firstName, String lastName, String userName, String password, int isLogged, int role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isLogged = isLogged;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(int isLogged) {
		this.isLogged = isLogged;
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
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}



}
