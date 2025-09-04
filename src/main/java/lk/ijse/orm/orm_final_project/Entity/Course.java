package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    private String ID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String durationType;

    @Column(nullable = false)
    private int duration_time;

    @Column(nullable = false , precision = 10 , scale = 2)
    private BigDecimal fee;

    @Column(nullable = false)
    private String description;


}
