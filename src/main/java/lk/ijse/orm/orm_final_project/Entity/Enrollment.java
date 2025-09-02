package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "student_Id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_Id")
    private Course course;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}
