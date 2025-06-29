package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Patient_Checkout implements Initializable {
	 @FXML
	    private Label checkout_patientID;

	    @FXML
	    private Label checkout_name;

	    @FXML
	    private Label checkout_doctor;

	    @FXML
	    private DatePicker checkout_date;

	    @FXML
	    private ImageView checkout_imageView;

	    @FXML
	    private DatePicker checkout_checkout;

	    @FXML
	    private Label checkout_totalDays;
	    
	  
	    @FXML
	    private Label checkout_totalPrice;

	    @FXML
	    private Button checkout_payBtn;

	    @FXML
	    private Button checkout_countBtn;

	    private Image image;
	    
	    public statusLabel alert = new statusLabel();

	    public void countBtn(ActionEvent event) {
	        long countDays = 0;
	        if (checkout_date.getValue() != null && checkout_checkout.getValue() != null) {
	            countDays
	                    = ChronoUnit.DAYS.between(checkout_date.getValue(), checkout_checkout.getValue());
	            
	        double price = 20.5;

	        double totalprice = (price * countDays);

	        checkout_totalDays.setText(String.valueOf(countDays));
	        checkout_totalPrice.setText(String.valueOf(totalprice));
	        
	        } else {
	        	alert.ErrorMessage("Something went wrong.");
	           
	        }
	    }

	    public void  payBtn(ActionEvent event) {
	        Date date = new Date();
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	        if (checkout_checkout.getValue() == null
	                || checkout_totalDays.getText().isEmpty()
	                || checkout_totalPrice.getText().isEmpty()) {
	            alert.ErrorMessage("Invalid");
	        } else {
	            if (alert.confirmationMessage("Are you sure you want to pay?")) {
	                String sql = "INSERT INTO payment "
	                        + "(Patient_ID, Doctor, Total_days, Total_price, Date, Date_checkout, date_pay) "
	                        + "VALUES(?,?,?,?,?,?,?)";

	               
	                try  (Connection conn = DatabaseConnection_new.getConnection();
	                	  PreparedStatement stmt = conn.prepareStatement(sql)){
	       			
	                	stmt.setString(1, checkout_patientID.getText());
	                	stmt.setString(2, checkout_doctor.getText());
	                	stmt.setString(3, checkout_totalDays.getText());
	                	stmt.setString(4, checkout_totalPrice.getText());
	                	stmt.setString(5, String.valueOf(checkout_date.getValue()));
	                	stmt.setString(6, String.valueOf(checkout_checkout.getValue()));
	                	stmt.setString(7, String.valueOf(sqlDate));

	                	stmt.executeUpdate();

	                   String updateData = "UPDATE patientmain SET Status_pay = ? WHERE PATIENT_ID = "
	                            + checkout_patientID.getText();

	                    PreparedStatement stmt2 = conn.prepareStatement(updateData);
	                    stmt2.setString(1, "Paid");
	                    stmt2.executeUpdate();

	                    alert.SuccesMessage("Successful!");

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            } else {
	                alert.ErrorMessage("Cancelled.");
	            }

	        }

	    }

	    public void displayFields() {

	        checkout_patientID.setText(String.valueOf(Data.temp_PatientID));
	        checkout_name.setText(Data.temp_name);
	        checkout_doctor.setText(Data.temp_doctorID);
	        checkout_date.setValue(LocalDate.parse(Data.temp_date));
	        checkout_date.setDisable(true);

	        image = new Image("File:" + Data.temp_path, 116, 132, false, true);
	        checkout_imageView.setImage(image);

	    }

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        displayFields();
	    }
}


