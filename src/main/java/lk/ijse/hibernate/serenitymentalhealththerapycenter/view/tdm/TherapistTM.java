package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistTM {
    private String therapistID;
    private String name;
    private String contactNumber;
    private String email;
    private String availabilityStatus;
    private String specialization;
}
