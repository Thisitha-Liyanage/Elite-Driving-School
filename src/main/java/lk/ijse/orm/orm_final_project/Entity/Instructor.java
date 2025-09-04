package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    private String ID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String availability;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String specification;

}
