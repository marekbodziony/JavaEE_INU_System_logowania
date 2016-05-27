package INU_10_system_logowania;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Test extends Application {

	// users (p - production user, t - test user, d - development user)
	private User p1 = new User("Marek","Bodziony");
	private User p2 = new User("Karol","Kwiatkowski");
	private User p3 = new User("Monika","Baranska");
	private User t1 = new User("Robert","Sroka");
	private User t2 = new User("Tomek","Cichocki");
	private User t3 = new User("Milosz","Izworski");
	private User t4 = new User("Jan","Kowalski");
	private User d1 = new User("Anna","Majoch");
	private User d2 = new User("Przemek","Bak");
	
	// lists of users
	private ObservableList<User> prodUsrList = FXCollections.observableArrayList();
	private ObservableList<User> testUsrList = FXCollections.observableArrayList();
	private ObservableList<User> devUsrList = FXCollections.observableArrayList();
	
	// environments
	private Environment prod = new Environment("http://www.o2.pl/prod","prod",22,"Produkcyjne",prodUsrList);
	private Environment test = new Environment("http://www/o2.pl/test","test",33,"Testowe",testUsrList);
	private Environment dev = new Environment("http://www.o2.pl/dev","dev",13,"Developerskie",devUsrList);
	
	// list of environments
	private ObservableList<Environment> envList = FXCollections.observableArrayList();
	
	// start method	
	public void start(Stage primaryStage){
		
		// fills user list with users
		prodUsrList.addAll(p1,p2,p3);
		testUsrList.addAll(t1,t2,t3,t4);
		devUsrList.addAll(d1,d2);
		
		// fills environment list with environments
		envList.addAll(prod,test,dev);
		
		// create and displays LoginBox
		LoginBox sys = new LoginBox();
		sys.show(envList);
		
		// checks LoginBox status and displays MessageBox (with specific image and message)
		boolean status = sys.getStatus();
		if (status){
			OkMessageBox okMessBox = new OkMessageBox();
			okMessBox.show("Ok! Poprawnie zalogowano do systemu");
		}
		else{
			CancelMessageBox cancelMessBox = new CancelMessageBox();
			cancelMessBox.show("UWAGA! Anulowano proces logowania");		
		}
		
		
	}
	// main method
	public static void main(String[] args){
		launch(args);
	}
}
