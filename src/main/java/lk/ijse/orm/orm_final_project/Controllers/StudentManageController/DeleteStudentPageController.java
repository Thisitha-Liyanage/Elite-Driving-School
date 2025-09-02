package lk.ijse.orm.orm_final_project.Controllers.StudentManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.StudentBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteStudentPageController implements Initializable {

    @FXML
    private AnchorPane deleteStudentanc;

    @FXML
    private Button closeBtn;

    @FXML
    private Button deleteBtn;

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

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        deleteStudentanc.getChildren().clear();
        deleteStudentanc.setVisible(false);
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = txtStudentId.getText();
        if(id.isEmpty()){
            new Alert(Alert.AlertType.ERROR , "Enter ID and Press 'Enter Key'").show();
        }

        try {
            if(studentBO.delete(id)){
               new Alert(Alert.AlertType.INFORMATION , "Student Deleted").show();
               resetBtnOnAction(null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void findStudentOnAction(ActionEvent event) {
        String id = txtStudentId.getText();

        if (id.isEmpty()){
            new Alert(Alert.AlertType.ERROR , "Enter ID and Press 'Enter Key'").show();
        }

        try {
            StudentDto studentDto = studentBO.searchByID(id);
            txtName.setText(studentDto.getName());
            txtContact.setText(studentDto.getContactNo());
            dobPicker.setValue(studentDto.getDOB());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch(NotFound n ){
            new Alert(Alert.AlertType.ERROR , n.getMessage()).show();
        }
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtName.clear();
        txtContact.clear();
        txtStudentId.clear();
        dobPicker.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtStudentId.setEditable(true);
    }
}
