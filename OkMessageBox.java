package INU_10_system_logowania;

public class OkMessageBox extends MessageBox{

	// override method getImagePath() to get specific image for OkMessageBox
	@Override
	public String getImagePath() {
		String imgPath = "ok.png";
		return imgPath;
	}
	
}
