package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapistBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.TherapistTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistFormController implements Initializable {

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
    private ComboBox<String> cmbAvailabilityStatus;

    @FXML
    private ComboBox<String> cmbSpecialization;

    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn<TherapistTM, String> colAvailabilityStatus;

    @FXML
    private TableColumn<TherapistTM, String> colContactNumber;

    @FXML
    private TableColumn<TherapistTM, String> colEmail;

    @FXML
    private TableColumn<TherapistTM, String> colName;

    @FXML
    private TableColumn<TherapistTM, String> colSpecialization;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistID;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<TherapistTM> tblTherapists;

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

    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistID.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAvailabilityStatus.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtTherapistID.setText(therapistBO.getNextTherapistId());
        cmbTitle.setValue("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        cmbAvailabilityStatus.setValue("");
        cmbSpecialization.setValue("");
    }

    private void refreshTable() throws Exception {
        List<TherapistDTO> therapistDTOS = therapistBO.getAllTherapists();
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

        for (TherapistDTO therapistDTO : therapistDTOS) {
            TherapistTM therapistTM = new TherapistTM(
                    therapistDTO.getTherapistId(),
                    therapistDTO.getName(),
                    therapistDTO.getContactNumber(),
                    therapistDTO.getEmail(),
                    therapistDTO.getAvailabilityStatus(),
                    therapistDTO.getSpecialization()
            );
            therapistTMS.add(therapistTM);
        }
        tblTherapists.setItems(therapistTMS);
    }
}