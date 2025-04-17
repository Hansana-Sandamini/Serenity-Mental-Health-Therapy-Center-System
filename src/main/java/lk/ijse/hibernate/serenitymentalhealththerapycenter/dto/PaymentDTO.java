package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
    private String paymentID;
    private String patientID;
    private String programID;
    private String sessionID;
    private Double amount;
    private Double amountPaid;
    private Double amountToPay;
    private Date date;
    private String time;
    private String status;
}
