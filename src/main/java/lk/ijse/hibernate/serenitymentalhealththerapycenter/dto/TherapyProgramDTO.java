package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramDTO {
    private String programID;
    private String programName;
    private String duration;
    private double fee;
}
