package INU_10_system_logowania;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class MessageBox {

	// method to display MessageBox with specific message and image
	public void show(String message){
		
		// stage declaration
		Stage stage = new Stage();
		
		// image settings
		String imgPath = getImagePath();
		Image img = new Image(MessageBox.class.getResourceAsStream(imgPath));
		ImageView imgView = new ImageView(img);
		
		// message label settings 
		Label messageLbl = new Label(message);
		messageLbl.setPrefWidth(300);
		messageLbl.setWrapText(true);
		
		// "OK" button settings 
		Button okBtn = new Button("OK");
		okBtn.setPrefWidth(100);
		okBtn.setOnAction(e->stage.close());
		okBtn.setDefaultButton(true);
		
		// layout managers
		HBox hBox = new HBox(50,imgView, messageLbl);
		hBox.setAlignment(Pos.CENTER);
		VBox vBox = new VBox(30,hBox,okBtn);
		vBox.setAlignment(Pos.CENTER);
		
		// scene settings
		Scene scene = new Scene(vBox,500,200);
		scene.setOnKeyReleased(e->{
			if (e.getCode() == KeyCode.ESCAPE){ stage.close();}
		});
		
		// stage settings
		stage.setScene(scene);
		stage.setTitle("Komunikat");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	// abstract method (used in subclass to get image for MessageBox)
	public abstract String getImagePath();
}
