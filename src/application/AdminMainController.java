
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;


public class AdminMainController implements Initializable {

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
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button doctors_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<String, Number> dashboad_chart_PD;

    @FXML
    private BarChart<String, Number> dashboad_chart_DD;


    @FXML
    private TableView<DoctorsData> dashboad_tableView;

    @FXML
    private TableColumn<DoctorsData, String> dashboad_col_doctorID;

    @FXML
    private TableColumn<DoctorsData, String> dashboad_col_name;

    @FXML
    private TableColumn<DoctorsData, String> dashboad_col_specialized;

    @FXML
    private TableColumn<DoctorsData, String> dashboad_col_status;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<DoctorsData> doctors_tableView;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_doctorID;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_name;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_gender;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_contactNumber;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_email;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_specialization;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_address;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_status;

    @FXML
    private TableColumn<DoctorsData, String> doctors_col_action;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TableView<PatientsData> patients_tableView;

    @FXML
    private TableColumn<PatientsData, String> patients_col_patientID;

    @FXML
    private TableColumn<PatientsData, String> patients_col_name;

    @FXML
    private TableColumn<PatientsData, String> patients_col_gender;

    @FXML
    private TableColumn<PatientsData, String> patients_col_contactNumber;

    @FXML
    private TableColumn<PatientsData, String> patients_col_description;

    @FXML
    private TableColumn<PatientsData, String> patients_col_date;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateModify;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateDelete;

    @FXML
    private TableColumn<PatientsData, String> patients_col_status;

    @FXML
    private TableColumn<PatientsData, String> patients_col_action;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<Appointment> appointments_tableView;

    @FXML
    private TableColumn<Appointment, String> appointments_appointmentID;

    @FXML
    private TableColumn<Appointment, String> appointments_name;

    @FXML
    private TableColumn<Appointment, String> appointments_gender;

    @FXML
    private TableColumn<Appointment, String> appointments_contactNumber;

    @FXML
    private TableColumn<Appointment, String> appointments_description;

    @FXML
    private TableColumn<Appointment, String> appointments_date;

    @FXML
    private TableColumn<Appointment, String> appointments_dateModify;

    @FXML
    private TableColumn<Appointment, String> appointments_dateDelete;

    @FXML
    private TableColumn<Appointment, String> appointments_status;

    @FXML
    private TableColumn<Appointment, String> appointments_action;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_adminIO;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_adminID;

    @FXML
    private TextField profile_username;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TableView<PatientsData> payment_tableView;

    @FXML
    private TableColumn<PatientsData, String> payment_col_patientID;

    @FXML
    private TableColumn<PatientsData, String> payment_col_name;

    @FXML
    private TableColumn<PatientsData, String> payment_col_gender;

    @FXML
    private TableColumn<PatientsData, String> payment_col_diagnosis;

    @FXML
    private TableColumn<PatientsData, String> payment_col_doctor;

    @FXML
    private TableColumn<PatientsData, String> payment_col_date;

    @FXML
    private Circle payment_circle;

    @FXML
    private Button payment_checkoutBtn;

    @FXML
    private Label payment_patientID;

    @FXML
    private Label payment_name;

    @FXML
    private Label payment_doctor;

    @FXML
    private Label payment_date;

    @FXML
    private Button logout_btn;
    
    private final statusLabel alert = new statusLabel();

	private Image image;
	
