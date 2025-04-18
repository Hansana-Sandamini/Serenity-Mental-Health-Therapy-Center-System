package lk.ijse.hibernate.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "therapy_program")
public class TherapyProgram implements SuperEntity {
    @Id
    @Column(name = "program_id")
    private String programId;

    @Column(nullable = false)
    private String programName;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fee;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToMany(mappedBy = "therapyProgram")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "therapyProgram")
    private List<Payment> payments;

    @ManyToMany(mappedBy = "therapyPrograms")
    private List<Patient> patients;
}
