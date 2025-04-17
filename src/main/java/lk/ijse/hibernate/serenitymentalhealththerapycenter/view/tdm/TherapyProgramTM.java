package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramTM {
    private String programID;
    private String programName;
    private String duration;
    private double fee;
}
