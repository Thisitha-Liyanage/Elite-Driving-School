package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Register {
    @Id
    private String reg_id;

    private LocalDate date;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "student_id" , nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id" , nullable = false)
    private Course course;
}
