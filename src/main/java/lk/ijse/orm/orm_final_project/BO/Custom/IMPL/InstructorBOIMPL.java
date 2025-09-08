package lk.ijse.orm.orm_final_project.BO.Custom.IMPL;

import lk.ijse.orm.orm_final_project.BO.Custom.InstructorBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DAO.Custom.InstructorDAO;
import lk.ijse.orm.orm_final_project.DAO.DAOFactory;
import lk.ijse.orm.orm_final_project.DAO.DAOTypes;
import lk.ijse.orm.orm_final_project.DTO.InstructorDto;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.Entity.Instructor;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorBOIMPL implements InstructorBO {
    private final InstructorDAO instructorDAO = DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTOR);
    @Override
    public boolean delete(String id) throws SQLException {
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDto searchByID(String id) throws SQLException {
        Optional<Instructor> instructor = instructorDAO.searchByID(id);

        if(instructor.isEmpty()){
            throw new NotFound("Instructor ID Not Found");

        }else {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(instructor, InstructorDto.class);
        }
    }

    @Override
    public boolean save(InstructorDto instructorDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Instructor instructor = modelMapper.map(instructorDto, Instructor.class);
        boolean isSaved = instructorDAO.save(instructor);
        System.out.println("isSaved" + isSaved);
        return isSaved ;
    }

    @Override
    public boolean update(InstructorDto instructorDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Instructor instructor = modelMapper.map(instructorDto, Instructor.class);
        boolean isSaved = instructorDAO.update(instructor);
        System.out.println("isSaved" + isSaved);
        return isSaved ;
    }

    @Override
    public List<InstructorDto> getAll() throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        List<Instructor> students = instructorDAO.getAll();
        Type listType = new TypeToken<List<InstructorDto>>() {}.getType();
        return modelMapper.map(students, listType);
    }

    @Override
    public String getNextID() throws SQLException {
        String lastId = instructorDAO.getNextID();
        String tableChar = "IN";
        if (lastId != null) {
            String lastIdNumberString = lastId.substring(2);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableChar + "%03d", nextIdNumber);
        }
        return tableChar + "001";
    }
}
