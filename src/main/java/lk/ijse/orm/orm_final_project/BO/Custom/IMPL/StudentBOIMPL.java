package lk.ijse.orm.orm_final_project.BO.Custom.IMPL;

import lk.ijse.orm.orm_final_project.BO.Custom.StudentBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DAO.Custom.StudentDAO;
import lk.ijse.orm.orm_final_project.DAO.DAOFactory;
import lk.ijse.orm.orm_final_project.DAO.DAOTypes;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentBOIMPL implements StudentBO {
    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
    @Override
    public boolean delete(String id) throws SQLException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean save(StudentDto studentDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDto, Student.class);
        boolean isSaved = studentDAO.save(student);
        System.out.println("isSaved" + isSaved);
        return isSaved ;
    }

    @Override
    public boolean update(StudentDto studentDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDto, Student.class);
        boolean isSaved = studentDAO.update(student);
        System.out.println("isUpdate " + isSaved);
        return isSaved ;
    }

    @Override
    public List<StudentDto> getAll() throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        List<Student> students = studentDAO.getAll();
        Type listType = new TypeToken<List<StudentDto>>() {}.getType();
        return modelMapper.map(students, listType);
    }

    @Override
    public String getNextID() throws SQLException {
        String lastId = studentDAO.getNextID();
        char tableChar = 'S';
        if (lastId != null) {
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableChar + "%03d", nextIdNumber);
        }
        return tableChar + "001";
    }

    @Override
    public StudentDto searchByID(String s) throws SQLException {
        Optional<Student> student = studentDAO.searchByID(s);

        if(student.isEmpty()){
            throw new NotFound("Student ID Not Found");

        }else {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(student, StudentDto.class);
        }
    }
}
