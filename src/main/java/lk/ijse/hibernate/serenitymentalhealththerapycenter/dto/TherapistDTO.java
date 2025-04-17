package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistDTO {
    private String therapistID;
    private String name;
    private String contactNumber;
    private String email;
    private String availabilityStatus;
    private String specialization;
}
