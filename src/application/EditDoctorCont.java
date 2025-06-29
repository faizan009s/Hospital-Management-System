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
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.stage.FileChooser;
	import javafx.stage.FileChooser.ExtensionFilter;

	public class EditDoctorCont implements Initializable{
	    @FXML
	    private TextField editDoctor_doctorID;

	    @FXML
	    private TextField editDoctor_fullName;

	    @FXML
	    private TextField editDoctor_email;

	    @FXML
	    private TextField editDoctor_password;

	    @FXML
	    private ComboBox<String> editDoctor_specialized;

	    @FXML
	    private ComboBox<String> editDoctor_gender;

	    @FXML
	    private TextField editDoctor_mobileNumber;

	    @FXML
	    private ImageView editDoctor_imageView;

	    @FXML
	    private Button editDoctor_importBtn;

	    @FXML
	    private TextArea editDoctor_address;

	    @FXML
	    private ComboBox<String> editDoctor_status;

	    @FXML
	    private Button editDoctor_updateBtn;

	    private final statusLabel alert = new statusLabel();
	    
	    private Image image;
	  
	    public void importBtn(ActionEvent event) {

	        FileChooser open = new FileChooser();
	        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*png", "*jpeg"));

	        File file = open.showOpenDialog(editDoctor_importBtn.getScene().getWindow());

	        if (file != null) {

	            Data.path = file.getAbsolutePath();

	            image = new Image(file.toURI().toString(), 112, 121, false, true);
	            editDoctor_imageView.setImage(image);

	        }

	    }

	    public void displayDoctorData() {

	        String sql = "SELECT * FROM doctordata_main WHERE DOCTOR_ID = '"
	                + editDoctor_doctorID.getText() + "'";
	        Connection conn = DatabaseConnection_new.getConnection();

	        try {
	        	PreparedStatement stmt = conn.prepareStatement(sql);
				 ResultSet resultSet = stmt.executeQuery();

	            if (resultSet.next()) {
	                editDoctor_fullName.setText(resultSet.getString("DOCTOR_NAME"));
	                editDoctor_email.setText(resultSet.getString("DOCTOR_EMAIL"));
	                editDoctor_password.setText(resultSet.getString("DOCTOR_PASSWORD"));
	                editDoctor_specialized.getSelectionModel().select(resultSet.getString("Dr_specialized"));
	                editDoctor_gender.getSelectionModel().select(resultSet.getString("Dr_gender"));
	                editDoctor_mobileNumber.setText(resultSet.getString("Dr_MOb_NUmber"));
	                editDoctor_address.setText(resultSet.getString("Dr_Address"));
	                editDoctor_status.getSelectionModel().select(resultSet.getString("Dr_status"));

	                image = new Image("File:" + resultSet.getString("Dr_image"), 112, 121, false, true);
	                editDoctor_imageView.setImage(image);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateBtn(ActionEvent event)  {
	    	 Connection conn = DatabaseConnection_new.getConnection();

	        if (editDoctor_doctorID.getText().isEmpty()
	                || editDoctor_fullName.getText().isEmpty()
	                || editDoctor_email.getText().isEmpty()
	                || editDoctor_password.getText().isEmpty()
	                || editDoctor_specialized.getSelectionModel().getSelectedItem() == null
	                || editDoctor_gender.getSelectionModel().getSelectedItem() == null
	                || editDoctor_mobileNumber.getText().isEmpty()
	                || editDoctor_address.getText().isEmpty()
	                || editDoctor_status.getSelectionModel().getSelectedItem() == null) {
	            alert.ErrorMessage("Please fill all blank fields");
	        } else {
	            Date date = new Date();
	            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	            if (Data.path == null || "".equals(Data.path)) {
	                String updateData = "UPDATE doctordata_main SET DOCTOR_NAME = '"
	                        + editDoctor_fullName.getText() + "', DOCTOR_EMAIL = '"
	                        + editDoctor_email.getText() + "', DOCTOR_PASSWORD = '"
	                        + editDoctor_password.getText() + "', Dr_specialized = '"
	                        + editDoctor_specialized.getSelectionModel().getSelectedItem() + "', Dr_gender = '"
	                        + editDoctor_gender.getSelectionModel().getSelectedItem() + "', Dr_MOb_NUmber = '"
	                        + editDoctor_mobileNumber.getText() + "', Dr_Address = '"
	                        + editDoctor_address.getText() + "', Dr_status = '"
	                        + editDoctor_status.getSelectionModel().getSelectedItem() + "', Dr_dateModify = '"
	                        + String.valueOf(sqlDate) + "' "
	                        + "WHERE DOCTOR_ID = '" + editDoctor_doctorID.getText() + "'";
	                try {
	                    if (alert.confirmationMessage("Are you sure you want to Update Doctor ID: " + editDoctor_doctorID.getText() + "?")) {
	                    	PreparedStatement stmt = conn.prepareStatement(updateData);
	                    	 stmt.executeUpdate();

	                    } else {
	                        alert.ErrorMessage("Cancelled.");
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            } else {
	                try {
	                    if (alert.confirmationMessage("Are you sure you want to Update Doctor ID: "
	                            + editDoctor_doctorID.getText() + "?")) {
	                        String path = Data.path;
	                        path = path.replace("\\", "\\\\");
	                        Path transfer = Paths.get(path);

	                        Path copy = Paths.get("C:\\Users\\ASUS\\eclipse-workspace\\HMS_New\\src\\Dr_Directory\\"
	                                + editDoctor_doctorID.getText() + ".jpg");

	                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

	                        String insertImage = copy.toString();
	                        insertImage = insertImage.replace("\\", "\\\\");
	                        
	                        String updateData = "UPDATE doctordata_main SET DOCTOR_NAME = '"
	                                + editDoctor_fullName.getText() + "', DOCTOR_EMAIL = '"
	                                + editDoctor_email.getText() + "', DOCTOR_PASSWORD = '"
	                                + editDoctor_password.getText() + "', Dr_specialized = '"
	                                + editDoctor_specialized.getSelectionModel().getSelectedItem() + "', Dr_gender = '"
	                                + editDoctor_gender.getSelectionModel().getSelectedItem() + "', Dr_MOb_NUmber = '"
	                                + editDoctor_mobileNumber.getText() + "', Dr_image = '"
	                                + insertImage + "', Dr_Address = '"
	                                + editDoctor_address.getText() + "', Dr_status = '"
	                                + editDoctor_status.getSelectionModel().getSelectedItem() + "' "
	                                + "WHERE DOCTOR_ID = '" + editDoctor_doctorID.getText() + "'";

	                        PreparedStatement stmt = conn.prepareStatement(updateData);
		       				  stmt.executeUpdate();


	                    } else {
	                        alert.ErrorMessage("Cancelled.");
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }

	            }
	        }
	        displayDoctorData();
	    }  @FXML
	   
	    public void setField() {
	        editDoctor_doctorID.setText(Data.temp_doctorID);
	        editDoctor_fullName.setText(Data.temp_doctorName);
	        editDoctor_email.setText(Data.temp_doctorEmail);
	        editDoctor_password.setText(Data.temp_doctorPassword);
	        editDoctor_specialized.getSelectionModel().select(Data.temp_doctorSpecialized);
	        editDoctor_gender.getSelectionModel().select(Data.temp_doctorGender);
	        editDoctor_mobileNumber.setText(Data.temp_doctorMobileNumber);
	        editDoctor_address.setText(Data.temp_doctorName);
	        editDoctor_status.getSelectionModel().select(Data.temp_doctorStatus);

	        image = new Image("File:" + Data.temp_doctorImagePath, 112, 121, false, true);
	        editDoctor_imageView.setImage(image);
	    }

	    public void specializationList() {
	        List<String> specializationL = new ArrayList<>();

	        for (String data : Data.specialization) {
	            specializationL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(specializationL);
	        editDoctor_specialized.setItems(listData);
	    }

	    public void genderList() {
	        List<String> genderL = new ArrayList<>();

	        for (String data : Data.gender) {
	            genderL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(genderL);
	        editDoctor_gender.setItems(listData);
	    }

	    public void statusList() {
	        List<String> statusL = new ArrayList<>();

	        for (String data : Data.status) {
	            statusL.add(data);
	        }

	        ObservableList<String> listData = FXCollections.observableList(statusL);
	        editDoctor_status.setItems(listData);
	    }

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        setField();
	        specializationList();
	        genderList();
	        statusList();
	        
	      
	    }

	}


