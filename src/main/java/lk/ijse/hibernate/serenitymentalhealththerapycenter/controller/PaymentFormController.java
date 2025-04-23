package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.*;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.util.ValidationUtil;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm.PaymentTM;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    TherapySessionBO sessionBO = (TherapySessionBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_SESSION);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapyProgramBO programBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

    @FXML
    void btnAddNewPaymentOnAction(ActionEvent event) throws Exception {
        setFormEnabled(true);
        clearFields();
        cmbPatientID.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String selectedPaymentId = tblPayments.getSelectionModel().getSelectedItem().getPaymentId();

        if (selectedPaymentId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to remove this Payment?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                try {
                    paymentBO.deletePayment(selectedPaymentId);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
                            "Payment Successfully Deleted...!");
                    successAlert.showAndWait();
                    refreshTable();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Payment selected to Remove...!").show();
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (validateTextFields()) {
            PaymentDTO paymentDTO = getTextFieldsValues();

            boolean isSaved = paymentBO.savePayment(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Payment...!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String selectedPaymentId = tblPayments.getSelectionModel().getSelectedItem().getPaymentId();

        if (selectedPaymentId != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this Payment?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                if (validateTextFields()) {
                    try {
                        PaymentDTO paymentDTO = getTextFieldsValues();
                        paymentBO.updatePayment(paymentDTO);
                        new Alert(Alert.AlertType.INFORMATION, "Payment Updated...!").show();
                        refreshPage();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to Update Payment...!").show();
                    }
                }
            } else {
                refreshPage();
            }
        }
    }

    @FXML
    void btnViewInvoiceOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPatientIDOnAction(ActionEvent event) throws Exception {
        String selectedPatientID = cmbPatientID.getSelectionModel().getSelectedItem();
        Patient patient = patientBO.searchPatient(selectedPatientID);

        if (patient != null) {
            cmbPatientID.setValue(patient.getPatientId());
        }
    }

    private void loadPatientIDs() throws Exception {
        List<String> patientIDs = patientBO.loadAllPatientIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(patientIDs);
        cmbPatientID.setItems(observableList);
    }

    @FXML
    void cmbProgramIDOnAction(ActionEvent event) throws Exception {
        String selectedProgramID = cmbProgramID.getSelectionModel().getSelectedItem();
        TherapyProgram program = programBO.searchProgram(selectedProgramID);

        if (program != null) {
            cmbProgramID.setValue(program.getProgramId());
        }
    }

    private void loadProgramIDs() throws Exception {
        List<String> programIDs = programBO.loadAllTherapyProgramIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(programIDs);
        cmbProgramID.setItems(observableList);
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
        setFormEnabled(true);
        PaymentTM selectedItem = tblPayments.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtPaymentID.setText(selectedItem.getPaymentId());
            cmbPatientID.setValue(selectedItem.getPatientId());
            cmbProgramID.setValue(selectedItem.getProgramId());
            cmbSessionID.setValue(selectedItem.getSessionId());
            txtAmount.setText(selectedItem.getAmount().toString());
            txtAmountPaid.setText(selectedItem.getAmountPaid().toString());
            txtAmountToPay.setText(selectedItem.getAmountToPay().toString());
//            txtDate.setValue(LocalDate.ofInstant(selectedItem.getDate().toInstant(), ));
            txtTime.setText(selectedItem.getTime());
            cmbStatus.setValue(selectedItem.getStatus());
        }
    }

    PaymentDTO getTextFieldsValues() {
        String paymentId = txtPaymentID.getText();
        String patientId = cmbPatientID.getValue();
        String programId = cmbProgramID.getValue();
        String sessionId = cmbSessionID.getValue();
        BigDecimal amount = new BigDecimal(txtAmount.getText());
        BigDecimal amountPaid = new BigDecimal(txtAmountPaid.getText());
        BigDecimal amountToPay = new BigDecimal(txtAmountToPay.getText());
        Date date = java.sql.Date.valueOf(LocalDate.now());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String status = cmbStatus.getValue();

        return new PaymentDTO(paymentId, patientId, programId, sessionId, amount, amountPaid, amountToPay, date, time, status);
    }

    boolean validateTextFields() {
        boolean isValidAmount = ValidationUtil.isValidAmount(txtAmount);
        boolean isValidAmountPaid = ValidationUtil.isValidAmount(txtAmountPaid);
        boolean isValidAmountToPay = ValidationUtil.isValidAmount(txtAmountToPay);
        boolean isValidTime = ValidationUtil.isValidTime(txtTime);

        return isValidAmount && isValidAmountPaid && isValidAmountToPay && isValidTime;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbStatus.getItems().addAll("Pending", "Completed");
        cmbTimeAmPm.getItems().addAll("AM", "PM");

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

        setFormEnabled(false);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() throws Exception {
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

    private void refreshPage() throws Exception {
        refreshTable();
        clearFields();
        setFormEnabled(false);
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

    private void setFormEnabled(boolean enabled) {
        cmbPatientID.setDisable(!enabled);
        cmbProgramID.setDisable(!enabled);
        cmbSessionID.setDisable(!enabled);
        txtAmount.setDisable(!enabled);
        txtAmountPaid.setDisable(!enabled);
        txtAmountToPay.setDisable(!enabled);
        txtDate.setDisable(!enabled);
        txtTime.setDisable(!enabled);
        cmbTimeAmPm.setDisable(!enabled);
        cmbStatus.setDisable(!enabled);

        btnSave.setDisable(!enabled);
        btnUpdate.setDisable(!enabled);
        btnDelete.setDisable(!enabled);
        btnViewInvoice.setDisable(!enabled);

        btnAddNewPayment.setDisable(enabled);
    }
}
