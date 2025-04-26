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
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.*;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.TherapySessionTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TherapySessionFormController implements Initializable {

    @FXML
    private Button btnAddNewSession;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPatientID;

    @FXML
    private ComboBox<String> cmbProgramID;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbTherapistID;

    @FXML
    private ComboBox<String> cmbTimeAmPm;

    @FXML
    private TableColumn<TherapySessionTM, String> colDate;

    @FXML
    private TableColumn<TherapySessionTM, String> colPatientID;

    @FXML
    private TableColumn<TherapySessionTM, String> colProgramID;

    @FXML
    private TableColumn<TherapySessionTM, String> colSessionID;

    @FXML
    private TableColumn<TherapySessionTM, String> colStatus;

    @FXML
    private TableColumn<TherapySessionTM, String> colTherapistID;

    @FXML
    private TableColumn<TherapySessionTM, String> colTime;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<TherapySessionTM> tblSessions;

    @FXML
    private AnchorPane therapySessionPane;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtSessionID;

    @FXML
    private TextField txtTime;

    TherapySessionBO sessionBO = (TherapySessionBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_SESSION);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapyProgramBO programBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    @FXML
    void btnAddNewSessionOnAction(ActionEvent event) throws Exception {
        setFormEnabled(true);
        clearFields();
        cmbPatientID.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedSessionId = tblSessions.getSelectionModel().getSelectedItem().getSessionId();

        if (selectedSessionId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to remove this Therapy Session?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    sessionBO.deleteTherapySession(selectedSessionId);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
                            "Therapy Session Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Therapy Session selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            TherapySessionDTO sessionDTO = getTextFieldsValues();

            boolean isSaved = sessionBO.saveTherapySession(sessionDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Session Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Therapy Session...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedSessionId = tblSessions.getSelectionModel().getSelectedItem().getSessionId();

        if (selectedSessionId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this Therapy Session?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                if (validateTextFields()) {
                    try {
                        TherapySessionDTO sessionDTO = getTextFieldsValues();
                        sessionBO.updateTherapySession(sessionDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Therapy Session Updated...!").show();
                        refreshPage();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to Update Therapy Session...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    @FXML
    void cmbPatientIDOnAction(ActionEvent event) throws Exception {
        String selectedPatientID = cmbPatientID.getSelectionModel().getSelectedItem();
        Patient patient = patientBO.searchPatient(selectedPatientID);

        if (patient != null) {
            cmbPatientID.setValue(patient.getPatientId());
        }
    }

    private void loadPatientIDs() throws Exception {
        List<String> patientIDs = patientBO.loadAllPatientIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(patientIDs);
        cmbPatientID.setItems(observableList);
    }

    @FXML
    void cmbProgramIDOnAction(ActionEvent event) throws Exception {
        String selectedProgramID = cmbProgramID.getSelectionModel().getSelectedItem();
        TherapyProgram program = programBO.searchProgram(selectedProgramID);

        if (program != null) {
            cmbProgramID.setValue(program.getProgramId());
        }
    }

    private void loadProgramIDs() throws Exception {
        List<String> programIDs = programBO.loadAllTherapyProgramIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(programIDs);
        cmbProgramID.setItems(observableList);
    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTherapistIDOnAction(ActionEvent event) throws Exception {
        String selectedTherapistID = cmbTherapistID.getSelectionModel().getSelectedItem();
        Therapist therapist = therapistBO.searchTherapist(selectedTherapistID);

        if (therapist != null) {
            cmbTherapistID.setValue(therapist.getTherapistId());
        }
    }

    private void loadTherapistIDs() throws Exception {
        List<String> therapistIDs = therapistBO.loadAllTherapistIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(therapistIDs);
        cmbTherapistID.setItems(observableList);
    }

    @FXML
    void cmbTimeAmPmOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblSessionsOnClicked(MouseEvent event) {
        setFormEnabled(true);
        TherapySessionTM selectedItem = tblSessions.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtSessionID.setText(selectedItem.getSessionId());
            cmbProgramID.setValue(selectedItem.getProgramId());
            cmbPatientID.setValue(selectedItem.getPatientId());
            cmbTherapistID.setValue(selectedItem.getTherapistId());
            txtDate.setValue(LocalDate.parse(selectedItem.getDate()));
            txtTime.setText(selectedItem.getTime());
            cmbStatus.setValue(selectedItem.getStatus());
        }
    }

    TherapySessionDTO getTextFieldsValues() {
        String sessionId = txtSessionID.getText();
        String programId = cmbProgramID.getValue();
        String patientId = cmbPatientID.getValue();
        String therapistId = cmbTherapistID.getValue();
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText() + " " + cmbTimeAmPm.getValue();
        String status = cmbStatus.getValue();

        return new TherapySessionDTO(sessionId, programId, patientId, therapistId, date, time, status);
    }

    boolean validateTextFields() throws Exception {
        boolean isValidTime = ValidationUtil.isValidTime(txtTime);
        boolean isPatientSelected = cmbPatientID.getValue() != null;
        boolean isProgramSelected = cmbProgramID.getValue() != null;
        boolean isTherapistSelected = cmbTherapistID.getValue() != null;
        boolean isStatusSelected = cmbStatus.getValue() != null;
        boolean isDateValid = txtDate.getValue() != null;

        boolean isTherapistAvailable = true;
        if (isTherapistSelected && isDateValid) {
            Therapist therapist = therapistBO.searchTherapist(cmbTherapistID.getValue());
            if (therapist != null) {
                List<TherapySession> sessions = sessionBO.getSessionsByTherapistAndDate(cmbTherapistID.getValue(), String.valueOf(txtDate.getValue()));
                if (!sessions.isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Selected therapist is not available on this date!").show();
                    isTherapistAvailable = false;
                }
            }
        }

        if (!isPatientSelected) {
            new Alert(Alert.AlertType.WARNING, "Please select a Patient!").show();
        }
        if (!isProgramSelected) {
            new Alert(Alert.AlertType.WARNING, "Please select a Program!").show();
        }
        if (!isTherapistSelected) {
            new Alert(Alert.AlertType.WARNING, "Please select a Therapist!").show();
        }
        if (!isStatusSelected) {
            new Alert(Alert.AlertType.WARNING, "Please select a Status!").show();
        }
        if (!isDateValid) {
            new Alert(Alert.AlertType.WARNING, "Please select a valid Date!").show();
        }

        return isValidTime && isPatientSelected && isProgramSelected &&
                isTherapistSelected && isStatusSelected && isDateValid && isTherapistAvailable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbStatus.getItems().addAll("Scheduled", "Completed", "Cancelled");
        cmbTimeAmPm.getItems().addAll("AM", "PM");

        colSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colTherapistID.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() throws Exception {
        txtSessionID.setText(sessionBO.getNextTherapySessionId());
        cmbProgramID.setValue("");
        cmbPatientID.setValue("");
        cmbTherapistID.setValue("");
        txtDate.setValue(LocalDate.now());
        cmbTimeAmPm.setValue("");
        txtTime.setText("");
        cmbStatus.setValue("");
    }

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
        loadTherapistIDs();
        loadProgramIDs();
        loadPatientIDs();
    }

    private void refreshTable() throws Exception {
        List<TherapySessionDTO> sessionDTOS = sessionBO.getAllTherapySessions();
        ObservableList<TherapySessionTM> sessionTMS = FXCollections.observableArrayList();

        for (TherapySessionDTO sessionDTO : sessionDTOS) {
            TherapySessionTM sessionTM = new TherapySessionTM(
                    sessionDTO.getSessionId(),
                    sessionDTO.getProgramId(),
                    sessionDTO.getPatientId(),
                    sessionDTO.getTherapistId(),
                    sessionDTO.getDate(),
                    sessionDTO.getTime(),
                    sessionDTO.getStatus()
            );
            sessionTMS.add(sessionTM);
        }
        tblSessions.setItems(sessionTMS);
    }

    private void setFormEnabled(boolean enabled) {
        cmbProgramID.setDisable(!enabled);
        cmbPatientID.setDisable(!enabled);
        cmbTherapistID.setDisable(!enabled);
        txtDate.setDisable(!enabled);
        txtTime.setDisable(!enabled);
        cmbTimeAmPm.setDisable(!enabled);
        cmbStatus.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);

        btnAddNewSession.setDisable(enabled);
    }
}

