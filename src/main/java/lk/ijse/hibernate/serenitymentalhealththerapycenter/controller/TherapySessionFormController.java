package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TherapySessionFormController {

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
    private ComboBox<?> cmbPatientID;

    @FXML
    private ComboBox<?> cmbProgramID;

    @FXML
    private ComboBox<?> cmbStatus;

    @FXML
    private ComboBox<?> cmbTherapistID;

    @FXML
    private ComboBox<?> cmbTimeAmPm;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colSessionID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTherapistID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<?> tblSessions;

    @FXML
    private AnchorPane therapySessionPane;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtSessionID;

    @FXML
    private TextField txtTime;

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

}

