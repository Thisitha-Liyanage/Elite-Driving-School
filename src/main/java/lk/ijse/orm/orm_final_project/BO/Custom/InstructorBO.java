package lk.ijse.orm.orm_final_project.BO.Custom;

import lk.ijse.orm.orm_final_project.BO.CrudBO;
import lk.ijse.orm.orm_final_project.DTO.InstructorDto;


import java.sql.SQLException;

public interface InstructorBO extends CrudBO<InstructorDto> {
    boolean delete(String id) throws SQLException;
    InstructorDto searchByID(String id) throws SQLException;
}
