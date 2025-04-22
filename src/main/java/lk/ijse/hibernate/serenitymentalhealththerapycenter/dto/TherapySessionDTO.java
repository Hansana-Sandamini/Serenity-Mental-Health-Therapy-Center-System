package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionDTO {
    private String sessionId;
    private String programId;
    private String patientId;
    private String therapistId;
    private LocalDate date;
    private String time;
    private String status;
}
