package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReceptionistDashboardFormController {

    @FXML
    private AnchorPane receptionistDashboardPane;

    @FXML
    private FontAwesomeIcon patientIcon;

    @FXML
    private FontAwesomeIcon paymentIcon;

    @FXML
    private FontAwesomeIcon reportIcon;

    @FXML
    private FontAwesomeIcon therapySessionIcon;

    @FXML
    private FontAwesomeIcon userIcon;

    public void navigateTo(String fxmlPath) {
        try {
            receptionistDashboardPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            receptionistDashboardPane.getChildren().add(load);
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
        navigateTo("/view/PaymentForm.fxml");
    }

    @FXML
    void reportIconOnAction(MouseEvent event) {

    }

    @FXML
    void therapySessionIconOnAction(MouseEvent event) {
        navigateTo("/view/TherapySessionForm.fxml");
    }

    @FXML
    void userIconOnAction(MouseEvent event) {
        navigateTo("/view/UserForm.fxml");
    }

}
