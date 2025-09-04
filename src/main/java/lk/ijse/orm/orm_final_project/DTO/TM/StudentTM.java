package lk.ijse.orm.orm_final_project.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentTM {
    private String ID;
    private String name;
    private LocalDate dOB;
    private LocalDate regDate;
    private String contactNo;
}
