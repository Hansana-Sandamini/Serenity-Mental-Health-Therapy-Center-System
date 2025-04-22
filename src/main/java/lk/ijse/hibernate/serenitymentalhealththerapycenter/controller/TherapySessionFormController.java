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
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapySessionBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.TherapySessionTM;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableColumn<TherapySessionTM, Date> colDate;

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

    @FXML
    void btnAddNewSessionOnAction(ActionEvent event) {

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
    void cmbPatientIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProgramIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTherapistIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTimeAmPmOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblSessionsOnClicked(MouseEvent event) {

    }

    TherapySessionDTO getTextFieldsValues() {
        String sessionId = txtSessionID.getText();
        String programId = cmbProgramID.getValue();
        String patientId = cmbPatientID.getValue();
        String therapistId = cmbTherapistID.getValue();
        LocalDate date = txtDate.getValue();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String status = cmbStatus.getValue();

        return new TherapySessionDTO(sessionId, programId, patientId, therapistId, date, time, status);
    }

    boolean validateTextFields() {
        boolean isValidTime = ValidationUtil.isValidTime(txtTime);

        return isValidTime;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colTherapistID.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtSessionID.setText(sessionBO.getNextTherapySessionId());
        cmbProgramID.setValue("");
        cmbPatientID.setValue("");
        cmbTherapistID.setValue("");
        txtDate.setValue(LocalDate.now());
        cmbTimeAmPm.setValue("");
        txtTime.setText("");
        cmbStatus.setValue("");
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
}

