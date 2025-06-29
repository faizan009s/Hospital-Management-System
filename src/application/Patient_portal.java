package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Patient_portal implements Initializable {

    @FXML
    private AnchorPane Login_form;
    
    @FXML
    private AnchorPane Main_form;

    @FXML
    private JFXComboBox<String> Patient_selectAcc;

    @FXML
    private TextField Pt_SignIn;

    @FXML
    private AnchorPane Register_form;

    @FXML
    private TextField pt_Register_username;

    @FXML
    private TextField pt_Register_Email;

    @FXML
    private TextField pt_Register_Name;

    @FXML
    private PasswordField pt_Register_pass;

    @FXML
    private FontAwesomeIconView Patient_cancel;

    @FXML
    private PasswordField pt_showPassword;
    
    private final statusLabel alert = new statusLabel(); // Global alert obje

    private void switchPane(AnchorPane paneToShow) {
    	Register_form.setVisible(false);
    	Login_form.setVisible(false);
        
        paneToShow.setVisible(true);
    }
    @FXML
    private void pt_Register_Btn(ActionEvent event) {
    	 String NAME = pt_Register_Name.getText();
         String email = pt_Register_Email.getText();
         String username = pt_Register_username.getText();
         String password = pt_Register_pass.getText();

         if (NAME.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
             alert.ErrorMessage("Please fill all fields!");
             return;
         }

         String sql = "INSERT INTO patientmain (PATIENT_NAME, Patient_Email, Patient_Username, PATIENT_PASSWORD) VALUES (?, ?, ?, ?)";

         try (Connection conn = DatabaseConnection_new.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, NAME);
             stmt.setString(2, email);
             stmt.setString(3, username);
             stmt.setString(4, password);

             int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0) {
                 alert.SuccesMessage("User registered successfully!");
                 switchPane(Login_form); // Switch to login after registration
             }

         } catch (SQLException e) {
             alert.ErrorMessage("Error: " + e.getMessage());
         }
    }

    @FXML
   private void pt_Sign_Btn(ActionEvent event) {
    	 String username = Pt_SignIn.getText();
         String password = pt_showPassword.getText();

         if (username.isEmpty() || password.isEmpty()) {
             alert.ErrorMessage("Please enter username and password!");
             return;
         }

         String query = "SELECT * FROM patientmain WHERE Patient_Username = ? AND PATIENT_PASSWORD = ?";

         try (Connection conn = DatabaseConnection_new.getConnection();
              PreparedStatement stmt = conn.prepareStatement(query)) {

             stmt.setString(1, username);
             stmt.setString(2, password);

             ResultSet resultSet = stmt.executeQuery();
             if (resultSet.next()) {
            	
                 Data.patient_id = resultSet.getString("PATIENT_ID");
                 
            	 Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                 currentStage.close();

                 Parent root = FXMLLoader.load(getClass().getResource("Patient_Dashboard.fxml"));
     			 Stage stage = new Stage();
 				 stage.setTitle("Hospital Management System | Patient Portal");
                 stage.setScene(new Scene(root));
                 stage.show();
                 
                 //alert.SuccesMessage("Login Successful!");
                 
             } else {
                 alert.ErrorMessage("Invalid Credentials!");
             }

         } catch (Exception e) {
             alert.ErrorMessage("Error: " + e.getMessage());
         }
        
     }
           
    

    @FXML
    void pt_WenttoRegisterpage(ActionEvent event) {
    	  switchPane(Register_form);
    }

    @FXML
    void pt_singntologin(ActionEvent event) {
    	 switchPane(Login_form);
    }
    public void Switch(ActionEvent  event) {
        String selectedPage = (String) Patient_selectAcc.getValue();
        String fxmlFile =" Patient_por.fxml";

        // Determine which FXML file to load
        switch (selectedPage) {
            case "Patient Portal":
               fxmlFile = "Patient_por.fxml";
               break;
            case "Admin Portal":
                fxmlFile = "Start.fxml";
                break;
            case "Doctor Portal":
                fxmlFile = "Doctor_por.fxml";
                break;
            
            default:
                return;
        }

        // Load the new page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) Patient_selectAcc.getScene().getWindow();
                if (stage != null) {
                    stage.hide();
                }
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void SelectUser() {
    	
    	
		List<String> listU = new ArrayList<>();
		
		for(String data : Users.users) {
			listU.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listU);
		Patient_selectAcc.setItems(listData);
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SelectUser();
		// TODO Auto-generated method stub
		Patient_cancel.setOnMouseClicked(event -> {
	        System.exit(0);
	        
				});
	
	}

}
	