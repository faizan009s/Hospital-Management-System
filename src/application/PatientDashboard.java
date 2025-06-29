package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PatientDashboard implements Initializable {

	 @FXML
	    private AnchorPane main_form;

	    @FXML
	    private Circle top_profile;

	    @FXML
	    private Label top_username;

	    @FXML
	    private Label date_time;

	    @FXML
	    private Label current_form;

	    @FXML
	    private Button logout_btn;

	    @FXML
	    private Label nav_patientID;

	    @FXML
	    private Button dashboard_btn;

	    @FXML
	    private Button doctors_btn;

	    @FXML
	    private Button appointments_btn;

	    @FXML
	    private Button profile_btn;

	    @FXML
	    private AnchorPane home_form;

	    @FXML
	    private TableView<PatientsData> home_patient_tableView;

	    @FXML
	    private TableColumn<PatientsData, String> home_patient_col_description;

	    @FXML
	    private TableColumn<PatientsData, String> home_patient_col_diagnosis;

	    @FXML
	    private TableColumn<PatientsData, String> home_patient_col_treatment;

	    @FXML
	    private TableColumn<PatientsData, String> home_patient_col_dateIn;

	    @FXML
	    private TableColumn<PatientsData, String> home_patient_col_dateDischarge;

	    @FXML
	    private Circle home_doctor_circle;

	    @FXML
	    private Label home_doctor_name;

	    @FXML
	    private Label home_doctor_specialization;

	    @FXML
	    private Label home_doctor_email;

	    @FXML
	    private Label home_doctor_mobileNumber;

	    @FXML
	    private TableView<Appointment> home_appointment_tableView;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_appointmenID;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_description;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_diagnosis;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_treatment;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_doctor;

	    @FXML
	    private TableColumn<Appointment, String> home_appointment_col_schedule;

	    @FXML
	    private AnchorPane doctors_form;

	    @FXML
	    private ScrollPane doctors_scrollPane;

	    @FXML
	    private GridPane doctors_gridPane;

	    @FXML
	    private AnchorPane appointments_form;

	    @FXML
	    private Label appointment_ad_name;

	    @FXML
	    private Label appointment_ad_mobileNumber;

	    @FXML
	    private Label appointment_ad_gender;

	    @FXML
	    private Label appointment_ad_address;

	    @FXML
	    private Label appointment_ad_description;

	    @FXML
	    private Label appointment_ad_doctorName;

	    @FXML
	    private Label appointment_ad_specialization;

	    @FXML
	    private Label appointment_ad_schedule;

	    @FXML
	    private Button appointment_d_confirmBtn;

	    @FXML
	    private TextArea appointment_d_description;

	    @FXML
	    private ComboBox<String> appointment_d_doctor;

	    @FXML
	    private DatePicker appointment_d_schedule;

	    @FXML
	    private Button appointment_d_clearBtn;

	    @FXML
	    private AnchorPane profile_form;

	    @FXML
	    private Circle profile_circle;

	    @FXML
	    private Button profile_importBtn;

	    @FXML
	    private Label profile_label_patientID;

	    @FXML
	    private Label profile_label_name;

	    @FXML
	    private Label profile_label_mobileNumber;

	    @FXML
	    private Label profile_label_dateCreated;

	    @FXML
	    private TextField profile_patientID;

	    @FXML
	    private TextField profile_name;

	    @FXML
	    private TextField profile_mobileNumber;

	    @FXML
	    private ComboBox<String> profile_status;

	    @FXML
	    private Button profile_updateBtn;

	    @FXML
	    private TextField profile_password;

	    @FXML
	    private TextArea profile_address;
	    
	    private Image image;
    
    private final statusLabel alert = new statusLabel();
    
    public ObservableList<PatientsData> homePatientGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patientmain WHERE  PATIENT_ID = " + Data.patient_id;
        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	 PreparedStatement stmt = conn.prepareStatement(sql);
        	 ResultSet resultSet = stmt.executeQuery();

            PatientsData pData;
            while (resultSet.next()) {
//                PatientsData(Integer id, Integer patientID, String description
//            , String diagnosis, String treatment, Date date)
                pData = new PatientsData(resultSet.getInt("id"),
                        resultSet.getString("PATIENT_ID"),
                        resultSet.getString("Pt_tdescription"),
                        resultSet.getString("Pt_diagnosis"),
                        resultSet.getString("Pt_treatment"), resultSet.getDate("Pt_date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> homePatientListData;

    public void homePatientDisplayData() {
        homePatientListData = homePatientGetData();

        home_patient_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        home_patient_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        home_patient_col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        home_patient_col_dateIn.setCellValueFactory(new PropertyValueFactory<>("date"));

        home_patient_tableView.setItems(homePatientListData);
    }
    public ObservableList<Appointment> homeAppointmentGetData() {

        ObservableList<Appointment> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointmentdata2 WHERE  Patient_id = "
                + Data.patient_id;

        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();

            Appointment aData;
            while (resultSet.next()) {
//                AppointmentData(Integer appointmentID, String description,
//            String diagnosis, String treatment, String doctorID, Date schedule)
                aData = new Appointment(resultSet.getString("appointmentID"),
                        resultSet.getString("description"),
                        resultSet.getString("diagnosis"), resultSet.getString("treatment"),
                        resultSet.getString("doctor"), resultSet.getDate("schedule"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    public void appointmentAppointmentInfoDisplay() {

        String sql = "SELECT * FROM patientmain WHERE PATIENT_ID = " + Data.patient_id;

        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                appointment_ad_name.setText(resultSet.getString("PATIENT_NAME"));
                appointment_ad_mobileNumber.setText(resultSet.getString("MOb_NUmber"));
                appointment_ad_gender.setText(resultSet.getString("Patient_gender"));
                appointment_ad_address.setText(resultSet.getString("Patient_Address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void appointmentConfirmBtn(ActionEvent event) {

        if (appointment_d_description.getText().isEmpty()
                || appointment_d_schedule.getValue() == null
                || appointment_d_doctor.getSelectionModel().isEmpty()) {
            alert.ErrorMessage("Please fill all blank fields");
        } else {

            appointment_ad_description.setText(appointment_d_description.getText());
            appointment_ad_doctorName.setText(appointment_d_doctor.getSelectionModel().getSelectedItem());

            String sql = "SELECT * FROM doctordata_main WHERE DOCTOR_NAME = ?";
            Connection conn = DatabaseConnection_new.getConnection();
            String tempSpecialized = "";

            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, appointment_d_doctor.getSelectionModel().getSelectedItem());
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    tempSpecialized = resultSet.getString("Dr_specialized");
                }
            } catch (Exception e) {
                e.printStackTrace();
                alert.ErrorMessage("Failed to retrieve doctor specialization.");
            }

            appointment_ad_specialization.setText(tempSpecialized);
            appointment_ad_schedule.setText(String.valueOf(appointment_d_schedule.getValue()));
        }
    }

    public void appointmentDoctor() {
        String sql = "SELECT * FROM doctordata_main";
        Connection conn = DatabaseConnection_new.getConnection();
        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();

            ObservableList<String> listData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                listData.add(resultSet.getString("DOCTOR_NAME"));
            }

            appointment_d_doctor.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void appointmentClearBtn(ActionEvent event)  {
        appointment_d_doctor.getSelectionModel().clearSelection();
        appointment_d_description.clear();
        appointment_d_schedule.setValue(null);

        appointment_ad_description.setText("");
        appointment_ad_doctorName.setText("");
        appointment_ad_specialization.setText("");
        appointment_ad_schedule.setText("");
    }

    public void appointmentBookBtn(ActionEvent event) {
        if (appointment_ad_description.getText().isEmpty()
                || appointment_d_doctor.getSelectionModel().isEmpty()
                || appointment_ad_specialization.getText().isEmpty()
                || appointment_ad_schedule.getText().isEmpty()) {
            alert.ErrorMessage("Please fill all required fields!");
            return;
        }

        Connection conn = DatabaseConnection_new.getConnection();
        String tempAppID = "1"; // Default ID if table is empty

        try {
            // Get max existing ID and add 1
            String selectData = "SELECT MAX(CAST(appointmentID AS UNSIGNED)) AS maxID FROM appointmentdata2";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectData);

            if (resultSet.next()) {
                int maxId = resultSet.getInt("maxID");
                tempAppID = String.valueOf(maxId + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.ErrorMessage("Error while generating appointment ID.");
            return;
        }

        String insertData = "INSERT INTO appointmentdata2 (appointmentID, Patient_id, name, gender, description, mobileNumber, address, date, doctor, specialized, schedule, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (alert.confirmationMessage("Are you sure you want to book?")) {
                PreparedStatement stmt = conn.prepareStatement(insertData);
                stmt.setString(1, tempAppID);
                stmt.setString(2, String.valueOf(Data.patient_id)); // Assuming Data.patient_id is already a string
                stmt.setString(3, appointment_ad_name.getText());
                stmt.setString(4, appointment_ad_gender.getText());
                stmt.setString(5, appointment_ad_description.getText());
                stmt.setString(6, appointment_ad_mobileNumber.getText());
                stmt.setString(7, appointment_ad_address.getText());
                stmt.setString(8, String.valueOf(appointment_d_schedule.getValue())); // assuming it's a DatePicker
                stmt.setString(9, appointment_d_doctor.getSelectionModel().getSelectedItem());
                stmt.setString(10, appointment_ad_specialization.getText());
                stmt.setString(11, appointment_ad_schedule.getText());
                stmt.setString(12, "Active");

                stmt.executeUpdate();

                alert.SuccesMessage("Appointment booked successfully!");
                // appointmentClearBtn(); // Uncomment if you want to clear fields
            } else {
                alert.ErrorMessage("Booking cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.ErrorMessage("Error while booking appointment.");
        }
    }

    public ObservableList<Appointment> homeAppointmentListData;

    public void homeAppointmentDisplayData() {
        homeAppointmentListData = homeAppointmentGetData();

        home_appointment_col_appointmenID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        home_appointment_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        home_appointment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        home_appointment_col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        home_appointment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("Doctor"));
        home_appointment_col_schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));

        home_appointment_tableView.setItems(homeAppointmentListData);
    }
    public void homeDoctorInfoDisplay() {

        String sql = "SELECT * FROM patientmain WHERE PATIENT_ID = " + Data.patient_id;

        Connection conn = DatabaseConnection_new.getConnection();
        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                resultSet.getString("Pt_doctor");
            }

            String checkDoctor = "SELECT * FROM doctordata_main  ";                    

            Statement stmt2 = conn.createStatement();
       	    ResultSet resultSet2 = stmt2.executeQuery(checkDoctor);

            if (resultSet2.next()) {
                home_doctor_name.setText(resultSet2.getString("DOCTOR_NAME"));
                home_doctor_specialization.setText(resultSet2.getString("Dr_specialized"));
                home_doctor_email.setText(resultSet2.getString("DOCTOR_EMAIL"));
                home_doctor_mobileNumber.setText(resultSet2.getString("Dr_MOb_NUmber"));

                String path = resultSet2.getString("Dr_image");

                if (path != null) {
                    path = path.replace("\\", "\\\\");

                    image = new Image("File:" + path, 138, 82, false, true);
                    home_doctor_circle.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }private ObservableList<DoctorsData> doctorList = FXCollections.observableArrayList();

    public ObservableList<DoctorsData> doctorGetData() {

        String sql = "SELECT * FROM doctordata_main WHERE Dr_status = 'Active'";

        Connection conn = DatabaseConnection_new.getConnection();
        ObservableList<DoctorsData> listData = FXCollections.observableArrayList();

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();
            DoctorsData dData;

            while (resultSet.next()) {
//                DoctorData(Integer id, String doctorID, String fullName, String specialized, String email)
                dData = new DoctorsData(resultSet.getInt("id"),
                        resultSet.getString("DOCTOR_ID"),
                        resultSet.getString("DOCTOR_NAME"),
                        resultSet.getString("Dr_specialized"),
                        resultSet.getString("DOCTOR_EMAIL"),
                        resultSet.getString("Dr_image"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void doctorShowCard() {
        doctorList.clear();
        doctorList.addAll(doctorGetData());

        doctors_gridPane.getChildren().clear();
        doctors_gridPane.getColumnConstraints().clear();
        doctors_gridPane.getRowConstraints().clear();

        int row = 0, column = 0;

        for (int q = 0; q < doctorList.size(); q++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Doctor_Card.fxml"));
                StackPane stack = loader.load();

                Doctor_cardCont dController = loader.getController();
                dController.setData(doctorList.get(q));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                doctors_gridPane.add(stack, column++, row);

                GridPane.setMargin(stack, new Insets(15));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

   
    public void profileDisplayFields() {

        String sql = "SELECT * FROM patientmain WHERE PATIENT_ID = " + Data.patient_id;
        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();


            if (resultSet.next()) {
                profile_patientID.setText(resultSet.getString("PATIENT_ID"));
                profile_name.setText(resultSet.getString("PATIENT_NAME"));
                profile_mobileNumber.setText(resultSet.getString("MOb_NUmber"));
                profile_status.getSelectionModel().select(resultSet.getString("Patient_gender"));
                profile_password.setText(resultSet.getString("PATIENT_PASSWORD"));
                profile_address.setText(resultSet.getString("Patient_Address"));

                if (resultSet.getString("pt_image") != null) {
                    String imagePath = "File:" + resultSet.getString("pt_image");
                    image = new Image(imagePath, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
                profileDisplayLabels();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileDisplayLabels() {
        String sql = "SELECT * FROM patientmain WHERE PATIENT_ID = " + Data.patient_id;
        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                profile_label_patientID.setText(resultSet.getString("PATIENT_ID"));
                profile_label_name.setText(resultSet.getString("PATIENT_NAME"));
                profile_label_mobileNumber.setText(resultSet.getString("MOb_NUmber"));
                profile_label_dateCreated.setText(resultSet.getString("Pt_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileDisplayImages() {

        String sql = "SELECT * FROM patientmain WHERE PATIENT_ID = " + Data.patient_id;
        Connection conn = DatabaseConnection_new.getConnection();

        String tempPath1 = "";
        String tempPath2 = "";

        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
       	    ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                tempPath1 = "File:" + resultSet.getString("pt_image");
                tempPath2 = "File:" + resultSet.getString("pt_image");

                if (resultSet.getString("pt_image") != null || "".equals(resultSet.getString("pt_image"))) {
                    image = new Image(tempPath1, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                    image = new Image(tempPath2, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logoutBtn(ActionEvent event) {
    	 try {
             if (alert.confirmationMessage("Are you sure you want to logout?")) {
                 Parent root = FXMLLoader.load(getClass().getResource("Patient_por.fxml"));
                 Stage stage = new Stage();

                 stage.setScene(new Scene(root));
                 stage.show();

                 logout_btn.getScene().getWindow().hide();
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    void profileImportBtn(ActionEvent event) {
    	 FileChooser open = new FileChooser();
         open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

         File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

         if (file != null) {
             Data.path = file.getAbsolutePath();

             image = new Image(file.toURI().toString(), 137, 95, false, true);
             profile_circle.setFill(new ImagePattern(image));
         }
    }

    @FXML
    void profileUpdateBtn(ActionEvent event) {
    	 Connection conn = DatabaseConnection_new.getConnection();

         if (profile_patientID.getText().isEmpty()
                 || profile_name.getText().isEmpty()
                 || profile_mobileNumber.getText().isEmpty()
                 || profile_status.getSelectionModel().isEmpty()
                 || profile_password.getText().isEmpty()
                 || profile_address.getText().isEmpty()) {
             alert.ErrorMessage("Please fill all blank fields");
         } else {
             if (alert.confirmationMessage("Are you sure you want to Update your Profile?")) {
                 if (Data.path == null || "".equals(Data.path)) {
                     String updateData = "UPDATE patientmain SET "
                             + "PATIENT_NAME = '" + profile_name.getText() + "', MOb_NUmber = '"
                             + profile_mobileNumber.getText() + "', Patient_gender = '"
                             + profile_status.getSelectionModel().getSelectedItem() + "', PATIENT_PASSWORD = '"
                             + profile_password.getText() + "', Patient_Address = '"
                             + profile_address.getText() + "' WHERE PATIENT_ID = " + Data.patient_id;

                     try {
                    	 PreparedStatement stmt = conn.prepareStatement(updateData);
                    	     stmt.executeUpdate();

                         alert.SuccesMessage("Updated Successfully");
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 } else {
                     String path = Data.path;
                     path = path.replace("\\", "\\\\");

                     Path transfer = Paths.get(path);

                     Path copy = Paths.get("C:\\Users\\ASUS\\eclipse-workspace\\HMS_New\\src\\Directory\\"
                             + Data.patient_id + ".jpg");

                     String copyPath = copy.toAbsolutePath().toString();
                     copyPath = copyPath.replace("\\", "\\\\");

                     String updateData = "UPDATE patientmain SET "
                             + "PATIENT_NAME = '" + profile_name.getText() + "', MOb_NUmber = '"
                             + profile_mobileNumber.getText() + "', Patient_gender = '"
                             + profile_status.getSelectionModel().getSelectedItem() + "', PATIENT_PASSWORD = '"
                             + profile_password.getText() + "', Patient_Address = '"
                             + profile_address.getText() + "', pt_image = '"
                             + copyPath + "' WHERE PATIENT_ID = " + Data.patient_id;

                     try {
                    	 PreparedStatement stmt = conn.prepareStatement(updateData);
                 	       stmt.executeUpdate();

                         Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                         alert.SuccesMessage("Updated successfully!");

                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             } else {
                 alert.ErrorMessage("Cancelled.");
             }

         }
         profileDisplayImages();
     }
    
    public void runTime() {

        new Thread() {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }

    @FXML
    void switchForm(ActionEvent event) {
    	  if (event.getSource() == dashboard_btn) {
    		  home_form.setVisible(true);
    		  doctors_form.setVisible(false);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == doctors_btn) {
	        	home_form.setVisible(false);
	        	doctors_form.setVisible(true);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == appointments_btn) {
	        	home_form.setVisible(false);
	        	doctors_form.setVisible(false);
	            appointments_form.setVisible(true);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == profile_btn) {
	        	home_form.setVisible(false);
	        	doctors_form.setVisible(false);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(true);
	        }
    }
    public void displayAdminIDNumberName1() {

    	 String query = "SELECT * FROM patientmain WHERE PATIENT_ID = '"
                 + Data.patient_id + "'";

        try (Connection conn = DatabaseConnection_new.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {

            	nav_patientID.setText(resultSet.getString("PATIENT_ID"));
                 String tempUsername = resultSet.getString("Patient_Username");
                 tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                 top_username.setText(tempUsername);
                 
            }
        } catch (Exception e) {
        	 e.printStackTrace();
        }

    }
    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listG);
        profile_status.setItems(listData);

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		runTime();
		displayAdminIDNumberName1();
		
		homePatientDisplayData();
		homeAppointmentDisplayData();
		//homeDoctorInfoDisplay();
		homeDoctorInfoDisplay();
		
		appointmentAppointmentInfoDisplay();
		appointmentDoctor();
		
		profileDisplayFields();
		profileDisplayLabels();
		profileDisplayImages();
		profileGenderList();
		
		doctorShowCard();
	}

}
