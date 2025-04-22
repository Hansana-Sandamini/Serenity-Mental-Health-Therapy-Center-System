package lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientTM {
    private String patientId;
    private String name;
    private String contactNumber;
    private String email;
    private int age;
    private LocalDate registrationDate;
}
