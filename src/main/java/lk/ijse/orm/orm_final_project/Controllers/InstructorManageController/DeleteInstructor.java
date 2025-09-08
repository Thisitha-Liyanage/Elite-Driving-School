package lk.ijse.orm.orm_final_project.Controllers.InstructorManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.InstructorBO;
import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.DTO.InstructorDto;

import java.sql.SQLException;

public class DeleteInstructor {

    @FXML
    private CheckBox chkAvailability;

    @FXML
    private Button closeBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane deleteInstructorAnc;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSpecification;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        deleteInstructorAnc.getChildren().clear();
        deleteInstructorAnc.setVisible(false);
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String id = txtInstructorId.getText();
        if(id.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Enter ID and Press 'Enter' key").show();
            return;
        }

        try{
            if (instructorBO.delete(id)){
                new Alert(Alert.AlertType.INFORMATION , "Instructor Deleted").show();
                resetBtnOnAction(null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtInstructorId.clear();
        txtContact.clear();
        txtName.clear();
        chkAvailability.setSelected(false);
        txtSpecification.clear();
    }

    @FXML
    void searchInstructorOnAction(ActionEvent event) {
        String id = txtInstructorId.getText();
        if(id.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Enter ID and Press 'Enter' key").show();
            return;
        }

        try{
            InstructorDto instructorDto = instructorBO.searchByID(id);

            txtContact.setText(instructorDto.getContact());
            txtSpecification.setText(instructorDto.getSpecification());
            txtName.setText(instructorDto.getName());

            if(instructorDto.getAvailability().equals("Available")){
                chkAvailability.setSelected(true);
            }else{
                chkAvailability.setSelected(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NotFound n) {
            new Alert(Alert.AlertType.ERROR, n.getMessage()).show();
        }
    }

}
