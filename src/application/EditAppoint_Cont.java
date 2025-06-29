package application;

	
	import java.net.URL;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	import java.util.ResourceBundle;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.Initializable;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;

	
	public class EditAppoint_Cont implements Initializable{

	    @FXML
	    private TextField editApp_appointmentID;

	    @FXML
	    private TextField editApp_fullName;

	    @FXML
	    private ComboBox<String> editApp_gender;

	    @FXML
	    private TextField editApp_mobileNumber;

	    @FXML
	    private TextArea editApp_address;

	    @FXML
	    private Button editApp_updateBtn;

	    @FXML
	    private Button editApp_cancelBtn;

	    @FXML
	    private TextArea editApp_description;

	    @FXML
	    private TextField editApp_diagnosis;

	    @FXML
	    private TextField editApp_treatment;

	    @FXML
	    private ComboBox<String> editApp_doctor;

	    @FXML
	    private ComboBox<String> editApp_specialized;

	    @FXML
	    private ComboBox<String> editApp_status;
	    
	   
	    private final statusLabel alert = new statusLabel();
	    @FXML
	    void Appontment_update(ActionEvent event) {

	        if (editApp_appointmentID.getText().isEmpty() || editApp_fullName.getText().isEmpty()
	                || editApp_gender.getSelectionModel().getSelectedItem() == null
	                || editApp_mobileNumber.getText().isEmpty()
	                || editApp_address.getText().isEmpty()
	                || editApp_diagnosis.getText().isEmpty()
	                || editApp_treatment.getText().isEmpty()
	                || editApp_description.getText().isEmpty()
	                || editApp_status.getSelectionModel().getSelectedItem() == null) {
	            alert.ErrorMessage("Please fill all blank fields");
	           
	        } else {
	            String updateData = "UPDATE appointmentdata2 SET name = ?, gender = ?"
	                    + ", mobileNumber = ?, address = ?, status = ?, description = ?, diagnosis = ?, treatment = ?, dateModify = ? "
	                    + "WHERE appointmentID = '"
	                    + editApp_appointmentID.getText() + "'";
	            Connection conn = DatabaseConnection_new.getConnection();
	            try {
	                if (alert.confirmationMessage("Are you sure you want to UPDATE Apppointment ID: " + editApp_appointmentID.getText()
	                        + "?")) {
	                	 PreparedStatement stmt = conn.prepareStatement(updateData);
	                	 //ResultSet resultSet = stmt.executeQuery();

	                    Date date = new Date();
	                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                    stmt.setString(1, editApp_fullName.getText());
	                    stmt.setString(2, editApp_gender.getSelectionModel().getSelectedItem());
	                    stmt.setString(3, editApp_mobileNumber.getText());
	                    stmt.setString(4, editApp_address.getText());
	                    stmt.setString(5, editApp_diagnosis.getText()); 
	                    stmt.setString(6, editApp_treatment.getText());
	                    stmt.setString(7, editApp_description.getText());
	                    stmt.setString(8, editApp_status.getSelectionModel().getSelectedItem());
	                    stmt.setString(9, String.valueOf(sqlDate));
	                    stmt.executeUpdate();
	                    alert.SuccesMessage("Updated Successfully!");
	                } else {
	                    alert.ErrorMessage("Cancelled.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }
	    }
	    /*public void setField() {
	    	editApp_appointmentID.setText(String.valueOf(Data.temp_appID));
	    	editApp_fullName.setText(Data.temp_appName);
	    	editApp_gender.getSelectionModel().select(Data.temp_appGender);
	    	editApp_mobileNumber.setText(String.valueOf(Data.temp_appMobileNumber));
	    	editApp_address.setText(Data.temp_appAddress);
	    	editApp_diagnosis.setText(Data.temp_appDiagnosis);
	    	editApp_treatment.setText(Data.temp_appTreatment);
	    	editApp_description.setText(Data.temp_appDescription);
	    	editApp_status.getSelectionModel().select(Data.temp_appStatus);
	    }*/

	    
	    public void displayFields(){
	        editApp_appointmentID.setText(Data.temp_appID);
	        editApp_fullName.setText(Data.temp_appName);
	        editApp_gender.getSelectionModel().select(Data.temp_appGender);
	        editApp_mobileNumber.setText(Data.temp_appMobileNumber);
	        editApp_address.setText(Data.temp_appAddress);
	        editApp_description.setText(Data.temp_appDescription);
	        editApp_diagnosis.setText(Data.temp_appDiagnosis);
	        editApp_treatment.setText(Data.temp_appTreatment);
	        editApp_doctor.getSelectionModel().select(Data.temp_appDoctor);
	        editApp_specialized.getSelectionModel().select(Data.temp_appSpecialized);
	        editApp_status.getSelectionModel().select(Data.temp_appStatus);
	    }
	    
	    public void doctorList(){
	        String sql = "SELECT * FROM doctordata_main WHERE Dr_dateDelete IS NULL";
	        
	        Connection conn = DatabaseConnection_new.getConnection();
	        try{
	        	 PreparedStatement stmt = conn.prepareStatement(sql);
				 ResultSet resultSet = stmt.executeQuery();
	            ObservableList<String> listData = FXCollections.observableArrayList();
	            while(resultSet.next()){
	                listData.add(resultSet.getString("DOCTOR_ID"));
	            }
	            
	            editApp_doctor.setItems(listData);
	            specializedList();
	        }catch(Exception e){e.printStackTrace();}
	    }
	    
	    public void specializedList(){
	        String sql = "SELECT * FROM doctordata_main WHERE Dr_dateDelete IS NULL AND DOCTOR_ID = '"
	                + editApp_doctor.getSelectionModel().getSelectedItem() + "'";
	        
	        Connection conn = DatabaseConnection_new.getConnection();
	        
	        try{
	        	 PreparedStatement stmt = conn.prepareStatement(sql);
				 ResultSet resultSet = stmt.executeQuery();
	            ObservableList<String> listData = FXCollections.observableArrayList();
	            if(resultSet.next()){
	                listData.add(resultSet.getString("Dr_specialized"));
	            }
	            editApp_specialized.setItems(listData);
	            
	        }catch(Exception e){e.printStackTrace();}
	    }
	    
	    public void genderList() {
	        List<String> genderL = new ArrayList<>();

	        for (String data : Data.gender) {
	            genderL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(genderL);
	        editApp_gender.setItems(listData);
	    }

	    public void statusList() {
	        List<String> statusL = new ArrayList<>();

	        for (String data : Data.status) {
	            statusL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(statusL);
	        editApp_status.setItems(listData);
	    }
	    
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        doctorList();
	        genderList();
	        statusList();
	        
	        displayFields();
	        specializedList();
	    }
	    
	}


