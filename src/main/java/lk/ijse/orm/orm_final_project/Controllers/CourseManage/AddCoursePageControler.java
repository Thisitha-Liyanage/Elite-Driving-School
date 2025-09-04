package lk.ijse.orm.orm_final_project.Controllers.CourseManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.CourseBO;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCoursePageControler implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private ComboBox<String> cmbDurationType;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private AnchorPane saveCourseAnc;

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
        saveCourseAnc.getChildren().clear();
        saveCourseAnc.setVisible(false);
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtCourseFee.clear();
        txtCourseName.clear();
        txtDescription.clear();
        txtDurationTime.clear();
        cmbDurationType.setValue("");

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
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
        if(description.length() == 50){
            txtDurationTime.setEditable(false);
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
            if (courseBO.save(courseDto)) {
                new Alert(Alert.AlertType.INFORMATION , "Course Saved").show();
                resetBtnOnAction(null);
                nextID();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbDurationType.getItems().addAll("Week", "Month", "Year");
        txtCourseId.setEditable(false);
        nextID();
    }

    public void nextID(){
        try{
            txtCourseId.setText(courseBO.getNextID());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
