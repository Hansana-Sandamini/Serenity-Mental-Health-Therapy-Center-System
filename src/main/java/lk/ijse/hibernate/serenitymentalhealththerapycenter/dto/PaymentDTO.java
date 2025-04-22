package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Payment;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
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
