package lk.ijse.orm.orm_final_project.DAO;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    String getNextID() throws SQLException;
    Optional<T> searchByID(String s) throws SQLException;
}
