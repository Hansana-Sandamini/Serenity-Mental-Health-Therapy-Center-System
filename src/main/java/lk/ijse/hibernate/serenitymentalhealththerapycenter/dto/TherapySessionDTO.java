package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionDTO {
    private String sessionID;
    private String programID;
    private String patientID;
    private String therapistID;
    private Date date;
    private String time;
    private String status;
}
