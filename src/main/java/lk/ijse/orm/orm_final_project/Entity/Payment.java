package lk.ijse.orm.orm_final_project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@Table(name = "payment")
public class Payment {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Column(nullable = false)
    private double amountPaid;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(name = "payment_methods", nullable = false)
    private String paymentMethod;
}


