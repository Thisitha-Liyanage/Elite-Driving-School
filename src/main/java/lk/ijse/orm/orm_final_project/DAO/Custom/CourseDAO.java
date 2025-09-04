package lk.ijse.orm.orm_final_project.DAO.Custom;

import lk.ijse.orm.orm_final_project.DAO.CrudDAO;
import lk.ijse.orm.orm_final_project.Entity.Course;

import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course> {
    boolean deleteCourse(String ID) throws SQLException;
}
