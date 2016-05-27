package INU_10_system_logowania;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginBox {
	
	// fields
	private Stage stage;
	private ChoiceBox<Environment> environmentChoiceBox;	
	private ComboBox<User> usrListComoBox;
	private Button okBtn;
	private PasswordField passPassField;
	private String activUser = "";
	private boolean status = false;
	
	// method to display LoginBox and set components options
	public boolean show(ObservableList<Environment> envList){
		
		// LoginBox image options
		Image loginImg = new Image(LoginBox.class.getResourceAsStream("login.png"));
		ImageView loginIv = new ImageView(loginImg);
	
		// LoginBox text labels
		Label envLbl = new Label("Srodowisko:");
		Label usrLbl = new Label("Uzytkownik:");
		Label passLbl = new Label("Haslo:");
	
		// LoginBox environment (ChoiceBox) options
		environmentChoiceBox = new ChoiceBox<Environment>();
		environmentChoiceBox.setPrefWidth(170);
		environmentChoiceBox.setItems(envList);
		environmentChoiceBox.setValue(envList.get(0));
		environmentChoiceBox.getSelectionModel().selectedItemProperty().addListener((v,oldEnv,newEnv)-> setLoginBoxEnviroment(newEnv));
	
		// environment users list options
		usrListComoBox = new ComboBox<User>(environmentChoiceBox.getValue().getUserList());
		usrListComoBox.setPrefWidth(170);
		usrListComoBox.setEditable(true);
		usrListComoBox.setPromptText("nazwa uzytkownika");
		usrListComoBox.setVisibleRowCount(3);
		usrListComoBox.setOnAction(e->{
			// need to check if user list value is User object (was chosen by mouse) or String object (was typed in on keyboard)
			Object usrTempObj = usrListComoBox.getValue();
			if (usrTempObj.getClass().equals(activUser.getClass())) { activUser = (String)usrTempObj;}
			else { activUser = usrTempObj.toString();}
			passPassField.setDisable(false);
			System.out.println("!!!! Zmieniona wartosc pola \"Uzytkownik\" na <" + activUser + ">");
		});
		// gets user login name typed in login field
		usrListComoBox.setOnKeyReleased(e->{
			activUser = usrListComoBox.getEditor().getText();
			System.out.println(activUser);
			passPassField.setDisable(false);
		});
	
		// LoginBox password filed options
		passPassField = new PasswordField();
		passPassField.setMaxWidth(170);
		passPassField.setDisable(true);
		passPassField.setPromptText("haslo");
	
		// "OK" button options
		okBtn = new Button("OK");
		okBtn.setMinWidth(130);
		okBtn.setOnAction(e-> {
			usrListComoBox.setValue(new User(activUser));
			activUser = usrListComoBox.getValue().toString();
			checkLoginData(usrListComoBox.getValue().toString(),passPassField.getText());		
		});
		okBtn.setDefaultButton(true);	
		okBtn.setDisable(true);
	
		//"Cancel" button options
		Button cancelBtn = new Button("Anuluj");
		cancelBtn.setMinWidth(130);
		cancelBtn.setOnAction(e-> {
			stage.close();
			System.out.println("ANULOWANO OPERACJE!");
		});
		cancelBtn.setCancelButton(true);
		
		// Region object used for buttons alignment
		Region buttonSpace = new Region();
	
		// LoginBox layout managers (horizontal) : environment, user, password, buttons
		HBox envHbox = new HBox(20,envLbl,environmentChoiceBox);
		envHbox.setAlignment(Pos.CENTER_RIGHT);
		HBox usrHbox = new HBox(20,usrLbl,usrListComoBox);
		usrHbox.setAlignment(Pos.CENTER_RIGHT);
		HBox passHbox = new HBox(20,passLbl,passPassField);
		passHbox.setAlignment(Pos.CENTER_RIGHT);
		HBox buttonsHbox = new HBox(okBtn,buttonSpace,cancelBtn);	
		buttonsHbox.setHgrow(buttonSpace, Priority.ALWAYS);
	
		// LoginBox layout manager (vertical) - put together environment, user, password and buttons layout managers
		VBox vBox = new VBox(envHbox,usrHbox,passHbox,buttonsHbox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(15);
		vBox.setMargin(buttonsHbox, new Insets(50,0,0,0));
		vBox.setMinWidth(300);
	
		// LoginBox main layout manager
		HBox mainHbox = new HBox(50,loginIv,vBox);
		mainHbox.setAlignment(Pos.CENTER);
	
		// scene settings
		Scene scene = new Scene(mainHbox,700,300);
		//if activUser is empty it disable password field, if password field is empty it disable "OK" button
		scene.setOnKeyReleased(e->{
			if (activUser.length() == 0) {
				passPassField.setDisable(true);
				passPassField.setText("");
			}
			else { passPassField.setDisable(false);}
			if(passPassField.getText().length() > 0) {okBtn.setDisable(false);}
			else {okBtn.setDisable(true);}
		});
		
		// stage options
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Okno logowania do systemu");
		stage.setResizable(false);
		stage.showAndWait();
		
		// returns boolean value of "status" (true - user logged in, false - login canceled, user login or/and password incorrect)
		return status;
	}

	// helper method to check login data (login and password)
	private void checkLoginData(String login, String pass) {
		
		CancelMessageBox errorMsgBox = new CancelMessageBox();
		String errorMsgText = "";
		
		if (pass.length() == 0 && login.length() == 0){ 
			errorMsgText = "BLAD! Wprowadz prawidlowy login i haslo!"; 
			errorMsgBox.show(errorMsgText);
			System.out.println(errorMsgText);
			return; }
		
		else if (login.length() == 0){ 
			errorMsgText = "BLAD! Wprowadz poprawny login!"; 
			errorMsgBox.show(errorMsgText);
			System.out.println(errorMsgText);
			return; }
		
		else if (pass.length() == 0){ 
			errorMsgText = "BLAD! Wprowadz poprawne haslo dla uzytkownika <" + login + ">"; 
			errorMsgBox.show(errorMsgText);
			System.out.println(errorMsgText);
			return; }
		
		else if (!login.contains(".")){
			errorMsgText = "BLAD! Niepoprawna nazwa uzytkownika! Uzytkownik <" + login + "> nie istnieje"; 
			errorMsgBox.show(errorMsgText);
			System.out.println(errorMsgText);
			return;
		}
		
		else if(login.contains(".")) {
			String msgText = "OK! Uzytkonik <" + login + "> zalogowany do systemu"; 
			OkMessageBox okMsg = new OkMessageBox();
			okMsg.show(msgText);
			System.out.println(msgText);
			status = true;
			stage.close();
		}
	}
	
	// helper method to set login box environment (set users list and reset the login box settings to default)
	public void setLoginBoxEnviroment(Environment env){
		
		environmentChoiceBox.setValue(env);
		usrListComoBox.setItems(env.getUserList());
		okBtn.setDisable(true);
		passPassField.clear();
		passPassField.setDisable(true);
	}
	// getter method to get login box status (true - user logged in, false - login canceled, user login or/and password incorrect)
	public boolean getStatus(){ return status;}
}
