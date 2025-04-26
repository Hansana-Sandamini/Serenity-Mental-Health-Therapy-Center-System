package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import net.sf.jasperreports.engine.JasperPrint;
import java.util.Map;

public interface ReportBO extends SuperBO {
    JasperPrint generateReport(String reportPath, Map<String, Object> parameters) throws Exception;
}
