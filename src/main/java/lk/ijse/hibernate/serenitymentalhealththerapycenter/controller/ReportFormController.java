package lk.ijse.hibernate.serenitymentalhealththerapycenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.BOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.ReportBO;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReportFormController {

    @FXML
    private Button btnDurationReportGenerate;

    @FXML
    private Button btnMonthOverviewGenerate;

    @FXML
    private Button btnMonthReportGenerate;

    @FXML
    private Button btnYearOverviewGenerate;

    @FXML
    private Button btnYearReportGenerate;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private AnchorPane therapistPane;

    @FXML
    private DatePicker txtFrom;

    @FXML
    private DatePicker txtTo;

    ReportBO reportBO = (ReportBO) BOFactory.getInstance().getBO(BOFactory.BOType.REPORT);

    private void generateReport(String reportPath) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_Date", LocalDate.now().toString());
            if (txtFrom.getValue() != null) parameters.put("p_txtFrom", txtFrom.getValue().toString());
            if (txtTo.getValue() != null) parameters.put("p_txtTo", txtTo.getValue().toString());

            JasperPrint jasperPrint = reportBO.generateReport(reportPath, parameters);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate report!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnDurationReportGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnMonthOverviewGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnMonthReportGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnYearOverviewGenerateOnAction(ActionEvent event) {

    }

    @FXML
    void btnYearReportGenerateOnAction(ActionEvent event) {
        generateReport("/reports/YearReport.jrxml");
    }

    @FXML
    void homeIconOnAction(MouseEvent event) {

    }

}
