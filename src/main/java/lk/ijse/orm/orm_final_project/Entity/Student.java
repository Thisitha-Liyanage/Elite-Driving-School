package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String ID;

    @Column(nullable = false)
    private String name;

    @Column(name = "d_O_B" , nullable = false)
    private LocalDate dOB;

    @Column(name = "reg_Date", nullable = false)
    private LocalDate regDate;

    @Column(name = "contact_No", unique = true , nullable = false)
    private String contactNo;


}
