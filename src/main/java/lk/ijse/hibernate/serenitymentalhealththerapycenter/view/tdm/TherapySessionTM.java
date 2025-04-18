package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionTM {
    private String sessionId;
    private String programId;
    private String patientId;
    private String therapistId;
    private Date date;
    private String time;
    private String status;
}
