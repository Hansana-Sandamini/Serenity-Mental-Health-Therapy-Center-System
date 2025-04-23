package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;

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

    private TextField txtPlainPassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void adminLoginBackIconOnAction(MouseEvent event) throws IOException {
        adminLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        adminLoginPane.getChildren().add(load);
    }

    @FXML
    void btnAdminLoginOnAction(ActionEvent event) throws IOException {
//        String username = txtAdminUserName.getText();
//        String password = checkShow.isSelected() ? txtPlainPassword.getText() : txtAdminPassword.getText();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "Username and password cannot be empty!").show();
//            return;
//        }
//
//        try {
//            if (userBO.verifyUser(username, password, "Admin")) {
                adminLoginPane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"));
                adminLoginPane.getChildren().add(load);
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
//            }
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Error during login: " + e.getMessage()).show();
//            e.printStackTrace();
//        }
    }

    @FXML
    void checkShowOnAction(ActionEvent event) {
        AnchorPane nestedPane = (AnchorPane) txtAdminPassword.getParent();

        if (checkShow.isSelected()) {
            txtPlainPassword = new TextField();
            txtPlainPassword.setText(txtAdminPassword.getText());
            txtPlainPassword.setPromptText(txtAdminPassword.getPromptText());

            txtPlainPassword.setLayoutX(txtAdminPassword.getLayoutX());
            txtPlainPassword.setLayoutY(txtAdminPassword.getLayoutY());
            txtPlainPassword.setPrefWidth(txtAdminPassword.getPrefWidth());
            txtPlainPassword.setPrefHeight(txtAdminPassword.getPrefHeight());

            txtPlainPassword.setStyle(txtAdminPassword.getStyle());
            txtPlainPassword.getStyleClass().addAll(txtAdminPassword.getStyleClass());
            txtPlainPassword.setFont(txtAdminPassword.getFont());
            txtPlainPassword.setAlignment(txtAdminPassword.getAlignment());
            txtPlainPassword.setPadding(txtAdminPassword.getPadding());

            nestedPane.getChildren().remove(txtAdminPassword);
            nestedPane.getChildren().add(txtPlainPassword);
        } else {
            txtAdminPassword.setText(txtPlainPassword.getText());
            nestedPane.getChildren().remove(txtPlainPassword);
            nestedPane.getChildren().add(txtAdminPassword);
            txtPlainPassword = null;
        }
    }

    @FXML
    void txtAdminPasswordOnKeyReleased(KeyEvent event) {
        if (checkShow.isSelected() && txtPlainPassword != null) {
            txtPlainPassword.setText(txtAdminPassword.getText());
        }
    }
}