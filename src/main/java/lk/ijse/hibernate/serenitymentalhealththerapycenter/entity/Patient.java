package lk.ijse.hibernate.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient implements SuperEntity {
    @Id
    @Column(name = "patient_id")
    private String patientId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String medicalHistory;

    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    @ManyToMany
    @JoinTable(
            name = "patient_therapy_program",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> therapyPrograms;
}