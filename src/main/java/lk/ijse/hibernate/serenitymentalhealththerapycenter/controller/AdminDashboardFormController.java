package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    private AnchorPane adminDashboardPane;

    @FXML
    private FontAwesomeIcon patientIcon;

    @FXML
    private FontAwesomeIcon paymentIcon;

    @FXML
    private FontAwesomeIcon reportIcon;

    @FXML
    private FontAwesomeIcon therapistIcon;

    @FXML
    private FontAwesomeIcon therapyProgramIcon;

    @FXML
    private FontAwesomeIcon therapySessionIcon;

    @FXML
    private FontAwesomeIcon userIcon;

    public void navigateTo(String fxmlPath) {
        try {
            adminDashboardPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            adminDashboardPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
        }
    }

    @FXML
    void patientIconOnAction(MouseEvent event) {
        navigateTo("/view/PatientForm.fxml");
    }

    @FXML
    void paymentIconOnAction(MouseEvent event) {

    }

    @FXML
    void reportIconOnAction(MouseEvent event) {

    }

    @FXML
    void therapistIconOnAction(MouseEvent event) {
        navigateTo("/view/TherapistForm.fxml");
    }

    @FXML
    void therapyProgramIconOnAction(MouseEvent event) {
        navigateTo("/view/TherapyProgramForm.fxml");
    }

    @FXML
    void therapySessionIconOnAction(MouseEvent event) {

    }

    @FXML
    void userIconOnAction(MouseEvent event) {

    }

}