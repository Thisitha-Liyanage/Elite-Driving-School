package lk.ijse.orm.orm_final_project.BO.Custom;

import lk.ijse.orm.orm_final_project.BO.CrudBO;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;

import java.sql.SQLException;

public interface CourseBO extends CrudBO<CourseDto> {
    boolean delete(String id) throws SQLException;
    CourseDto searchByID(String id) throws SQLException;
}
