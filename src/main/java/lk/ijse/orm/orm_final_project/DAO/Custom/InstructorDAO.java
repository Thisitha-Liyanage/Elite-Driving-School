package lk.ijse.orm.orm_final_project.DAO.Custom;

import lk.ijse.orm.orm_final_project.DAO.CrudDAO;
import lk.ijse.orm.orm_final_project.Entity.Instructor;

import java.sql.SQLException;

public interface InstructorDAO extends CrudDAO<Instructor> {
    boolean delete(String ID) throws SQLException;
}
