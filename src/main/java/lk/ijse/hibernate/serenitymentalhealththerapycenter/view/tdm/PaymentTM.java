package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM {
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
