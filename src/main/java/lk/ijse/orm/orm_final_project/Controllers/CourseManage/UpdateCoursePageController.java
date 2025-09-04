package lk.ijse.orm.orm_final_project.Controllers.CourseManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.CourseBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DAO.Custom.CourseDAO;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCoursePageController implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private ComboBox<String> cmbDurationType;

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

    @FXML
    private Button updateBtn;

    @FXML
    private AnchorPane updateCourseAnc;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        updateCourseAnc.getChildren().clear();
        updateCourseAnc.setVisible(false);
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

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String id = txtCourseId.getText();
        String name = txtCourseName.getText();
        String description = txtDescription.getText();
        String durationType = cmbDurationType.getValue();
        String durationTime = txtDurationTime.getText();
        String fee = txtCourseFee.getText();


        if(name.isEmpty()&&description.isEmpty()&&durationTime.isEmpty()&&durationType.isEmpty()&&fee.isEmpty()){
            new Alert(Alert.AlertType.ERROR , "Please Fill All Missing Fields").show();
            return;
        }
        int dueTimeInt = 0;
        double courseFee = 0;
        try{
            dueTimeInt = Integer.parseInt(durationTime);
            courseFee = Double.parseDouble(fee);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR , "Duration TIme and Course Fee Values Must Be numbers").show();
        }

        BigDecimal courseFeeB = BigDecimal.valueOf(courseFee);

        CourseDto courseDto = new CourseDto();
        courseDto.setId(id);
        courseDto.setName(name);
        courseDto.setDescription(description);
        courseDto.setDurationType(durationType);
        courseDto.setDuration_time(dueTimeInt);
        courseDto.setFee(courseFeeB);


        try{
            if(courseBO.update(courseDto)){
                new Alert(Alert.AlertType.INFORMATION , "Course Updated").show();
                resetBtnOnAction(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void FindCourseOnAction(ActionEvent event) {
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
            new Alert(Alert.AlertType.ERROR , n.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDurationType.getItems().addAll("Week", "Month", "Year");
        txtCourseId.setEditable(false);
        txtCourseId.setEditable(true);
    }
}
