package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM {
    private String paymentId;
    private String patientId;
    private String programId;
    private String sessionId;
    private BigDecimal amount;
    private BigDecimal amountPaid;
    private BigDecimal amountToPay;
    private Date date;
    private String time;
    private String status;
}
