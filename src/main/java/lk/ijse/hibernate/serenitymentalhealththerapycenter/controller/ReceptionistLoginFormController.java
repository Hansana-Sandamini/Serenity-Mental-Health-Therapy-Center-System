package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReceptionistLoginFormController {

    @FXML
    private Button btnReceptionistLogin;

    @FXML
    private CheckBox checkShow;

    @FXML
    private ImageView receptionistLoginBackIcon;

    @FXML
    private AnchorPane receptionistLoginPane;

    @FXML
    private PasswordField txtReceptionistPassword;

    @FXML
    private TextField txtReceptionistUserName;

    @FXML
    void btnReceptionistLoginOnAction(ActionEvent event) throws IOException {
        receptionistLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml"));
        receptionistLoginPane.getChildren().add(load);
    }

    @FXML
    void checkShowOnAction(ActionEvent event) {

    }

    @FXML
    void receptionistLoginBackIconOnAction(MouseEvent event) throws IOException {
        receptionistLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        receptionistLoginPane.getChildren().add(load);
    }

    @FXML
    void txtReceptionistPasswordOnKeyReleased(KeyEvent event) {

    }

}
