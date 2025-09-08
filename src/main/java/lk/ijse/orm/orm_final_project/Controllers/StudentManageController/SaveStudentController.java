package lk.ijse.orm.orm_final_project.Controllers.StudentManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.StudentBO;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SaveStudentController implements Initializable {

    @FXML
    private AnchorPane saveStudentAnc;

    @FXML
    private Button cancelBtn;

    @FXML
    private DatePicker dobPicker;
    @FXML
    private Button saveBtn;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStudentId;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String mobilePattern = "^(07[1-9][0-9]{7})$";
        String studentID = txtStudentId.getText();
        LocalDate dOB = dobPicker.getValue();
        String contact = txtContact.getText();
        String name = txtName.getText();

        if (contact.isEmpty() && name.isEmpty() && dOB == null){
            new Alert(Alert.AlertType.ERROR , "Please Fill All Missing Fields").show();
            return;
        }

        if(!contact.matches(mobilePattern)){
            new Alert(Alert.AlertType.ERROR , "Please Enter Vali Phone Number").show();
            return;
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setID(studentID);
        studentDto.setName(name);
        studentDto.setDOB(dOB);
        studentDto.setContactNo(contact);
        studentDto.setRegDate(LocalDate.now());


        try {
            if (studentBO.save(studentDto)) {
                new Alert(Alert.AlertType.INFORMATION , "Student Saved").show();
                getNextID();
                resetBtnOnAction(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void resetBtnOnAction(ActionEvent event) {
        txtName.clear();
        txtContact.clear();
        dobPicker.setValue(null);
    }

    public void closeBtnOnAction(ActionEvent event) {
        saveStudentAnc.getChildren().clear();
        saveStudentAnc.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getNextID();
        txtStudentId.setEditable(false);
        dobPicker.getEditor().setDisable(true);
        dobPicker.getEditor().setOpacity(1);
    }

    public void getNextID(){
        try {
            txtStudentId.setText(studentBO.getNextID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
