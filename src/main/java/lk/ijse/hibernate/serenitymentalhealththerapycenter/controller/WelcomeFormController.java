package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WelcomeFormController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnReceptionist;

    @FXML
    private AnchorPane selectRolePane;

    @FXML
    private AnchorPane welcomePane;

    public void navigateTo(String fxmlPath) {
        try {
            welcomePane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            welcomePane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load page...!").show();
        }
    }

    @FXML
    void navigateToAdminLoginForm(ActionEvent event) {
        navigateTo("/view/AdminLoginForm.fxml");
    }

    @FXML
    void navigateToReceptionistLoginForm(ActionEvent event) {
        navigateTo("/view/ReceptionistLoginForm.fxml");
    }
}
