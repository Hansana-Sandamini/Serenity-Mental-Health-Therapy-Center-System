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

    private TextField txtPlainPassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void btnReceptionistLoginOnAction(ActionEvent event) throws IOException {
        String username = txtReceptionistUserName.getText();
        String password = checkShow.isSelected() ? txtPlainPassword.getText() : txtReceptionistPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Username and password cannot be empty!").show();
            return;
        }

        try {
            if (userBO.verifyUser(username, password, "Receptionist")) {
                receptionistLoginPane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml"));
                receptionistLoginPane.getChildren().add(load);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error during login: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void checkShowOnAction(ActionEvent event) {
        AnchorPane nestedPane = (AnchorPane) txtReceptionistPassword.getParent();

        if (checkShow.isSelected()) {
            txtPlainPassword = new TextField();
            txtPlainPassword.setText(txtReceptionistPassword.getText());
            txtPlainPassword.setPromptText(txtReceptionistPassword.getPromptText());

            txtPlainPassword.setLayoutX(txtReceptionistPassword.getLayoutX());
            txtPlainPassword.setLayoutY(txtReceptionistPassword.getLayoutY());
            txtPlainPassword.setPrefWidth(txtReceptionistPassword.getPrefWidth());
            txtPlainPassword.setPrefHeight(txtReceptionistPassword.getPrefHeight());

            txtPlainPassword.setStyle(txtReceptionistPassword.getStyle());
            txtPlainPassword.getStyleClass().addAll(txtReceptionistPassword.getStyleClass());
            txtPlainPassword.setFont(txtReceptionistPassword.getFont());
            txtPlainPassword.setAlignment(txtReceptionistPassword.getAlignment());
            txtPlainPassword.setPadding(txtReceptionistPassword.getPadding());

            nestedPane.getChildren().remove(txtReceptionistPassword);
            nestedPane.getChildren().add(txtPlainPassword);
        } else {
            txtReceptionistPassword.setText(txtPlainPassword.getText());
            nestedPane.getChildren().remove(txtPlainPassword);
            nestedPane.getChildren().add(txtReceptionistPassword);
            txtPlainPassword = null;
        }
    }

    @FXML
    void receptionistLoginBackIconOnAction(MouseEvent event) throws IOException {
        receptionistLoginPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomeForm.fxml"));
        receptionistLoginPane.getChildren().add(load);
    }

    @FXML
    void txtReceptionistPasswordOnKeyReleased(KeyEvent event) {
        if (checkShow.isSelected() && txtPlainPassword != null) {
            txtPlainPassword.setText(txtReceptionistPassword.getText());
        }
    }

}
