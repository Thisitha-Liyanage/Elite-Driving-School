package lk.ijse.orm.orm_final_project.Controllers.CourseManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.CourseBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;

import java.sql.SQLException;

public class DeleteCoursePageController {

    @FXML
    private Button closeBtn;

    @FXML
    private ComboBox<String > cmbDurationType;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane deleteCourseAnc;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDurationTime;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        deleteCourseAnc.getChildren().clear();
        deleteCourseAnc.setVisible(false);
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = txtCourseId.getText();

        if(id.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Enter ID and Press 'Enter' key ").show();
            return;
        }

        try{
            if(courseBO.delete(id)){
                new Alert(Alert.AlertType.ERROR , "Course Deleted").show();
                resetBtnOnAction(null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void findCourseOnAction(ActionEvent event) {
        String id = txtCourseId.getText();

        if(id.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Enter ID and Press 'Enter' key ").show();
            return;
        }

        try{
            CourseDto courseDto = courseBO.searchByID(id);
            txtCourseName.setText(courseDto.getName());
            txtCourseFee.setText(String.valueOf(courseDto.getFee()));
            txtDescription.setText(courseDto.getDescription());
            txtDurationTime.setText(String.valueOf(courseDto.getDuration_time()));
            cmbDurationType.setValue(courseDto.getDurationType());

        }catch (SQLException e){
            e.printStackTrace();
        }catch(NotFound n) {
            new Alert(Alert.AlertType.ERROR , n.getMessage()).show();
        }
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtCourseFee.clear();
        txtCourseName.clear();
        txtDescription.clear();
        txtDurationTime.clear();
        cmbDurationType.setValue("");
        txtCourseId.clear();
    }

}
