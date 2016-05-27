package INU_10_system_logowania;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
	
	// fields
	private String url;
	private String sysId;
	private int port;
	private String name;
	private ObservableList<User> usrList = FXCollections.observableArrayList();
	
	// constructor
	public Environment(String url, String sysId, int port, String name, ObservableList<User> usrList){
		this.url = url;
		this.sysId = sysId;
		this.port = port;
		this.name = name;
		this.usrList = usrList;
	}
	// getter method that gets environment users list
	public ObservableList<User> getUserList(){return usrList;}
	
	// override method toString() to display environment name
	@Override
	public String toString(){
		return name;
	}

}
