package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.ReportBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class ReportBOImpl implements ReportBO {

    @Override
    public JasperPrint generateReport(String reportPath, Map<String, Object> parameters) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();

            InputStream reportStream = getClass().getResourceAsStream(reportPath);
            if (reportStream == null) {
                throw new Exception("Report file not found: " + reportPath);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            return JasperFillManager.fillReport(jasperReport, parameters,
                    (Connection) session.doReturningWork(connection -> connection));

        } catch (JRException e) {
            throw new Exception("Failed to generate report: " + e.getMessage(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


//    @Override
//    public JasperPrint generateReport(String reportPath, Map<String, Object> parameters) throws Exception {
//        try {
//            Session session = FactoryConfiguration.getInstance().getSession();
//            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream(reportPath));
//            return JasperFillManager.fillReport(jasperReport, parameters, (Connection) session);
//        } catch (JRException e) {
//            new Alert(Alert.AlertType.ERROR, "Fail to load Report..!").show();
//        }
//        return null;
//    }
}