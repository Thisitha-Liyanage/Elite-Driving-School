package lk.ijse.orm.orm_final_project.DAO.Custom;

import lk.ijse.orm.orm_final_project.DAO.CrudDAO;
import lk.ijse.orm.orm_final_project.DAO.SuperDAO;
import lk.ijse.orm.orm_final_project.Entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {
    boolean delete(String id) throws SQLException;

}
