package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramDTO {
    private String programId;
    private String programName;
    private String duration;
    private BigDecimal fee;
}
