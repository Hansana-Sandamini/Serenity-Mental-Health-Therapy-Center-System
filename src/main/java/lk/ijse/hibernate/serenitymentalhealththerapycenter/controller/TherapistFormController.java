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
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.TherapistTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
    void btnAddNewTherapistOnAction(ActionEvent event) throws Exception {
        setFormEnabled(true);
        clearFields();
        txtName.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedTherapistId = tblTherapists.getSelectionModel().getSelectedItem().getTherapistId();

        if (selectedTherapistId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Therapist?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    therapistBO.deleteTherapist(selectedTherapistId);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Therapist Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Therapist selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            TherapistDTO therapistDTO = getTextFieldsValues();

            boolean isSaved = therapistBO.saveTherapist(therapistDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Save Therapist...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedTherapistId = tblTherapists.getSelectionModel().getSelectedItem().getTherapistId();

        if (selectedTherapistId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this Therapist?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                if (validateTextFields()) {
                    try {
                        TherapistDTO therapistDTO = getTextFieldsValues();
                        therapistBO.updateTherapist(therapistDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Therapist Updated...!").show();
                        refreshPage();

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Fail to Update Therapist...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
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
        setFormEnabled(true);
        TherapistTM selectedItem = tblTherapists.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] nameParts = selectedItem.getName().split(" ", 2);
            if (nameParts.length == 2) {
                cmbTitle.setValue(nameParts[0]);
                txtName.setText(nameParts[1]);
            } else {
                cmbTitle.setValue("");
                txtName.setText(selectedItem.getName());
            }
            txtTherapistID.setText(selectedItem.getTherapistId());
            txtContactNumber.setText(selectedItem.getContactNumber());
            txtEmail.setText(selectedItem.getEmail());
            cmbAvailabilityStatus.setValue(selectedItem.getAvailabilityStatus());
            cmbSpecialization.setValue(selectedItem.getSpecialization());
        }
    }

    TherapistDTO getTextFieldsValues() {
        String therapistId = txtTherapistID.getText();
        String name = cmbTitle.getValue() + " " + txtName.getText();
        String contactNumber = txtContactNumber.getText();
        String email = txtEmail.getText();
        String availabilityStatus = cmbAvailabilityStatus.getValue();
        String specialization = cmbSpecialization.getValue();

        return new TherapistDTO(therapistId, name, contactNumber, email, availabilityStatus, specialization);
    }

    boolean validateTextFields() {
        boolean isValidName = ValidationUtil.isValidName(txtName);
        boolean isValidEmail = ValidationUtil.isValidEmail(txtEmail);
        boolean isValidContactNumber = ValidationUtil.isValidContactNumber(txtContactNumber);

        return isValidName && isValidEmail && isValidContactNumber;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbTitle.getItems().addAll("Dr.", "Prof.", "Mr.", "Mrs.", "Ms.");
        cmbAvailabilityStatus.getItems().addAll("Available", "On Leave", "Not Available");
        cmbSpecialization.getItems().addAll(
                "Clinical Psychology",
                "Counseling Psychology",
                "Psychiatry",
                "Child Psychology",
                "Neuropsychology"
        );

        colTherapistID.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAvailabilityStatus.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() throws Exception {
        txtTherapistID.setText(therapistBO.getNextTherapistId());
        cmbTitle.setValue("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        cmbAvailabilityStatus.setValue("");
        cmbSpecialization.setValue("");
    }

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
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

    private void setFormEnabled(boolean enabled) {
        cmbTitle.setDisable(!enabled);
        txtName.setDisable(!enabled);
        txtContactNumber.setDisable(!enabled);
        txtEmail.setDisable(!enabled);
        cmbAvailabilityStatus.setDisable(!enabled);
        cmbSpecialization.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);

        btnAddNewTherapist.setDisable(enabled);
    }
}