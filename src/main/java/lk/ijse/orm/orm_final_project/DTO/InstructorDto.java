package lk.ijse.orm.orm_final_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorDto {
    private String ID;
    private String name;
    private String availability;
    private String contact;
    private String specification;
}
