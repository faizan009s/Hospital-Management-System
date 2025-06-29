 
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
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


	public class DoctorDashboardmain implements Initializable{

		 @FXML
		    private AnchorPane main_form;

		    @FXML
		    private Circle top_profile;

		    @FXML
		    private Label top_username;

		    @FXML
		    private Button logout_btn;

		    @FXML
		    private Label date_time;

		    @FXML
		    private Label current_form;

		    @FXML
		    private Label nav_adminID;

		    @FXML
		    private Label nav_username;

		    @FXML
		    private Button dashboard_btn;

		    @FXML
		    private Button patients_btn;

		    @FXML
		    private Button appointments_btn;

		    @FXML
		    private Button profile_btn;

		    @FXML
		    private AnchorPane dashboard_form;

		    @FXML
		    private Label dashboard_IP;

		    @FXML
		    private Label dashboard_TP;

		    @FXML
		    private Label dashboard_AP;

		    @FXML
		    private Label dashboard_tA;

		    @FXML
		    private AreaChart<String,Number> dashboad_chart_PD;

		    @FXML
		    private BarChart<String, Number> dashboad_chart_DD;

		    @FXML
		    private TableView<Appointment> dashboad_tableView;

		    @FXML
		    private TableColumn<Appointment, String> dashboad_col_appointmentID;

		    @FXML
		    private TableColumn<Appointment, String> dashboad_col_name;

		    @FXML
		    private TableColumn<Appointment, String> dashboad_col_description;

		    @FXML
		    private TableColumn<Appointment, String> dashboad_col_appointmentDate;

		    @FXML
		    private TableColumn<Appointment, String> dashboad_col_status;

		    @FXML
		    private AnchorPane patients_form;

		    @FXML
		    private TextField patients_patientID;

		    @FXML
		    private TextField patients_patientName;

		    @FXML
		    private TextField patients_mobileNumber;

		    @FXML
		    private TextField patients_password;

		    @FXML
		    private TextArea patients_address;

		    @FXML
		    private Button patients_confirmBtn;

		    @FXML
		    private Label patients_PA_patientID;

		    @FXML
		    private Label patients_PA_password;

		    @FXML
		    private Label patients_PA_dateCreated;

		    @FXML
		    private Label patients_PI_patientName;

		    @FXML
		    private Label patients_PI_gender;

		    @FXML
		    private Label patients_PI_mobileNumber;

		    @FXML
		    private Label patients_PI_address;

		    @FXML
		    private Button patients_PI_addBtn;

		    @FXML
		    private Button patients_PI_recordBtn;

		    @FXML
		    private AnchorPane appointments_form;

		    @FXML
		    private TableView<Appointment> appointments_tableView;

		    @FXML
		    private TableColumn<Appointment, Integer> appointments_col_appointmentID;

		    @FXML
		    private TableColumn<Appointment, String> appointments_col_name;

		    @FXML
		    private TableColumn<Appointment, String> appointments_col_gender;

		    @FXML
		    private TableColumn<Appointment, Long> appointments_col_contactNumber;

		    @FXML
		    private TableColumn<Appointment, String> appointments_col_description;

		    @FXML
		    private TableColumn<Appointment, Date> appointments_col_date;

		    @FXML
		    private TableColumn<Appointment, Date> appointments_col_dateModify;

		    @FXML
		    private TableColumn<Appointment, Date> appointments_col_dateDelete;

		    @FXML
		    private TableColumn<Appointment, String> appointments_col_status;

		    @FXML
		    private TableColumn<Appointment, String> appointments_col_action;

		    @FXML
		    private TextField appointment_appointmentID;

		    @FXML
		    private TextField appointment_name;

		    @FXML
		    private ComboBox<String> appointment_gender;

		    @FXML
		    private TextField appointment_description;

		    @FXML
		    private TextField appointment_diagnosis;

		    @FXML
		    private TextField appointment_treatment;

		    @FXML
		    private TextField appointment_mobileNumber;

		    @FXML
		    private TextArea appointment_address;

		    @FXML
		    private ComboBox<String> appointment_status;

		    @FXML
		    private DatePicker appointment_schedule;

		    @FXML
		    private Button appointment_insertBtn;

		    @FXML
		    private Button appointment_updateBtn;

		    @FXML
		    private Button appointment_clearBtn;

		    @FXML
		    private Button appointment_deleteBtn;

		    @FXML
		    private ComboBox<String> patients_gender;

		    @FXML
		    private AnchorPane profile_form;

		    @FXML
		    private Circle profile_circleImage;

		    @FXML
		    private Button profile_importBtn;

		    @FXML
		    private Label profile_label_doctorID;

		    @FXML
		    private Label profile_label_name;

		    @FXML
		    private Label profile_label_email;

		    @FXML
		    private Label profile_label_dateCreated;

		    @FXML
		    private TextField profile_doctorID;

		    @FXML
		    private TextField profile_name;

		    @FXML
		    private TextField profile_email;

		    @FXML
		    private ComboBox<String> profile_gender;

		    @FXML
		    private TextField profile_mobileNumber;

		    @FXML
		    private TextArea profile_address;
		    @FXML
		    private Image image;

		    @FXML
		    private ComboBox<String> profile_specialized;

		    @FXML
		    private ComboBox<String> profile_status;

		    @FXML
		    private Button profile_updateBtn;
		    @FXML
		    private final statusLabel alert = new statusLabel();
		    
		    public void dashbboardDisplayIP() {
		        String sql = "SELECT COUNT(id) AS total FROM patientmain ";
		                //+ Data.doctor_id + "'";
		        //SELECT COUNT(id) AS total FROM your_table_name

		        Connection conn = DatabaseConnection_new.getConnection();
		        int getIP = 0;
		        try {
		        	PreparedStatement stmt = conn.prepareStatement(sql);
	            	 ResultSet resultSet = stmt.executeQuery();

		            if (resultSet.next()) {
		                getIP = resultSet.getInt("total");
		            }
		            dashboard_IP.setText(String.valueOf(getIP));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    public void dashbboardDisplayTP() {
		        String sql = "SELECT COUNT(id) AS total FROM patientmain";
		               // + Data.doctor_id + "'";
		        Connection conn = DatabaseConnection_new.getConnection();
		        int getTP = 0;
		        try {
		        	PreparedStatement stmt = conn.prepareStatement(sql);
	            	 ResultSet resultSet = stmt.executeQuery();

		            if (resultSet.next()) {
		                getTP = resultSet.getInt("total");
		            }
		            dashboard_TP.setText(String.valueOf(getTP));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    public void dashbboardDisplayAP() {
		        String sql = "SELECT COUNT(id) AS total FROM patientmain WHERE Pt_status = 'Active'";
		               // + Data.doctor_id + "'";
		        Connection conn = DatabaseConnection_new.getConnection();
		        int getAP = 0;
		        try {
		        	PreparedStatement stmt = conn.prepareStatement(sql);
	            	 ResultSet resultSet = stmt.executeQuery();
		            if (resultSet.next()) {
		                getAP = resultSet.getInt("total");
		            }
		            dashboard_TP.setText(String.valueOf(getAP));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    public void dashbboardDisplayTA() {
		        String sql = "SELECT COUNT(id) AS total FROM appointmentdata2 WHERE status = 'Active' ";
		              //  + Data.doctor_id + "'";
		        Connection conn = DatabaseConnection_new.getConnection();
		        int getTA = 0;
		        try {
		        	PreparedStatement stmt = conn.prepareStatement(sql);
	            	 ResultSet resultSet = stmt.executeQuery();
		            if (resultSet.next()) {
		                getTA = resultSet.getInt("total");
		            }
		            dashboard_tA.setText(String.valueOf(getTA));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    public ObservableList<Appointment> dashboardAppointmentTableView() {

		        ObservableList<Appointment> listData = FXCollections.observableArrayList();

		        String sql = "SELECT * FROM appointmentdata2"; //WHERE doctor = '"
		               // + Data.doctor_id + "'";

		        Connection conn = DatabaseConnection_new.getConnection();

		        try {

		        	PreparedStatement stmt = conn.prepareStatement(sql);
	            	ResultSet resultSet = stmt.executeQuery();
		            Appointment aData;
		            while (resultSet.next()) {
		                aData = new Appointment(resultSet.getString("appointmentID"),
		                		resultSet.getString("name"), resultSet.getString("description"),
		                		resultSet.getDate("date"), resultSet.getString("status"));
		                listData.add(aData);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return listData;
		    }

		    private ObservableList<Appointment> dashboardGetData;

		    public void dashboardDisplayData() {
		        dashboardGetData = dashboardAppointmentTableView();

		        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		        dashboad_tableView.setItems(dashboardGetData);
		    }
		 
		    public void dashboardNOP() throws SQLException {
		        PreparedStatement stmt = null;
		        ResultSet rs = null;
		        
		        try {
		            // Assuming DatabaseConnection_new.getConnection() is handled in a separate class
		            Connection conn = DatabaseConnection_new.getConnection();

		            String query = "SELECT P.PATIENT_NAME, COUNT(a.Patient_id) AS Patient_count " +
		                           "FROM patientmain P " +
		                           "LEFT JOIN appointmentdata2 a ON P.PATIENT_ID = a.Patient_id " +
		                           "GROUP BY P.PATIENT_NAME";

		            stmt = conn.prepareStatement(query);
		            rs = stmt.executeQuery();

		            XYChart.Series<String, Number> series = new XYChart.Series<>();
		            series.setName("Appointment Data");

		            // Add data to series
		            while (rs.next()) {
		                String patientName = rs.getString("PATIENT_NAME");
		                int appointmentCount = rs.getInt("Patient_count");

		                series.getData().add(new XYChart.Data<>(patientName, appointmentCount));
		            }

		            // Clear old data and add new series to the chart
		            dashboad_chart_PD.getData().clear();  // Clear previous data
		            dashboad_chart_PD.getData().add(series);  // Add the updated series

		        } catch (SQLException e) {
		            // Handle SQL exceptions (e.g., database connection issues)
		            e.printStackTrace();
		        }
		    }

		    public void dashboardNOA() {
		        dashboad_chart_DD.getData().clear();  // Clear old data

		        // SQL to get the number of appointments grouped by appointment date
		        String sql = "SELECT DATE(date) AS appt_day, COUNT(id) AS total " +
		                     "FROM appointmentdata2 " +
		                     "WHERE doctor = ? " +  // Filter by doctor ID
		                     "GROUP BY appt_day " +
		                     "ORDER BY appt_day ASC LIMIT 7";  // Limit to the last 7 days

		        try (Connection conn = DatabaseConnection_new.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setString(1, Data.doctor_id);  // Use the doctor ID to filter data

		            ResultSet resultSet = stmt.executeQuery();
		            XYChart.Series<String, Number> chart = new XYChart.Series<>();
		            chart.setName("Number of Appointments");

		            // Iterate through resultSet and add data to the chart
		            while (resultSet.next()) {
		                String date = resultSet.getString("appt_day");  // Appointment date
		                int count = resultSet.getInt("total");  // Number of appointments on that date
		                chart.getData().add(new XYChart.Data<>(date, count));
		            }

		            dashboad_chart_DD.getData().add(chart);  // Add the series to the chart

		        } catch (Exception e) {
		            e.printStackTrace();  // Handle exceptions
		        }
		    }
		    public void patientClearFields() {
		        patients_patientID.clear();
		        patients_patientName.clear();
		        patients_gender.getSelectionModel().clearSelection();
		        patients_mobileNumber.clear();
		        patients_password.clear();
		        patients_address.clear();
		        patients_PA_patientID.setText("");
		        patients_PA_password.setText("");
		        patients_PA_dateCreated.setText("");
		        patients_PI_patientName.setText("");
		        patients_PI_gender.setText("");
		        patients_PI_mobileNumber.setText("");
		        patients_PI_address.setText("");
		    }
		    @FXML
		    private void patientGenderList() {

		        List<String> listG = new ArrayList<>();

		        for (String data : Data.gender) {
		            listG.add(data);
		        }
		        ObservableList<String> listData = FXCollections.observableList(listG);

		        patients_gender.setItems(listData);

		    }

		    public void appointmentInsertBtn(ActionEvent event) {

//		        CHECK IF THE FIELD(S) ARE EMPTY
		        if (appointment_appointmentID.getText().isEmpty()
		                || appointment_name.getText().isEmpty()
		                || appointment_gender.getSelectionModel().getSelectedItem() == null
		                || appointment_mobileNumber.getText().isEmpty()
		                || appointment_description.getText().isEmpty()
		                || appointment_address.getText().isEmpty()
		                || appointment_status.getSelectionModel().getSelectedItem() == null
		                || appointment_schedule.getValue() == null) {
		            alert.ErrorMessage("Please fill the blank fields");
		        } else {
		            String checkAppointmentID = "SELECT * FROM appointmentdata2 WHERE appointmentID = "
		                    + appointment_appointmentID.getText();
		            Connection conn = DatabaseConnection_new.getConnection();
		            try {
		            	 PreparedStatement stmt = conn.prepareStatement(checkAppointmentID);
		            	 ResultSet resultSet = stmt.executeQuery();

		                if (resultSet.next()) {
		                    alert.ErrorMessage(appointment_appointmentID.getText() + " was already taken");
		                } else {
		                    String getSpecialized = "";
		                    String getDoctorData = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
		                            + Data.doctor_id + "'";

		                    PreparedStatement stmt2 = conn.prepareStatement(getDoctorData);
		                    ResultSet resultSet2 = stmt2.executeQuery();

		                    if (resultSet2.next()) {
		                        getSpecialized = resultSet2.getString("Dr_specialized");
		                    }

		                    String insertData = "INSERT INTO appointmentdata2 (appointmentID, name, gender"
		                            + ", description, diagnosis, treatment, mobileNumber"
		                            + ", address, date, status, doctor, specialized, schedule) "
		                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		                    PreparedStatement stmt3 = conn.prepareStatement(insertData);

		                    stmt3.setString(1, appointment_appointmentID.getText());
		                    stmt3.setString(2, appointment_name.getText());
		                    stmt3.setString(3, (String) appointment_gender.getSelectionModel().getSelectedItem());
		                    stmt3.setString(4, appointment_description.getText());
		                    stmt3.setString(5, appointment_diagnosis.getText());
		                    stmt3.setString(6, appointment_treatment.getText());
		                    stmt3.setString(7, appointment_mobileNumber.getText());
		                    stmt3.setString(8, appointment_address.getText());
		                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
		                    stmt3.setString(9, "" + sqlDate);
		                    stmt3.setString(10, (String) appointment_status.getSelectionModel().getSelectedItem());
		                    stmt3.setString(11, Data.doctor_id);
		                    stmt3.setString(12, getSpecialized);
		                    stmt3.setString(13, "" + appointment_schedule.getValue());
		                    stmt3.executeUpdate();
		                    appointmentShowData();
		                    appointmentAppointmentID();
		                    //appointmentClearBtn();
		                    alert.SuccesMessage("Successully added!");

		                }

		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }

		    }
		    public void appointmentClearBtn()  {
		        appointment_appointmentID.clear();
		        appointment_name.clear();
		        appointment_gender.getSelectionModel().clearSelection();
		        appointment_mobileNumber.clear();
		        appointment_description.clear();
		        appointment_treatment.clear();
		        appointment_diagnosis.clear();
		        appointment_address.clear();
		        appointment_status.getSelectionModel().clearSelection();
		        appointment_schedule.setValue(null);
		    }
		    
		    private Integer AppointmentID;

		    public void appointmentGetAppointmentID() {
		        String sql = "SELECT MAX(appointmentID) FROM appointmentdata2";
		        Connection conn = DatabaseConnection_new.getConnection();
		        int tempAppID = 0;
		        try {
		        	 PreparedStatement stmt = conn.prepareStatement(sql);
		        	 ResultSet resultSet = stmt.executeQuery();
		            if (resultSet.next()) {
		                tempAppID = resultSet.getInt("MAX(appointmentID)");
		            }
		            if (tempAppID == 0) {
		                tempAppID += 1;
		            } else {
		                tempAppID += 1;
		            }
		            AppointmentID = tempAppID;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    public void profileFields() {
		        String selectData = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
		                + Data.doctor_id + "'";

		        Connection conn = DatabaseConnection_new.getConnection();
		        try {
		        	 PreparedStatement stmt = conn.prepareStatement(selectData);
	            	 ResultSet resultSet = stmt.executeQuery();


		            if (resultSet.next()) {
		                profile_doctorID.setText(resultSet.getString("DOCTOR_ID"));
		                profile_name.setText(resultSet.getString("DOCTOR_NAME"));
		                profile_email.setText(resultSet.getString("DOCTOR_EMAIL"));
		                profile_gender.getSelectionModel().select(resultSet.getString("Dr_gender"));
		                profile_mobileNumber.setText(resultSet.getString("Dr_MOb_NUmber"));
		                profile_address.setText(resultSet.getString("Dr_Address"));
		                profile_specialized.getSelectionModel().select(resultSet.getString("Dr_specialized"));
		                profile_status.getSelectionModel().select(resultSet.getString("Dr_status"));
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		    }
		    public void profileLabels() {
		        String selectData = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
		                + Data.doctor_id + "'";

		        Connection conn = DatabaseConnection_new.getConnection();
		        try {
		        	PreparedStatement stmt = conn.prepareStatement(selectData);
	            	ResultSet resultSet = stmt.executeQuery();
		            if (resultSet.next()) {
		                profile_label_doctorID.setText(resultSet.getString("DOCTOR_ID"));
		                profile_label_name.setText(resultSet.getString("DOCTOR_NAME"));
		                profile_label_email.setText(resultSet.getString("DOCTOR_EMAIL"));
		                profile_label_dateCreated.setText(resultSet.getString("Dr_date"));
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    public void profileStatusList() {

		        List<String> listS = new ArrayList<>();

		        for (String data : Data.status) {
		            listS.add(data);
		        }

		        ObservableList<String> listData = FXCollections.observableArrayList(listS);
		        profile_status.setItems(listData);
		    }

		    public void profileGenderList() {

		        List<String> listG = new ArrayList<>();

		        for (String data : Data.gender) {
		            listG.add(data);
		        }

		        ObservableList<String> listData = FXCollections.observableArrayList(listG);
		        profile_gender.setItems(listData);

		    }
		    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

		    public void profileSpecializedList() {

		        List<String> listS = new ArrayList<>();

		        for (String data : specialization) {
		            listS.add(data);
		        }

		        ObservableList<String> listData = FXCollections.observableArrayList(listS);
		        profile_specialized.setItems(listData);
		    }

		    public void appointmentAppointmentID() {
		        appointmentGetAppointmentID();

		        appointment_appointmentID.setText("" + AppointmentID);
		        appointment_appointmentID.setDisable(true);

		    }
		    
		    public void appointmentGenderList() {
		        List<String> listG = new ArrayList<>();

		        for (String data : Data.gender) {
		            listG.add(data);
		        }

		        ObservableList<String> listData = FXCollections.observableArrayList(listG);
		        appointment_gender.setItems(listData);

		    }
		    
		    public void appointmentStatusList() {
		        List<String> listS = new ArrayList<>();

		        for (String data : Data.status) {
		            listS.add(data);
		        }

		        ObservableList<String> listData = FXCollections.observableArrayList(listS);
		        appointment_status.setItems(listData);

		    }
		    
		    public ObservableList<Appointment> appointmentGetData() {

		        ObservableList<Appointment> listData = FXCollections.observableArrayList();

		        String sql = "SELECT * FROM appointmentdata2  ";
		                

		        Connection conn = DatabaseConnection_new.getConnection();
		        try {

		        	 PreparedStatement stmt = conn.prepareStatement(sql);
		        	 ResultSet resultSet = stmt.executeQuery();

		            Appointment appData;

		            while (resultSet.next()) {
//		            Integer appointmentID, String name, String gender,
//		            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//		            Date date, Date dateModify, Date dateDelete, String status, Date schedule

		                appData = new Appointment(resultSet.getString("appointmentID"),
		                		resultSet.getString("name"), resultSet.getString("gender"),
		                		resultSet.getLong("mobileNumber"), resultSet.getString("description"),
		                		resultSet.getString("diagnosis"), resultSet.getString("treatment"),
		                		resultSet.getString("address"), resultSet.getDate("date"),
		                		resultSet.getDate("dateModify"), resultSet.getDate("dateDelete"),
		                        resultSet.getString("status"), resultSet.getDate("schedule"));
		                // STORE ALL DATA
		                listData.add(appData);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }System.out.println("Appointments fetched: " + listData.size());
		        for (Appointment app : listData) {
		            System.out.println(" - " + app.getAppointmentID() + " | " + app.getName());
		        }

		        return listData;
		    }

		    public ObservableList<Appointment> appoinmentListData;

		    public void appointmentShowData() {
		        appoinmentListData = appointmentGetData();

		        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
		        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
		        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
		        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		        appointments_tableView.setItems(appoinmentListData);
		    }
	    //@FXML
	    //void appointmentClearBtn(ActionEvent event) {

	    

	    @FXML
	    void appointmentDeleteBtn() {
	    	 if (appointment_appointmentID.getText().isEmpty()) {
	             alert.ErrorMessage("Please select the item first");
	         } else {

	             String updateData = "DELETE FROM appointmentdata2 WHERE appointmentID = '"
	                     + appointment_appointmentID.getText() + "'";

	             Connection conn = DatabaseConnection_new.getConnection();

	             try {
	                 //java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

	                 if (alert.confirmationMessage("Are you sure you want to DELETE Appointment ID: "
	                         + appointment_appointmentID.getText() + "?")) {
	                	 PreparedStatement stmt = conn.prepareStatement(updateData);

	                	 //stmt.setString(1, String.valueOf(sqlDate));
	                	 stmt.executeUpdate();

	                     appointmentShowData();
	                     appointmentAppointmentID();
	                     //appointmentClearBtn();

	                     alert.SuccesMessage("Successully Updated!");
	                 } else {
	                     alert.ErrorMessage("Cancelled.");
	                 }

	             } catch (Exception e) {
	                 e.printStackTrace();
	             }

	         }

	    }

	  
	    @FXML
	    void appointmentSelect() {
	    	 Appointment appData = appointments_tableView.getSelectionModel().getSelectedItem();
	         int num = appointments_tableView.getSelectionModel().getSelectedIndex();

	         if ((num - 1) < -1) {
	             return;
	         }

	         appointment_appointmentID.setText("" + appData.getAppointmentID());
	         appointment_name.setText(appData.getName());
	         appointment_gender.getSelectionModel().select(appData.getGender());
	         appointment_mobileNumber.setText("" + appData.getMobileNumber());
	         appointment_description.setText(appData.getDescription());
	         appointment_diagnosis.setText(appData.getDiagnosis());
	         appointment_treatment.setText(appData.getTreatment());
	         appointment_address.setText(appData.getAddress());
	         appointment_status.getSelectionModel().select(appData.getStatus());

	     }
	    

	    @FXML
	    void appointmentUpdateBtn() {
	    	if (appointment_appointmentID.getText().isEmpty()
	                || appointment_name.getText().isEmpty()
	                || appointment_gender.getSelectionModel().getSelectedItem() == null
	                || appointment_mobileNumber.getText().isEmpty()
	                || appointment_description.getText().isEmpty()
	                || appointment_address.getText().isEmpty()
	                || appointment_status.getSelectionModel().getSelectedItem() == null
	                || appointment_schedule.getValue() == null) {
	            alert.ErrorMessage("Please fill the blank fields");
	        } else {
	            // TO GET THE DATE TODAY
	            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

	            String updateData = "UPDATE appointmentdata2 SET name = '"
	                    + appointment_name.getText() + "', gender = '"
	                    + appointment_gender.getSelectionModel().getSelectedItem() + "', mobileNumber = '"
	                    + appointment_mobileNumber.getText() + "', description = '"
	                    + appointment_description.getText() + "', address = '"
	                    + appointment_address.getText() + "', status = '"
	                    + appointment_status.getSelectionModel().getSelectedItem() + "', schedule = '"
	                    + appointment_schedule.getValue() + "', dateModify = '"
	                    + sqlDate + "' WHERE appointmentID = '"
	                    + appointment_appointmentID.getText() + "'";

	            Connection conn = DatabaseConnection_new.getConnection();

	            try {
	                if (alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: "
	                        + appointment_appointmentID.getText() + "?")) {
	                	 PreparedStatement stmt = conn.prepareStatement(updateData);

	                	 stmt.executeUpdate();

	                    appointmentShowData();
	                    appointmentAppointmentID();
	                    // appointmentClearBtn();
	                    alert.SuccesMessage("Successully Updated!");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    @FXML
	    void logoutBtn(ActionEvent event) {
	    	 try {
	             if (alert.confirmationMessage("Are you sure you want to logout?")) {
	                 Data.doctor_id = "";
	                 Data.doctor_name = "";
	                 Parent root = FXMLLoader.load(getClass().getResource("Doctor_por.fxml"));
	                 Stage stage = new Stage();
	                 stage.setScene(new Scene(root));
	                 stage.show();

	                 // TO HIDE YOUR MAIN FORM
	                 logout_btn.getScene().getWindow().hide();

	                 Data.doctor_id = "";
	                 Data.doctor_name = "";
	                 Data.temp_PatientID = "";
	                 Data.temp_name = "";
	                 Data.temp_gender = "";
	                 Data.temp_number = Long.parseLong("0");
	                 Data.temp_address = "";
	                 Data.temp_status = "";
	                 Data.temp_date = "";
	                 Data.temp_path = "";

	             }
	         } catch (Exception e) {
	             e.printStackTrace();
	         }

	    }

	    @FXML
	    void patientAddBtn(ActionEvent event) {
	    	 if (patients_PA_patientID.getText().isEmpty()
	                 || patients_PA_password.getText().isEmpty()
	                 || patients_PA_dateCreated.getText().isEmpty()
	                 || patients_PI_patientName.getText().isEmpty()
	                 || patients_PI_gender.getText().isEmpty()
	                 || patients_PI_mobileNumber.getText().isEmpty()
	                 || patients_PI_address.getText().isEmpty()) {
	             alert.ErrorMessage("Something wenr wrong");
	         } else {

	        	  Connection conn = DatabaseConnection_new.getConnection();
	             try {
	                 String doctorSpecialized = "";

	                 String getDoctor = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
	                         + nav_adminID.getText() + "'";

	                 PreparedStatement stmt = conn.prepareStatement(getDoctor);
	                 ResultSet resultSet = stmt.executeQuery();

	                 if (resultSet.next()) {
	                     resultSet.getString("DOCTOR_NAME");
	                     doctorSpecialized = resultSet.getString("Dr_specialized");
	                 }
	                 // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING ALREADY
	                 String checkPatientID = "SELECT * FROM patientmain WHERE PATIENT_ID = '"
	                         + patients_PA_patientID.getText() + "'";
	                  stmt  = conn.prepareStatement(checkPatientID);
	                  resultSet = stmt.executeQuery();
	                 if (resultSet.next()) {
	                     alert.ErrorMessage(patients_PA_patientID.getText() + " is already exist");
	                 } else {
	                     String insertData = "INSERT INTO patientmain (PATIENT_ID, PATIENT_PASSWORD, PATIENT_NAME, MOb_NUmber, "
	                             + "Patient_Address, Pt_doctor, Pt_specialized, Pt_date, "
	                             + "Pt_status) "
	                             + "VALUES(?,?,?,?,?,?,?,?,?)";
	                     Date date = new Date();
	                     java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                     PreparedStatement stmt3 = conn.prepareStatement(insertData);
	                     stmt3.setString(1, patients_PA_patientID.getText());
	                     stmt3.setString(2, patients_PA_password.getText());
	                     stmt3.setString(3, patients_PI_patientName.getText());
	                     stmt3.setString(4, patients_PI_mobileNumber.getText());
	                     stmt3.setString(5, patients_PI_address.getText());
	                     stmt3.setString(6, nav_adminID.getText());
	                     stmt3.setString(7, doctorSpecialized);
	                     stmt3.setString(8, "" + sqlDate);
	                     stmt3.setString(9, "Confirm");

	                     stmt3.executeUpdate();

	                     alert.SuccesMessage("Added successfully!");
	                     // TO CLEAR ALL FIELDS AND SOME LABELS
	                     patientClearFields();
	                 }

	             } catch (Exception e) {
	                 e.printStackTrace();
	             }
	 // NOW, LETS TRY 
	         }
	     }
	    public void profileDisplayImages() {

	        String selectData = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
	                + Data.doctor_id + "'";
	        String temp_path1 = "";
	        String temp_path2 = "";
	        Connection conn = DatabaseConnection_new.getConnection();

	        try {

                PreparedStatement stmt = conn.prepareStatement(selectData);
                ResultSet resultSet = stmt.executeQuery();

	            if (resultSet.next()) {
	                temp_path1 = "File:" + resultSet.getString("Dr_image");
	                temp_path2 = "File:" + resultSet.getString("Dr_image");

	                if (resultSet.getString("Dr_image") != null) {
	                    image = new Image(temp_path1, 1012, 22, false, true);
	                    top_profile.setFill(new ImagePattern(image));

	                    image = new Image(temp_path2, 128, 103, false, true);
	                    profile_circleImage.setFill(new ImagePattern(image));
	                }

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	    

	    @FXML
	    void patientConfirmBtn(ActionEvent event) {
	    	        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
	    	        if (patients_patientID.getText().isEmpty()
	    	                || patients_patientName.getText().isEmpty()
	    	                || patients_gender.getSelectionModel().getSelectedItem() == null
	    	                || patients_mobileNumber.getText().isEmpty()
	    	                || patients_password.getText().isEmpty()
	    	                || patients_address.getText().isEmpty()) {
	    	            alert.ErrorMessage("Please fill all blank fields");
	    	        } else {
	    	            Date date = new Date();
	    	            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	    	            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT 
	    	            patients_PA_patientID.setText(patients_patientID.getText());
	    	            patients_PA_password.setText(patients_password.getText());
	    	            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

	    	            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION 
	    	            patients_PI_patientName.setText(patients_patientName.getText());
	    	            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
	    	            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
	    	            patients_PI_address.setText(patients_address.getText());
	    	        }    
	    }
	    @FXML
	    void patientRecordBtn(ActionEvent event) {
	    	 try {
		            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
		            Parent root = FXMLLoader.load(getClass().getResource("RecordPage_Form.fxml"));
		            Stage stage = new Stage();

		            stage.setTitle("Hospital Management System | Record of Patients");
		            stage.setScene(new Scene(root));
		            stage.show();

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void profileChangeProfile(ActionEvent event) {

	        FileChooser open = new FileChooser();
	        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

	        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

	        if (file != null) {
	            Data.path = file.getAbsolutePath();

	            image = new Image(file.toURI().toString(), 128, 103, false, true);
	            profile_circleImage.setFill(new ImagePattern(image));
	        }
	    }

	    @FXML
	    void profileUpdateBtn(ActionEvent event) {
	    	 Connection conn = DatabaseConnection_new.getConnection();

	          if (profile_doctorID.getText().isEmpty()
	                  || profile_name.getText().isEmpty()
	                  || profile_email.getText().isEmpty()
	                  || profile_gender.getSelectionModel().getSelectedItem() == null
	                  || profile_mobileNumber.getText().isEmpty()
	                  || profile_address.getText().isEmpty()
	                  || profile_specialized.getSelectionModel().getSelectedItem() == null
	                  || profile_status.getSelectionModel().getSelectedItem() == null) {
	              alert.ErrorMessage("Please fill all blank fields");
	          } else {
	              // CHECK IF THE PATH IS NULL 
	              if (Data.path == null || "".equals(Data.path)) {
	                  String updateData = "UPDATE doctordata_main SET DOCTOR_NAME = ?, DOCTOR_EMAIL = ?"
	                          + ", Dr_gender = ?, Dr_MOb_NUmber = ?, Dr_Address = ?, Dr_specialized = ?, Dr_status = ?, Dr_dateModify = ?"
	                          + " WHERE DOCTOR_ID = '"
	                          + Data.doctor_id + "'";
	                  try {
	                      Date date = new Date();
	                      java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                      PreparedStatement stmt = conn.prepareStatement(updateData);
	                      stmt.setString(1, profile_name.getText());
	                      stmt.setString(2, profile_email.getText());
	                      stmt.setString(3, profile_gender.getSelectionModel().getSelectedItem());
	                      stmt.setString(4, profile_mobileNumber.getText());
	                      stmt.setString(5, profile_address.getText());
	                      stmt.setString(6, profile_specialized.getSelectionModel().getSelectedItem());
	                      stmt.setString(7, profile_status.getSelectionModel().getSelectedItem());
	                      stmt.setString(8, String.valueOf(sqlDate));

	                      stmt.executeUpdate();

	                      alert.SuccesMessage("Updated Successfully!");
	                  } catch (Exception e) {
	                      e.printStackTrace();
	                  }
	              } else {
	                  String updateData = "UPDATE doctordata_main SET DOCTOR_NAME = ?, DOCTOR_EMAIL = ?"
	                          + ", Dr_gender = ?, Dr_MOb_NUmber = ?, Dr_Address = ?, Dr_image = ?, Dr_specialized = ?, Dr_status = ?, Dr_dateModify = ?"
	                          + " WHERE DOCTOR_ID = '"
	                          + Data.doctor_id + "'";
	                  try {
	                      Date date = new Date();
	                      java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                      PreparedStatement stmt = conn.prepareStatement(updateData);
	                      stmt.setString(1, profile_name.getText());
	                      stmt.setString(2, profile_email.getText());
	                      stmt.setString(3, profile_gender.getSelectionModel().getSelectedItem());
	                      stmt.setString(4, profile_mobileNumber.getText());
	                      stmt.setString(5, profile_address.getText());
	                      String path = Data.path;
	                      path = path.replace("\\", "\\\\");
	                      Path transfer = Paths.get(path);

	                      // LINK YOUR DIRECTORY FOLDER
	                      Path copy = Paths.get("C:\\Users\\ASUS\\eclipse-workspace\\HMS_New\\src\\Dr_Directory\\"
	                              + Data.doctor_id + ".jpg");

	                      try {
	                          // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
	                          Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
	                      } catch (Exception e) {
	                          e.printStackTrace();
	                      }
	                      stmt.setString(6, copy.toAbsolutePath().toString());
	                      stmt.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
	                      stmt.setString(8, profile_status.getSelectionModel().getSelectedItem());
	                      stmt.setString(9, String.valueOf(sqlDate));

	                      stmt.executeUpdate();

	                      alert.SuccesMessage("Updated Successfully!");
	                  } catch (Exception e) {
	                      e.printStackTrace();
	                  }
	              }
	          }
	    }
	    @FXML
	    public void switchForm(ActionEvent event) {
	        if (event.getSource() == dashboard_btn) {
	            dashboard_form.setVisible(true);
	            patients_form.setVisible(false);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == patients_btn) {
	            dashboard_form.setVisible(false);
	            patients_form.setVisible(true);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == appointments_btn) {
	            dashboard_form.setVisible(false);
	            patients_form.setVisible(false);
	            appointments_form.setVisible(true);
	            profile_form.setVisible(false);
	        } else if (event.getSource() == profile_btn) {
	            dashboard_form.setVisible(false);
	            patients_form.setVisible(false);
	            appointments_form.setVisible(false);
	            profile_form.setVisible(true);
	        }
	    }
	    public void displayAdminIDNumberName() {

	        String name = Data.doctor_name;
	        name = name.substring(0, 1).toUpperCase() + name.substring(1);

	        nav_username.setText(name);
	        nav_adminID.setText(Data.doctor_id);
	        top_username.setText(name);

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
	   
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			runTime();
			displayAdminIDNumberName(); 
			appointmentShowData();
			appointmentGenderList();
			appointmentStatusList();
			//appointmentClearBtn();
			//appointmentInsertBtn();
			//appointmentGetAppointmentID();
			appointmentAppointmentID();
			patientGenderList();
			
			profileLabels();
			profileFields();
			profileStatusList();
			profileGenderList();
			profileSpecializedList();
			profileDisplayImages();
			
			dashbboardDisplayIP();
			dashbboardDisplayTP();
			dashbboardDisplayAP();
			dashbboardDisplayTA();
			dashboardDisplayData();
			try {
				dashboardNOP();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 dashboardNOA();
			
		}

	}


