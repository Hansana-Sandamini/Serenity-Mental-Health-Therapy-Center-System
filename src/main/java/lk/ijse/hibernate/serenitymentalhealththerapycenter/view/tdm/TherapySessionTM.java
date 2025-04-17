package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionTM {
    private String sessionID;
    private String programID;
    private String patientID;
    private String therapistID;
    private Date date;
    private String time;
    private String status;
}
