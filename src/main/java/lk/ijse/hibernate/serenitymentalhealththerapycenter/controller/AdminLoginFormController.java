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

public class AdminLoginFormController {

    @FXML
    private ImageView adminLoginBackIcon;

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private Button btnAdminLogin;

    @FXML
    private CheckBox checkShow;

    @FXML
    private PasswordField txtAdminPassword;

    @FXML
    private TextField txtAdminUserName;

    @FXML
    void adminLoginBackIconOnAction(MouseEvent event) throws IOException {
        adminLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        adminLoginPane.getChildren().add(load);
    }

    @FXML
    void btnAdminLoginOnAction(ActionEvent event) {

    }

    @FXML
    void checkShowOnAction(ActionEvent event) {

    }

    @FXML
    void txtAdminPasswordOnKeyReleased(KeyEvent event) {

    }
}
