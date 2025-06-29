package application;

	import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.layout.AnchorPane;

	public class EditPatientsCont implements Initializable {

	    @FXML
	    private TextArea edit_address;

	    @FXML
	    private TextField edit_contactNumber;

	    @FXML
	    private ComboBox<String> edit_gender;

	    @FXML
	    private TextField edit_name;

	    @FXML
	    private TextField edit_patientID;

	    @FXML
	    private ComboBox<String> edit_status;

	    @FXML
	    private Button edit_updateBtn;

	    @FXML
	    private AnchorPane main_form;
	    
	    @FXML
	    public  statusLabel alert = new statusLabel();
	    
	    @FXML
	    void updateBtn(ActionEvent event) {

	        if (edit_patientID.getText().isEmpty() || edit_name.getText().isEmpty()
	                || edit_gender.getSelectionModel().getSelectedItem() == null
	                || edit_contactNumber.getText().isEmpty()
	                || edit_address.getText().isEmpty()
	                || edit_status.getSelectionModel().getSelectedItem() == null) {
	            alert.ErrorMessage("Please fill all blank fields");
	           
	        } else {
	            String updateData = "UPDATE patientmain SET PATIENT_NAME = ?, Patient_gender = ?"
	                    + ", MOb_NUmber = ?, Patient_Address = ?, Pt_status = ?, Pt_dateModify = ? "
	                    + "WHERE PATIENT_ID = '"
	                    + edit_patientID.getText() + "'";
	            Connection conn = DatabaseConnection_new.getConnection();
	            try {
	                if (alert.confirmationMessage("Are you sure you want to UPDATE Patient ID: " + edit_patientID.getText()
	                        + "?")) {
	                	 PreparedStatement stmt = conn.prepareStatement(updateData);
	                	 //ResultSet resultSet = stmt.executeQuery();

	                    Date date = new Date();
	                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                    stmt.setString(1, edit_name.getText());
	                    stmt.setString(2, edit_gender.getSelectionModel().getSelectedItem());
	                    stmt.setString(3, edit_contactNumber.getText());
	                    stmt.setString(4, edit_address.getText());
	                    stmt.setString(5, edit_status.getSelectionModel().getSelectedItem());
	                    stmt.setString(6, String.valueOf(sqlDate));
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
	    public void setField() {
	        edit_patientID.setText(String.valueOf(Data.temp_PatientID));
	        edit_name.setText(Data.temp_name);
	        edit_gender.getSelectionModel().select(Data.temp_gender);
	        edit_contactNumber.setText(String.valueOf(Data.temp_number));
	        edit_address.setText(Data.temp_address);
	        edit_status.getSelectionModel().select(Data.temp_status);
	    }

	    public void genderList() {
	        List<String> genderL = new ArrayList<>();

	        for (String data : Data.gender) {
	            genderL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(genderL);
	        edit_gender.setItems(listData);
	    }

	    public void statusList() {
	        List<String> statusL = new ArrayList<>();

	        for (String data : Data.status) {
	            statusL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(statusL);
	        edit_status.setItems(listData);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			genderList();
			 statusList();
			 setField();
		}

	}


