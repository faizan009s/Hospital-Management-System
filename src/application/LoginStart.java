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
import javafx.scene.layout.StackPane;

public class LoginStart implements Initializable{
	
	@FXML
    private FontAwesomeIconView AdminCancelBtn;

    @FXML
    private StackPane AdminStack_pane;
    @FXML
    private AnchorPane AdminRegister_form, AdminLogin_form;
    @FXML
    private TextField Register_Admin_Name, RegisterAdmin_Email, RegisterAdmin_UserName;
    @FXML
    private PasswordField RegisterAdmin_Cpass, Admin_showPassword, AdminForget_pass, AdminForget_CPass;
    @FXML
    private TextField Admin_SignIn;
    
    @FXML
    private JFXComboBox<String> Admin_selectAcc;
    

    private final statusLabel alert = new statusLabel(); // Global alert object

    /**
     * Switch between AnchorPane views inside the StackPane
     */
    private void switchPane(AnchorPane paneToShow) {
        AdminRegister_form.setVisible(false);
        AdminLogin_form.setVisible(false);
        
        paneToShow.setVisible(true);
    }

    /**
     * Handles user registration.
     */
    @FXML
    private void Register(ActionEvent event) {
        String NAME = Register_Admin_Name.getText();
        String EMAIL = RegisterAdmin_Email.getText();
        String USERNAME = RegisterAdmin_UserName.getText();
        String PASSWORD = RegisterAdmin_Cpass.getText();

        if (NAME.isEmpty() || EMAIL.isEmpty() || USERNAME.isEmpty() || PASSWORD.isEmpty()) {
            alert.ErrorMessage("Please fill all fields!");
            return;
        }

        String sql = "INSERT INTO MAINADMINLOGIN (ADMIN_NAME, ADMIN_EMAIL, ADMIN_USERNAME, ADMIN_PASSWORD) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection_new.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, NAME);
            stmt.setString(2, EMAIL);
            stmt.setString(3, USERNAME);
            stmt.setString(4, PASSWORD);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                alert.SuccesMessage("User registered successfully!");
                switchPane(AdminLogin_form); // Switch to login after registration
            }

        } catch (SQLException e) {
            alert.ErrorMessage("Error: " + e.getMessage());
        }
    }

    /**
     * Handles user login.
     */
    @FXML
    private void Login(ActionEvent event) {
        String USERNAME = Admin_SignIn.getText();
        String PASSWORD = Admin_showPassword.getText();

        if (USERNAME.isEmpty() || PASSWORD.isEmpty()) {
            alert.ErrorMessage("Please enter username and password!");
            return;
        }
        String sql = "SELECT * FROM MAINADMINLOGIN WHERE ADMIN_USERNAME = ? AND ADMIN_PASSWORD = ?";

        try (Connection conn = DatabaseConnection_new.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, USERNAME);
            stmt.setString(2, PASSWORD);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
            	 Data.AD_USERNAME = USERNAME;
                 Data.AD_ID = Integer.parseInt(resultSet.getString("ADMIN_ID"));
                 
                 //alert.SuccesMessage("Login Successful!");
                 
                 Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                 currentStage.close();

                 Parent root = FXMLLoader.load(getClass().getResource("AdminSecondPage.fxml"));
     			 Stage stage = new Stage();
 				 stage.setTitle("Hospital Management System | Admin Portal");
                 stage.setScene(new Scene(root));
                 stage.show();
                 
            } else {
                alert.ErrorMessage("Invalid Credentials!");
            }

        } catch (Exception e) {
            alert.ErrorMessage("Error: " + e.getMessage());
        }
    }
        
    @FXML
    private void Admin_registeraccount() {
        switchPane(AdminRegister_form);
    }
    @FXML
    private void BackToLogin() {
        switchPane(AdminLogin_form);
    }

    /**
     * Navigate back to login.
     */
    public void Switch(ActionEvent  event) {
        String selectedPage = (String) Admin_selectAcc.getValue();
        String fxmlFile =" Start.fxml";

        // Determine which FXML file to load
        switch (selectedPage) {
            case "Admin Portal":
               fxmlFile = "Start.fxml";
               break;
            case "Patient Portal":
                fxmlFile = "Patient_por.fxml";
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
            Stage stage = (Stage) Admin_selectAcc.getScene().getWindow();
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
		Admin_selectAcc.setItems(listData);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		SelectUser();

		AdminCancelBtn.setOnMouseClicked(event -> {
	        System.exit(0);
	        
				});
	
		
	}

	}
    


	  

	   
	

