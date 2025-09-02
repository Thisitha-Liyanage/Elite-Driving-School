package lk.ijse.orm.orm_final_project.BO.Custom;

import lk.ijse.orm.orm_final_project.BO.CrudBO;
import lk.ijse.orm.orm_final_project.DAO.CrudDAO;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.hibernate.annotations.processing.SQL;

import java.sql.SQLException;

public interface StudentBO extends CrudBO<StudentDto> {
    boolean delete(String id) throws SQLException;
    StudentDto searchByID(String id) throws SQLException;
}
