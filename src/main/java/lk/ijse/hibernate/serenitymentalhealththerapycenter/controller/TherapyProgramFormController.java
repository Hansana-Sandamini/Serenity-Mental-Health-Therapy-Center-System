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
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapyProgramBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.TherapyProgramTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgramFormController implements Initializable {

    @FXML
    private Button btnAddNewProgram;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapyProgramTM, String> colDuration;

    @FXML
    private TableColumn<TherapyProgramTM, BigDecimal> colFee;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramID;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramName;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<TherapyProgramTM> tblTherapyPrograms;

    @FXML
    private AnchorPane therapyProgramPane;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramID;

    @FXML
    private TextField txtProgramName;

    TherapyProgramBO programBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

    @FXML
    void btnAddNewProgramOnAction(ActionEvent event) throws Exception {
        setFormEnabled(true);
        clearFields();
        txtProgramName.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedProgramId = tblTherapyPrograms.getSelectionModel().getSelectedItem().getProgramId();

        if (selectedProgramId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this Therapy Program?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    programBO.deleteTherapyProgram(selectedProgramId);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Therapy Program Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Therapy Program selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            TherapyProgramDTO programDTO = getTextFieldsValues();

            boolean isSaved = programBO.saveTherapyProgram(programDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Save Therapy Program...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedProgramId = tblTherapyPrograms.getSelectionModel().getSelectedItem().getProgramId();

        if (selectedProgramId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this Therapy Program?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                if (validateTextFields()) {
                    try {
                        TherapyProgramDTO programDTO = getTextFieldsValues();
                        programBO.updateTherapyProgram(programDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Therapy Program Updated...!").show();
                        refreshPage();

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Fail to Update Therapy Program...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    public void navigateToHome(String fxmlPath) {
        try {
            therapyProgramPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            therapyProgramPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
        }
    }

    @FXML
    void homeIconOnAction(MouseEvent event) {
        navigateToHome("/view/AdminDashboardForm.fxml");
    }

    @FXML
    void tblTherapyProgramsOnClicked(MouseEvent event) {
        setFormEnabled(true);
        TherapyProgramTM selectedItem = tblTherapyPrograms.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtProgramID.setText(selectedItem.getProgramId());
            txtProgramName.setText(selectedItem.getProgramName());
            txtDuration.setText(selectedItem.getDuration());
            txtFee.setText(selectedItem.getFee().toString());
        }
    }

    TherapyProgramDTO getTextFieldsValues() {
        String programId = txtProgramID.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        BigDecimal fee = new BigDecimal(txtFee.getText());

        return new TherapyProgramDTO(programId, programName, duration, fee);
    }

    boolean validateTextFields() {
        boolean isValidFee = ValidationUtil.isValidAmount(txtFee);

        return isValidFee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() throws Exception {
        txtProgramID.setText(programBO.getNextTherapyProgramId());
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
    }

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
    }

    private void refreshTable() throws Exception {
        List<TherapyProgramDTO> programDTOS = programBO.getAllTherapyPrograms();
        ObservableList<TherapyProgramTM> programTMS = FXCollections.observableArrayList();

        for (TherapyProgramDTO programDTO : programDTOS) {
            TherapyProgramTM programTM = new TherapyProgramTM(
                    programDTO.getProgramId(),
                    programDTO.getProgramName(),
                    programDTO.getDuration(),
                    programDTO.getFee()
            );
            programTMS.add(programTM);
        }
        tblTherapyPrograms.setItems(programTMS);
    }

    private void setFormEnabled(boolean enabled) {
        txtProgramName.setDisable(!enabled);
        txtDuration.setDisable(!enabled);
        txtFee.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);

        btnAddNewProgram.setDisable(enabled);
    }
}
