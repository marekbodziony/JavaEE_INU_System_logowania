package INU_10_system_logowania;

public class CancelMessageBox extends MessageBox{

	// override method getImagePath() to get specific image for CancelMessageBox
	@Override
	public String getImagePath() {
		String imgPath = "cancel.png";
		return imgPath;
	}

}
