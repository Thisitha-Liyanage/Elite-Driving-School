package lk.ijse.orm.orm_final_project.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseTM {
    private String id;
    private String name;
    private String durationType;
    private int duration_time;
    private BigDecimal fee;
    private String description;
}
