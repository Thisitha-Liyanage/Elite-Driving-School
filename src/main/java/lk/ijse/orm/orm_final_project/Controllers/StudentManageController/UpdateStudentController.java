package lk.ijse.orm.orm_final_project.Controllers.StudentManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.StudentBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateStudentController implements Initializable {

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    @FXML
    private AnchorPane updateStudentAnc;

    @FXML
    private Button closeBtn;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStudentId;

    @FXML
    private Button updateBtn;

    @FXML
    void closeBtnOnAction(ActionEvent event) {
        updateStudentAnc.getChildren().clear();
        updateStudentAnc.setVisible(false);
    }

    @FXML
    void findStudentOnAction(ActionEvent event) {
        String sID = txtStudentId.getText();
        if(sID.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Enter Student ID and Press 'Enter' Key");
            return;
        }

        try {
            StudentDto findStudent = studentBO.searchByID(sID);

            txtContact.setText(findStudent.getContactNo());
            txtName.setText(findStudent.getName());
            dobPicker.setValue(findStudent.getDOB());

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NotFound n){
            new Alert(Alert.AlertType.ERROR , n.getMessage()).show();
        }

    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtStudentId.clear();
        txtName.clear();
        txtContact.clear();
        dobPicker.setValue(null);
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String mobilePattern = "^(07[1-9][0-9]{7})$";
        String name = txtName.getText();
        String id = txtStudentId.getText();
        String contact = txtContact.getText();
        LocalDate date = dobPicker.getValue();

        if(name.isEmpty() && id.isEmpty() && contact.isEmpty()&& date == null){
            new Alert(Alert.AlertType.ERROR , "Please Fill Missing Fields");
            return;
        }

        if (!contact.matches(mobilePattern)){
            new Alert(Alert.AlertType.WARNING , "Please Enter Valid Phone Number");
            return;
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setID(id);
        studentDto.setName(name);
        studentDto.setDOB(date);
        studentDto.setContactNo(contact);

        try {
            if(studentBO.update(studentDto)){
                new Alert(Alert.AlertType.INFORMATION , "Student Updated").show();
                resetBtnOnAction(null);
            }else{
                new Alert(Alert.AlertType.ERROR , "Student Not Updated").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dobPicker.getEditor().setDisable(true);
        dobPicker.getEditor().setOpacity(1);
    }


}
