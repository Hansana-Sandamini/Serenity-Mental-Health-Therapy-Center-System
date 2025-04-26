package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public void showPopup(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initStyle(StageStyle.DECORATED);
            popupStage.setTitle(title);
            popupStage.setScene(new Scene(root));

            popupStage.showAndWait();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load " + title + " window").show();
            e.printStackTrace();
        }
    }

    @FXML
    void patientIconOnAction(MouseEvent event) {
        showPopup("/view/PatientForm.fxml", "Patient Management");
    }

    @FXML
    void paymentIconOnAction(MouseEvent event) {
        showPopup("/view/PaymentForm.fxml", "Payment Management");
    }

    @FXML
    void reportIconOnAction(MouseEvent event) {
        showPopup("/view/ReportsForm.fxml", "Reports");
    }

    @FXML
    void therapistIconOnAction(MouseEvent event) {
        showPopup("/view/TherapistForm.fxml", "Therapist Management");
    }

    @FXML
    void therapyProgramIconOnAction(MouseEvent event) {
        showPopup("/view/TherapyProgramForm.fxml", "Therapy Program Management");
    }

    @FXML
    void therapySessionIconOnAction(MouseEvent event) {
        showPopup("/view/TherapySessionForm.fxml", "Therapy Session Management");
    }

    @FXML
    void userIconOnAction(MouseEvent event) {
        showPopup("/view/UserForm.fxml", "User Management");
    }

}