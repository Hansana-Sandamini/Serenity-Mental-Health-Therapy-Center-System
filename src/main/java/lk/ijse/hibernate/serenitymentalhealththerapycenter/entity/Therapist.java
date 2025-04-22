package lk.ijse.hibernate.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {
    @Id
    @Column(name = "therapist_id")
    private String therapistId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String availabilityStatus;

    @Column(nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "therapist")
    private List<TherapyProgram> therapyPrograms;

    public Therapist(String therapistId, String name, String contactNumber, String email, String availabilityStatus, String specialization) {
        this.therapistId = therapistId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.availabilityStatus = availabilityStatus;
        this.specialization = specialization;
    }
}