package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.PatientTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private Button btnAddNewPatient;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn<PatientTM, Integer> colAge;

    @FXML
    private TableColumn<PatientTM, String> colContactNumber;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, String> colPatientID;

    @FXML
    private TableColumn<PatientTM, Date> colRegistrationDate;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private AnchorPane patientPane;

    @FXML
    private TableView<PatientTM> tblPatients;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPatientID;

    @FXML
    private DatePicker txtRegistrationDate;

    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @FXML
    void btnAddNewPatientOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTitleOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblPatientsOnClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtPatientID.setText(patientBO.getNextPatientId());
        txtName.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtRegistrationDate.setValue(LocalDate.now());
    }

    private void refreshTable() throws Exception {
        List<PatientDTO> patientDTOS = patientBO.getAllPatients();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM(
                    patientDTO.getPatientId(),
                    patientDTO.getName(),
                    patientDTO.getContactNumber(),
                    patientDTO.getEmail(),
                    patientDTO.getAge(),
                    patientDTO.getRegistrationDate()
            );
            patientTMS.add(patientTM);
        }
        tblPatients.setItems(patientTMS);
    }
}
