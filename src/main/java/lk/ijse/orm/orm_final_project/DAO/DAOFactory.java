package lk.ijse.orm.orm_final_project.DAO;

import lk.ijse.orm.orm_final_project.DAO.Custom.IMPL.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DAOFactory() ): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        return switch (daoTypes) {
            case STUDENT -> (T) new StudentDAOIMPL();
            case COURSE -> (T) new CourseDAOIMPL();
            case INSTRUCTOR -> (T) new InstructorDAOIMPL();
            case ENROLLMENT -> null;
            case COURSE_INSTRUCTOR -> null;
            case LESSON -> null;
            case PAYMENT -> null;
            case REGISTRATION -> null;
        };

    }

}
