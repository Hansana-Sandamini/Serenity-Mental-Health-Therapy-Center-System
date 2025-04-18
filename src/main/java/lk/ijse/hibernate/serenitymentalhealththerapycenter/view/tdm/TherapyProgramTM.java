package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramTM {
    private String programId;
    private String programName;
    private String duration;
    private BigDecimal fee;
}
