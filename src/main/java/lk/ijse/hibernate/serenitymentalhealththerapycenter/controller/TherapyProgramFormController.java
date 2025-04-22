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
    void btnAddNewProgramOnAction(ActionEvent event) {

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

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtProgramID.setText(programBO.getNextTherapyProgramId());
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
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
}
