package lk.ijse.hibernate.serenitymentalhealththerapycenter.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDTO {
    private String patientId;
    private String name;
    private String contactNumber;
    private String email;
    private int age;
    private LocalDate registrationDate;
}
