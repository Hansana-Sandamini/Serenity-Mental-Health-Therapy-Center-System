package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.UserTM;

import java.net.URL;
import java.util.List;
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

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtUsername.setText("");
        txtPassword.setText("");
        txtName.setText("");
        cmbRole.setValue("");
        txtEmail.setText("");
        txtContactNumber.setText("");
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
}
