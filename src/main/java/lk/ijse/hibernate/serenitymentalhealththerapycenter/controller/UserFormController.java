package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private Button btnAddNewUser;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private CheckBox checkShow;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TableColumn<UserTM, String> colContactNumber;

    @FXML
    private TableColumn<UserTM, String> colName;

    @FXML
    private TableColumn<UserTM, String> colPassword;

    @FXML
    private TableColumn<UserTM, String> colEmail;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TableColumn<UserTM, String> colUsername;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<UserTM> tblUsers;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane userPane;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void btnAddNewUserOnAction(ActionEvent event) {
        setFormEnabled(true);
        clearFields();
        txtUsername.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedUser = tblUsers.getSelectionModel().getSelectedItem().getUsername();

        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this User?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    userBO.deleteUser(selectedUser);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "User Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshPage();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No User selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            UserDTO userDTO = getTextFieldsValues();

            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Save User...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedUser = tblUsers.getSelectionModel().getSelectedItem().getUsername();

        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this User?", ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {

                if (validateTextFields()) {
                    try {
                        UserDTO userDTO = getTextFieldsValues();
                        userBO.updateUser(userDTO);
                        new Alert(Alert.AlertType.INFORMATION, "User Updated...!").show();
                        refreshPage();

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Fail to Update User...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    @FXML
    void checkShowOnAction(ActionEvent event) {

    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblUsersOnClicked(MouseEvent event) {
        setFormEnabled(true);
        UserTM selectedItem = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtUsername.setText(selectedItem.getUsername());
            txtPassword.setText(selectedItem.getPassword());
            txtName.setText(selectedItem.getName());
            cmbRole.setValue(selectedItem.getRole());
            txtEmail.setText(selectedItem.getEmail());
            txtContactNumber.setText(selectedItem.getContactNumber());
        }
    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    UserDTO getTextFieldsValues() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String name = txtName.getText();
        String role = cmbRole.getValue();
        String email = txtEmail.getText();
        String contactNumber = txtContactNumber.getText();

        return new UserDTO(username, password, name, role, email, contactNumber);
    }

    boolean validateTextFields() {
        boolean isValidName = ValidationUtil.isValidName(txtName);
        boolean isValidEmail = ValidationUtil.isValidEmail(txtEmail);
        boolean isValidContactNumber = ValidationUtil.isValidContactNumber(txtContactNumber);

        return isValidName && isValidEmail && isValidContactNumber;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRole.getItems().addAll("Admin", "Receptionist");

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtName.setText("");
        cmbRole.setValue("");
        txtEmail.setText("");
        txtContactNumber.setText("");
    }

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
    }

    private void refreshTable() throws Exception {
        List<UserDTO> userDTOS = userBO.getAllUsers();
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

        for (UserDTO userDTO : userDTOS) {
            UserTM userTM = new UserTM(
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    userDTO.getName(),
                    userDTO.getRole(),
                    userDTO.getEmail(),
                    userDTO.getContactNumber()
            );
            userTMS.add(userTM);
        }
        tblUsers.setItems(userTMS);
    }

    private void setFormEnabled(boolean enabled) {
        txtUsername.setDisable(!enabled);
        txtPassword.setDisable(!enabled);
        txtName.setDisable(!enabled);
        cmbRole.setDisable(!enabled);
        txtEmail.setDisable(!enabled);
        txtContactNumber.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);

        btnAddNewUser.setDisable(enabled);
    }
}