	public void dashboardAD() {

        String sql = "SELECT COUNT(id) FROM doctordata_main ";

        Connection conn = DatabaseConnection_new.getConnection();

        int tempAD = 0;
        try {

        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                tempAD = resultSet.getInt("COUNT(id)");
            }
            dashboard_AD.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTP() {

        String sql = "SELECT COUNT(id) FROM patientmain ";

        Connection conn = DatabaseConnection_new.getConnection();

        int tempTP = 0;
        try {

        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                tempTP = resultSet.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardAP() {

        String sql = "SELECT COUNT(id) FROM patientmain ";

        Connection conn = DatabaseConnection_new.getConnection();

        int tempAP = 0;
        try {

        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                tempAP = resultSet.getInt("COUNT(id)");
            }
            dashboard_AP.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTA() {

        String sql = "SELECT COUNT(id) FROM appointmentdata2 ";

        Connection conn = DatabaseConnection_new.getConnection();

        int tempTA = 0;
        try {

        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                tempTA = resultSet.getInt("COUNT(id)");
            }
            dashboard_AP.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public ObservableList<DoctorsData> dashboardGetDoctorData() {

        ObservableList<DoctorsData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM doctordata_main ";

        


        try (Connection conn = DatabaseConnection_new.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)){

        	
			 ResultSet resultSet = stmt.executeQuery();

			 DoctorsData dData;

            while (resultSet.next()) {
//                DoctorData(String doctorID, String fullName, String specialized, String status)
                dData = new DoctorsData(resultSet.getString("DOCTOR_ID"),
                		resultSet.getString("DOCTOR_NAME"), resultSet.getString("Dr_specialized"),
                		resultSet.getString("Dr_status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DoctorsData> dashboardGetDoctorListData;

    public void dashboardGetDoctorDisplayData() {
        dashboardGetDoctorListData = dashboardGetDoctorData();

        dashboad_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dashboad_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetDoctorListData);

    }
   public void dashboardPatientDataChart() {
        dashboad_chart_PD.getData().clear();

        String selectData = "SELECT IFNULL(Pt_date, 'No Date') AS Pt_date, COUNT(id) \r\n"
        		+ "FROM patientmain \r\n"
        		+ "GROUP BY Pt_date \r\n"
        		+ "ORDER BY Pt_date ASC LIMIT 8;\r\n"
        		+ "";

        Connection conn = DatabaseConnection_new.getConnection();
        XYChart.Series<String, Number> chart = new XYChart.Series<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(selectData);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            dashboad_chart_PD.getData().add( chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDoctorDataChart() throws SQLException {
        Connection conn = DatabaseConnection_new.getConnection();

        String query = "SELECT d.DOCTOR_NAME, COUNT(a.DOCTOR_ID) AS appointment_count " +
                       "FROM doctordata_main d " +
                       "LEFT JOIN appointmentdata2 a ON d.DOCTOR_ID = a.DOCTOR_ID " +
                       "GROUP BY d.DOCTOR_NAME";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("appointmentdata2");

        while (rs.next()) {
            String doctorName = rs.getString("DOCTOR_NAME");
            int appointmentCount = rs.getInt("appointment_count");

            series.getData().add(new XYChart.Data<>(doctorName, appointmentCount));
        }

        dashboad_chart_DD.getData().clear(); // Clear old data
        dashboad_chart_DD.getData().add(series);
    }



	public ObservableList<DoctorsData> doctorGetData() {
        ObservableList<DoctorsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM doctordata_main";

        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();
			 DoctorsData dData;
            while (resultSet.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
                dData = new DoctorsData(resultSet.getInt("id"), resultSet.getString("DOCTOR_ID"),
                		resultSet.getString("DOCTOR_PASSWORD"), resultSet.getString("DOCTOR_NAME"),
                		resultSet.getString("DOCTOR_EMAIL"), resultSet.getString("Dr_gender"),
                		resultSet.getLong("Dr_MOb_NUmber"), resultSet.getString("Dr_specialized"),
                		resultSet.getString("Dr_Address"), resultSet.getString("Dr_image"),
                		resultSet.getDate("Dr_date"), resultSet.getDate("Dr_dateModify"),
                		resultSet.getDate("Dr_dateDelete"), resultSet.getString("Dr_status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<DoctorsData> doctorListData;

    public void doctorDisplayData()
    {
        doctorListData = doctorGetData();

        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableView.setItems(doctorListData);

    }
    public void doctorActionButton() {

    	 Connection conn = DatabaseConnection_new.getConnection();

        doctorListData = doctorGetData();

        Callback<TableColumn<DoctorsData, String>, TableCell<DoctorsData, String>> cellFactory = (TableColumn<DoctorsData, String> param) -> {
            final TableCell<DoctorsData, String> cell = new TableCell<DoctorsData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                            	DoctorsData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                                int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.ErrorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_doctorID = pData.getDoctorID();
                                Data.temp_doctorName = pData.getFullName();
                                Data.temp_doctorEmail = pData.getEmail();
                                Data.temp_doctorPassword = pData.getPassword();
                                Data.temp_doctorSpecialized = pData.getSpecialized();
                                Data.temp_doctorGender = pData.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                                Data.temp_doctorAddress = pData.getAddress();
                                Data.temp_doctorStatus = pData.getStatus();
                                Data.temp_doctorImagePath = pData.getImage();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditDoctorPage.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                        	DoctorsData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                            int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.ErrorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "DELETE FROM doctordata_main WHERE DOCTOR_ID = ?";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                                	 PreparedStatement stmt = conn.prepareStatement(deleteData);
                        			// ResultSet resultSet = stmt.executeQuery();
                                   // Date date = new Date();
                                    //java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    //stmt.setString(1, String.valueOf(sqlDate));
                                	 stmt.setString(1, pData.getDoctorID());
                                    stmt.executeUpdate();

                                    doctorGetData();
                                    alert.SuccesMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctorListData);

    }

    public ObservableList<PatientsData> patientGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patientmain";

        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();
            PatientsData pData;

            while (resultSet.next()) {
//                PatientsData(Integer id, Integer patientID, String password, String fullName, Long mobileNumber
//            , String address, String image, String description, String diagnosis, String treatment
//            , String doctor, String specialized, Date date, Date dateModify
//            , Date dateDelete, String status)
                pData = new PatientsData(resultSet.getInt("id"), resultSet.getString("PATIENT_ID"),
                        resultSet.getString("PATIENT_PASSWORD"), resultSet.getString("PATIENT_NAME"),
                        resultSet.getLong("MOb_NUmber"), resultSet.getString("Patient_gender"),
                        resultSet.getString("Patient_Address"),
                        resultSet.getString("pt_image"), resultSet.getString("Pt_tdescription"),
                        resultSet.getString("Pt_diagnosis"),
                        resultSet.getString("Pt_treatment"), resultSet.getString("Pt_doctor"),
                        resultSet.getString("Pt_specialized"), resultSet.getDate("Pt_date"),
                        resultSet.getDate("Pt_dateModify"), resultSet.getDate("Pt_dateDelete"),
                        resultSet.getString("Pt_status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patients_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableView.setItems(patientListData);
    }
    public void patientActionButton() {

    	  Connection conn = DatabaseConnection_new.getConnection();
          patientListData = patientGetData();

        Callback<TableColumn<PatientsData, String>, TableCell<PatientsData, String>> cellFactory = (TableColumn<PatientsData, String> param) -> {
            final TableCell<PatientsData, String> cell = new TableCell<PatientsData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                                int num = patients_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.ErrorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_PatientID = pData.getPatientID();
                                Data.temp_address = pData.getAddress();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditPatientsForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                            int num = patients_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.ErrorMessage("Please select item first");
                                return;
                            }

                            String deleteData ="DELETE FROM patientmain WHERE PATIENT_ID = ?";
                                   // + pData.getPatientID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getPatientID() + "?")) {
                                	 PreparedStatement stmt = conn.prepareStatement(deleteData);
                                    //Date date = new Date();
                                   // java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                   // stmt.setString(1, String.valueOf(sqlDate));
                                	 stmt.setString(1, pData.getPatientID());
                                    stmt.executeUpdate();

                                    doctorGetData();
                                    alert.SuccesMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        patients_col_action.setCellFactory(cellFactory);
        patients_tableView.setItems(patientListData);

    }
    public ObservableList<Appointment> appointmentGetData() {

        ObservableList<Appointment> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointmentdata2";

        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet resultSet = stmt.executeQuery();

            Appointment aData;
            while (resultSet.next()) {
//            AppointmentData(Integer id, Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule)
                aData = new Appointment(resultSet.getInt("id"), resultSet.getString("appointmentID"),
                        resultSet.getString("name"), resultSet.getString("gender"), resultSet.getLong("mobileNumber"),
                        resultSet.getString("description"), resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"), resultSet.getString("address"),
                        resultSet.getString("doctor"), resultSet.getString("specialized"),
                        resultSet.getDate("date"), resultSet.getDate("dateModify"),
                        resultSet.getDate("dateDelete"), resultSet.getString("status"),
                        resultSet.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Appointment> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appointmentListData);

    }
    public void appointmentActionButton() {

    	  Connection conn = DatabaseConnection_new.getConnection();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>> cellFactory = (TableColumn<Appointment, String> param) -> {
            final TableCell<Appointment, String> cell = new TableCell<Appointment, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                            	Appointment aData = appointments_tableView.getSelectionModel().getSelectedItem();
                                int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.ErrorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_appID = String.valueOf(aData.getAppointmentID());
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                                Data.temp_appDoctor = aData.getDoctorID();
                                Data.temp_appSpecialized = aData.getSpecialized();
                                Data.temp_appStatus = aData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT APPOINTMENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditAppoint_Form.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                        	Appointment aData = appointments_tableView.getSelectionModel().getSelectedItem();
                            int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.ErrorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "DELETE FROM appointmentdata2 WHERE appointmentID = ?";
                                   // + aData.getAppointmentID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Appointment ID: " + aData.getAppointmentID() + "?")) {
                                	 PreparedStatement stmt = conn.prepareStatement(deleteData);
                                    //Date date = new Date();
                                    //java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    //stmt.setString(1, String.valueOf(sqlDate));
                                	stmt.setString(1, aData.getAppointmentID());
                                    stmt.executeUpdate();

                                    doctorGetData();
                                    alert.SuccesMessage("Deleted Successfully!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    @FXML
    void switchForm(ActionEvent event) {
    	
    	    if (event.getSource() == dashboard_btn) {
    	        dashboard_form.setVisible(true);
    	        appointments_form.setVisible(false);
    	        doctors_form.setVisible(false);
    	        patients_form.setVisible(false);
    	        payment_form.setVisible(false);
    	        profile_form.setVisible(false);
    	        
    	    } else if (event.getSource() == appointments_btn) {
    	        dashboard_form.setVisible(false);
    	        appointments_form.setVisible(true);
    	        doctors_form.setVisible(false);
    	        patients_form.setVisible(false);
    	        payment_form.setVisible(false);
    	        profile_form.setVisible(false);
    	    }
    	     else if (event.getSource() == doctors_btn) {
    	        dashboard_form.setVisible(false);
    	        appointments_form.setVisible(false);
    	        doctors_form.setVisible(true);
    	        patients_form.setVisible(false);
    	        payment_form.setVisible(false);
    	        profile_form.setVisible(false);
    	    }
    	     else if (event.getSource() == patients_btn) {
     	        dashboard_form.setVisible(false);
     	        appointments_form.setVisible(false);
     	        doctors_form.setVisible(false);
     	        patients_form.setVisible(true);
     	        payment_form.setVisible(false);
     	        profile_form.setVisible(false);
     	    }
    	     else if (event.getSource() == payment_btn) {
      	        dashboard_form.setVisible(false);
      	        appointments_form.setVisible(false);
      	        doctors_form.setVisible(false);
      	        patients_form.setVisible(false);
      	        payment_form.setVisible(true);
      	        profile_form.setVisible(false);
      	    }
    	     else if (event.getSource() == profile_btn) {
       	        dashboard_form.setVisible(false);
       	        appointments_form.setVisible(false);
       	        doctors_form.setVisible(false);
       	        patients_form.setVisible(false);
       	        payment_form.setVisible(false);
       	        profile_form.setVisible(true);
       	    }
    	}
    @FXML
    private void displayAdminIDUsername() {
      
       
    	 String query = "SELECT * FROM MAINADMINLOGIN WHERE ADMIN_USERNAME = '"
                 + Data.AD_USERNAME + "'";

        try (Connection conn = DatabaseConnection_new.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {

            	 nav_adminID.setText(resultSet.getString("ADMIN_ID"));
                 String tempUsername = resultSet.getString("ADMIN_USERNAME");
                 tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                 nav_username.setText(tempUsername);
                 top_username.setText(tempUsername);
                 
            }
        } catch (Exception e) {
        	 e.printStackTrace();
        }
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
    void logoutBtn(ActionEvent event) {
    	 try {
             if (alert.confirmationMessage("Are you sure you want to logout?")) {
                 Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
                 Stage stage = new Stage();

                 stage.setScene(new Scene(root));
                 stage.show();

                 // TO HIDE YOUR MAIN FORM
                 logout_btn.getScene().getWindow().hide();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

    }
    
    public ObservableList<PatientsData> paymentGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patientmain ";
        Connection conn = DatabaseConnection_new.getConnection();

        try {
        	 PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery();
            PatientsData pData;
            while (resultSet.next()) {
//                (Integer id, Integer patientID, String fullName, String gender
//            , String description, String diagnosis, String treatment
//            , String doctor, String image, Date date)
                pData = new PatientsData(resultSet.getInt("id"),
                        resultSet.getString("PATIENT_ID"), resultSet.getString("PATIENT_NAME"),
                        resultSet.getString("Patient_gender"), resultSet.getString("Pt_tdescription"),
                        resultSet.getString("Pt_diagnosis"), resultSet.getString("Pt_treatment"),
                        resultSet.getString("Pt_doctor"), resultSet.getString("pt_image"), resultSet.getDate("Pt_date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        payment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        payment_tableView.setItems(paymentListData);

    }
    @FXML
    void paymentCheckOutBtn(ActionEvent event) {
    	 try {
             Parent root = FXMLLoader.load(getClass().getResource("CheckOut_patient.fxml"));
             Stage stage = new Stage();

             stage.setTitle("Hospital Management System | Check-Out");
             stage.setScene(new Scene(root));
             stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    void paymentSelectItems(MouseEvent event) {
    	 PatientsData pData = payment_tableView.getSelectionModel().getSelectedItem();
         int num = payment_tableView.getSelectionModel().getSelectedIndex();

         if ((num - 1) < -1) {
             return;
         }
         if (pData.getImage() != null) {
             String path = "File:" + pData.getImage();
             image = new Image(path, 144, 105, false, true);
             payment_circle.setFill(new ImagePattern(image));

             Data.temp_path = pData.getImage();
         }

         Data.temp_PatientID = pData.getPatientID();
         Data.temp_name = pData.getFullName();
         Data.temp_date = String.valueOf(pData.getDate());
        
         
         
         payment_patientID.setText(String.valueOf(pData.getPatientID()));
         payment_name.setText(pData.getFullName());
         payment_doctor.setText(pData.getDoctor());
         payment_date.setText(String.valueOf(pData.getDate()));

    }
    @FXML
    void profileInsertImage(ActionEvent event) {
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
         if (profile_adminID.getText().isEmpty()
                 || profile_username.getText().isEmpty()
                 || profile_email.getText().isEmpty()
                 || profile_status.getSelectionModel().getSelectedItem() == null) {
             alert.ErrorMessage("Please fill all blank fields");
         } else {
             if (Data.path == null || "".equals(Data.path)) {
                 String updateData = "UPDATE mainadminlogin SET ADMIN_USERNAME = ?, ADMIN_EMAIL = ?, Ad_gender = ? "
                         + "WHERE admin_id = " + Data.AD_ID;

                 try {
                	 PreparedStatement stmt = conn.prepareStatement(updateData);
                	 stmt.setString(1, profile_username.getText());
                	 stmt.setString(2, profile_email.getText());
                	 stmt.setString(3, profile_status.getSelectionModel().getSelectedItem());

                	 stmt.executeUpdate();

                     profileDisplayInfo();

                     alert.SuccesMessage("Updated Successfully");
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             } else {
                 String updateData = "UPDATE mainadminlogin SET ADMIN_USERNAME = ?, ADMIN_EMAIL = ?, Ad_image = ?, Ad_gender = ? "
                         + "WHERE ADMIN_ID = " + Data.AD_ID;
                 try {
                	 PreparedStatement stmt = conn.prepareStatement(updateData);
                	 stmt.setString(1, profile_username.getText());
                	 stmt.setString(2, profile_email.getText());

                     String path = Data.path;
                     path = path.replace("\\", "\\\\");
                     Path transfer = Paths.get(path);

                     Path copy = Paths.get("C:\\Users\\ASUS\\eclipse-workspace\\HMS_New\\src\\Ad_Directory\\"
                             + Data.AD_ID + ".jpg");

                     Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                     stmt.setString(3, copy.toAbsolutePath().toString());
                     stmt.setString(4, profile_status.getSelectionModel().getSelectedItem());

                     stmt.executeUpdate();
                     profileDisplayInfo();
                     profileDisplayImages();
                     alert.SuccesMessage("Updated Successfully!");
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }
    }

  
    public void profileDisplayImages() {

        String selectData = "SELECT * FROM mainadminlogin WHERE ADMIN_ID = " + Data.AD_ID;
        Connection conn = DatabaseConnection_new.getConnection();
        String tempPath1 = "";
        String tempPath2 = "";
        try {
        	 PreparedStatement stmt = conn.prepareStatement(selectData);
                 ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                tempPath1 = "File:" + resultSet.getString("Ad_image");
                tempPath2 = "File:" + resultSet.getString("Ad_image");

                if (resultSet.getString("Ad_image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

  
    public void profileDisplayInfo() {

        String sql = "SELECT * FROM mainadminlogin WHERE ADMIN_ID = " + Data.AD_ID;
        System.out.println(Data.AD_ID);
       
        
        try (Connection conn = DatabaseConnection_new.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)){
             ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                profile_adminID.setText(resultSet.getString("ADMIN_ID"));
                profile_username.setText(resultSet.getString("ADMIN_USERNAME"));
                profile_email.setText(resultSet.getString("ADMIN_EMAIL"));
                profile_status.getSelectionModel().select(resultSet.getString("Ad_gender"));

                profile_label_adminIO.setText(resultSet.getString("ADMIN_ID"));
                profile_label_name.setText(resultSet.getString("ADMIN_USERNAME"));
                profile_label_email.setText(resultSet.getString("ADMIN_EMAIL"));
                profile_label_dateCreated.setText(resultSet.getString("Ad_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	public void profileStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.gender) {
            listS.add(data);
        }
        ObservableList<String> listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		runTime();
		displayAdminIDUsername();
		profileStatusList();
		 profileDisplayInfo();
		 profileDisplayImages();
		 //profileInsertImage();
		
		//doctorDisplayData();
		doctorActionButton();
		patientDisplayData();
		patientActionButton();
		
		appointmentDisplayData();
		appointmentActionButton();
		dashboardAD();
		dashboardTP();
		dashboardAP();
		dashboardTA();
		paymentDisplayData();
		
		dashboardGetDoctorDisplayData();
		dashboardPatientDataChart();
		try {
			dashboardDoctorDataChart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

   

}
