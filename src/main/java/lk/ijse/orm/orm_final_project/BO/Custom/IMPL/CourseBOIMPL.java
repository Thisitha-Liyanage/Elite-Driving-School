package lk.ijse.orm.orm_final_project.BO.Custom.IMPL;

import lk.ijse.orm.orm_final_project.BO.Custom.CourseBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.Config.FactoryConfiguration;
import lk.ijse.orm.orm_final_project.DAO.Custom.CourseDAO;
import lk.ijse.orm.orm_final_project.DAO.DAOFactory;
import lk.ijse.orm.orm_final_project.DAO.DAOTypes;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.Entity.Course;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseBOIMPL implements CourseBO {
    private final CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    @Override
    public boolean delete(String id) throws SQLException {
        return courseDAO.deleteCourse(id);
    }

    @Override
    public CourseDto searchByID(String id) throws SQLException {
        Optional<Course> course = courseDAO.searchByID(id);

        if(course.isEmpty()){
            throw new NotFound("Course ID Not Found");

        }else {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(course, CourseDto.class);
        }
    }

    @Override
    public boolean save(CourseDto courseDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Course course = modelMapper.map(courseDto, Course.class);
        boolean isSaved = courseDAO.save(course);
        System.out.println("isSaved" + isSaved);
        return isSaved ;
    }

    @Override
    public boolean update(CourseDto courseDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Course course = modelMapper.map(courseDto, Course.class);
        boolean isSaved = courseDAO.update(course);
        System.out.println("isSaved" + isSaved);
        return isSaved ;
    }

    @Override
    public List<CourseDto> getAll() throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        List<Course> courses = courseDAO.getAll();
        Type listType = new TypeToken<List<CourseDto>>() {}.getType();
        return modelMapper.map(courses, listType);
    }

    @Override
    public String getNextID() throws SQLException {
        String lastId = courseDAO.getNextID();
        char tableChar = 'C';
        if (lastId != null) {
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableChar + "%03d", nextIdNumber);
        }
        return tableChar + "001";
    }
}
