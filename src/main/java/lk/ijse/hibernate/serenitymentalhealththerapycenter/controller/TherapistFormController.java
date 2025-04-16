package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TherapistFormController {

    @FXML
    private Button btnAddNewTherapist;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbAvailabilityStatus;

    @FXML
    private ComboBox<?> cmbSpecialization;

    @FXML
    private ComboBox<?> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAvailabilityStatus;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSpecialization;

    @FXML
    private TableColumn<?, ?> colTherapistID;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<?> tblTherapists;

    @FXML
    private AnchorPane therapistPane;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTherapistID;

    @FXML
    void btnAddNewTherapistOnAction(ActionEvent event) {

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
    void cmbAvailabilityStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSpecializationOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTitleOnAction(ActionEvent event) {

    }

    public void navigateToHome(String fxmlPath) {
        try {
            therapistPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            therapistPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
        }
    }

    @FXML
    void homeIconOnAction(MouseEvent event) {
        navigateToHome("/view/AdminDashboardForm.fxml");
    }

    @FXML
    void tblTherapistsOnClicked(MouseEvent event) {

    }

}