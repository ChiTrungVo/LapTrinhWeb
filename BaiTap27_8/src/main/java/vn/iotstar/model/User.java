package vn.iotstar.model;
import java.io.Serializable;

public class User {
	private int id;
	  private String userName;
	  private String pass;
	  private String fullName;

	  public User() {}

	  // getters & setters
	  public int getId() { return id; }
	  public void setId(int id) { this.id = id; }

	  public String getUserName() { return userName; }
	  public void setUserName(String userName) { this.userName = userName; }

	  public String getPass() { return pass; }
	  public void setPass(String pass) { this.pass = pass; }

	  public String getFullName() { return fullName; }
	  public void setFullName(String fullName) { this.fullName = fullName; }
}
