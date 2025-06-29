package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class statusLabel {
           
	public Alert alert;
	
	public void ErrorMessage(String Message) {
		alert =new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText(Message);
		alert.show();
	}
	
	public void SuccesMessage(String Message) {
		alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Message");
		alert.setHeaderText(null);
		alert.setContentText(Message);
		alert.show();

	}

	public boolean confirmationMessage(String message) {
		alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation");
	    alert.setHeaderText(null);
	    alert.setContentText(message);

	    Optional<ButtonType> result = alert.showAndWait();
	    return result.isPresent() && result.get() == ButtonType.OK;
	}

}


