package lk.ijse.orm.orm_final_project.BO;

import lk.ijse.orm.orm_final_project.BO.Custom.IMPL.CourseBOIMPL;
import lk.ijse.orm.orm_final_project.BO.Custom.IMPL.InstructorBOIMPL;
import lk.ijse.orm.orm_final_project.BO.Custom.IMPL.StudentBOIMPL;
import lk.ijse.orm.orm_final_project.DAO.Custom.IMPL.StudentDAOIMPL;
import lk.ijse.orm.orm_final_project.DAO.DAOFactory;
import lk.ijse.orm.orm_final_project.DAO.DAOTypes;
import lk.ijse.orm.orm_final_project.DAO.SuperDAO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getInstance(){
        return boFactory == null ? (boFactory = new BOFactory() ): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case STUDENT -> (T) new StudentBOIMPL();
            case COURSE -> (T) new CourseBOIMPL();
            case INSTRUCTOR -> (T) new InstructorBOIMPL();
            case ENROLLMENT -> null;
            case COURSE_INSTRUCTOR -> null;
            case LESSON -> null;
            case PAYMENT -> null;
            case REGISTRATION -> null;
        };

    }
}
