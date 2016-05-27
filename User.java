package INU_10_system_logowania;

public class User {
	
	// fields
	private String firstName;
	private String lastName;
	private String login;
	
	// constructor (take first name and last name)
	public User(String fName, String lName){
		firstName = fName;
		lastName = lName;
		login = fName + "." + lName;
	}
	// constructor (takes login name)
	public User(String userName){
			login = userName;
	}
	
	// override method toString() to display user login name
	@Override
	public String toString(){
		return login.toLowerCase();
	}

}
