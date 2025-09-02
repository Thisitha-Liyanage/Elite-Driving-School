package lk.ijse.orm.orm_final_project.BO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudBO<T> extends SuperBO{
    boolean save(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    String getNextID() throws SQLException;
}
