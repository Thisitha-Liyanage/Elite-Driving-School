package lk.ijse.orm.orm_final_project.DTO;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private String ID;
    private String name;
    private LocalDate dOB;
    private LocalDate regDate;
    private String contactNo;

}
