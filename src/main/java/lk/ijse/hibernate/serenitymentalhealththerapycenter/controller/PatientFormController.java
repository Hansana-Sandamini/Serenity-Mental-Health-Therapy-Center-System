package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.PatientTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;

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
    void btnAddNewPatientOnAction(ActionEvent event) throws Exception {
        setFormEnabled(true);
        clearFields();
        txtName.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedPatientId = tblPatients.getSelectionModel().getSelectedItem().getPatientId();

        if (selectedPatientId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to remove this Patient?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    patientBO.deletePatient(selectedPatientId);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
                            "Patient Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Patient selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            PatientDTO patientDTO = getTextFieldsValues();

            boolean isSaved = patientBO.savePatient(patientDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Patient...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedPatientId = tblPatients.getSelectionModel().getSelectedItem().getPatientId();

        if (selectedPatientId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this Patient?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                if (validateTextFields()) {
                    try {
                        PatientDTO patientDTO = getTextFieldsValues();
                        patientBO.updatePatient(patientDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Patient Updated...!").show();
                        refreshPage();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to Update Patient...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    @FXML
    void cmbTitleOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblPatientsOnClicked(MouseEvent event) {
        setFormEnabled(true);
        PatientTM selectedItem = tblPatients.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtPatientID.setText(selectedItem.getPatientId());
            txtName.setText(selectedItem.getName());
            txtContactNumber.setText(selectedItem.getContactNumber());
            txtEmail.setText(selectedItem.getEmail());
            txtAge.setText(String.valueOf(selectedItem.getAge()));
            txtRegistrationDate.setValue(selectedItem.getRegistrationDate());
        }
    }

    PatientDTO getTextFieldsValues() {
        String patientId = txtPatientID.getText();
        String name = txtName.getText();
        String contactNumber = txtContactNumber.getText();
        String email = txtEmail.getText();
        Integer age = Integer.valueOf(txtAge.getText());
        LocalDate registrationDate = txtRegistrationDate.getValue();

        return new PatientDTO(patientId, name, contactNumber, email, age, registrationDate);
    }

    boolean validateTextFields() {
        boolean isValidName = ValidationUtil.isValidName(txtName);
        boolean isValidEmail = ValidationUtil.isValidEmail(txtEmail);
        boolean isValidContactNumber = ValidationUtil.isValidContactNumber(txtContactNumber);

        return isValidName && isValidEmail && isValidContactNumber;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbTitle.getItems().addAll("Mr.", "Mrs.", "Miss", "Dr.");
        cmbTitle.setValue("Mr.");

        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() throws Exception {
        txtPatientID.setText(patientBO.getNextPatientId());
        txtName.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtRegistrationDate.setValue(LocalDate.now());
    }

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
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

    private void setFormEnabled(boolean enabled) {
        cmbTitle.setDisable(!enabled);
        txtName.setDisable(!enabled);
        txtContactNumber.setDisable(!enabled);
        txtEmail.setDisable(!enabled);
        txtAge.setDisable(!enabled);
        txtRegistrationDate.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);

        btnAddNewPatient.setDisable(enabled);
    }
}
