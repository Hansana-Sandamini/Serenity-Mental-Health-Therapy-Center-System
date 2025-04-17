package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientTM {
    private String patientID;
    private String name;
    private String contactNumber;
    private String email;
    private int age;
    private Date registrationDate;
}
