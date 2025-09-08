module lk.ijse.orm.orm_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires modelmapper;

    opens lk.ijse.orm.orm_final_project.Entity;
    opens lk.ijse.orm.orm_final_project.DTO;
    opens lk.ijse.orm.orm_final_project.DTO.TM;
    opens lk.ijse.orm.orm_final_project.Controllers to javafx.fxml;
    opens lk.ijse.orm.orm_final_project.BO;
    opens lk.ijse.orm.orm_final_project.BO.Custom;
    opens lk.ijse.orm.orm_final_project.BO.Custom.IMPL;
    opens lk.ijse.orm.orm_final_project.DAO;
    opens lk.ijse.orm.orm_final_project.DAO.Custom;
    opens lk.ijse.orm.orm_final_project.DAO.Custom.IMPL to org.hibernate.orm.core, jakarta.persistence;
    opens lk.ijse.orm.orm_final_project.Config to org.hibernate.orm.core, jakarta.persistence;
    opens lk.ijse.orm.orm_final_project.Controllers.StudentManageController to javafx.fxml;
    opens lk.ijse.orm.orm_final_project.Controllers.CourseManage to javafx.fxml;
    opens lk.ijse.orm.orm_final_project.Controllers.InstructorManageController to javafx.fxml;
    exports lk.ijse.orm.orm_final_project;
}