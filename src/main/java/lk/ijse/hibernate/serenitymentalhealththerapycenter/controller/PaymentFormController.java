package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.PaymentTM;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private Button btnAddNewPayment;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnViewInvoice;

    @FXML
    private ComboBox<String> cmbPatientID;

    @FXML
    private ComboBox<String> cmbProgramID;

    @FXML
    private ComboBox<String> cmbSessionID;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbTherapistID;

    @FXML
    private ComboBox<String> cmbTimeAmPm;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colAmount;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colAmountPaid;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colAmountToPay;

    @FXML
    private TableColumn<PaymentTM, Date> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colPatientID;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentID;

    @FXML
    private TableColumn<PaymentTM, String> colProgramID;

    @FXML
    private TableColumn<PaymentTM, String> colSessionID;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private TableColumn<PaymentTM, String> colTime;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private AnchorPane therapySessionPane;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtAmountPaid;

    @FXML
    private TextField txtAmountToPay;

    @FXML
    private TextField txtPaymentID;

    @FXML
    private TextField txtTime;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    @FXML
    void btnAddNewPaymentOnAction(ActionEvent event) {

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
    void btnViewInvoiceOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPatientIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProgramIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSessionIDOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTimeAmPmOnAction(ActionEvent event) {

    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

    @FXML
    void tblPaymentsOnClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        colAmountToPay.setCellValueFactory(new PropertyValueFactory<>("amountToPay"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {
        refreshTable();

        txtPaymentID.setText(paymentBO.getNextPaymentId());
        cmbPatientID.setValue("");
        cmbProgramID.setValue("");
        cmbSessionID.setValue("");
        txtAmount.setText("");
        txtAmountPaid.setText("");
        txtAmountToPay.setText("");
        txtDate.setValue(LocalDate.now());
        txtTime.setText("");
        cmbTimeAmPm.setValue("");
        cmbStatus.setValue("");
    }

    private void refreshTable() throws Exception {
        List<PaymentDTO> paymentDTOS = paymentBO.getAllPayments();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getPatientId(),
                    paymentDTO.getProgramId(),
                    paymentDTO.getSessionId(),
                    paymentDTO.getAmount(),
                    paymentDTO.getAmountPaid(),
                    paymentDTO.getAmountToPay(),
                    paymentDTO.getDate(),
                    paymentDTO.getTime(),
                    paymentDTO.getStatus()
            );
            paymentTMS.add(paymentTM);
        }
        tblPayments.setItems(paymentTMS);
    }
}
