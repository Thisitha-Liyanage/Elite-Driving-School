package lk.ijse.orm.orm_final_project.Controllers.InstructorManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateInstructor implements Initializable {

    @FXML
    private CheckBox chkAvailability;

    @FXML
    private Button closeBtn;

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

    @FXML
    private Button updateBtn;

    @FXML
    private AnchorPane updateInstructorAnc;

    private final InstructorBO instructorBo = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        updateInstructorAnc.getChildren().clear();
        updateInstructorAnc.setVisible(false);
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
    void updateBtnOnAction(ActionEvent event) {
        String mobilePattern = "^(07[1-9][0-9]{7})$";
        String id = txtInstructorId.getText();
        String name = txtName.getText();
        String specific = txtSpecification.getText();
        boolean isAvailable = chkAvailability.isSelected();
        String contact = txtContact.getText();

        if(name.isEmpty() && specific.isEmpty() && contact.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Please Fill All Fields").show();
            return;
        }

        if(!contact.matches(mobilePattern)){
            new Alert(Alert.AlertType.WARNING , "Contact Number Not Valid").show();
            return;
        }

        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setID(id);
        instructorDto.setName(name);
        instructorDto.setContact(contact);
        instructorDto.setSpecification(specific);

        if(isAvailable){
            instructorDto.setAvailability("Available");
        }else {
            instructorDto.setAvailability("Not Available");
        }

        try {
            if(instructorBo.update(instructorDto)){
                new Alert(Alert.AlertType.INFORMATION , "Instructor Updated").show();
                resetBtnOnAction(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchInstructorOnAction(ActionEvent event) {
        String id = txtInstructorId.getText();
        if(id.isEmpty()){
            new Alert(Alert.AlertType.WARNING , "Enter ID and Press 'Enter' key").show();
            return;
        }

        try{
           InstructorDto instructorDto = instructorBo.searchByID(id);

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
        }catch (NotFound n){
            new Alert(Alert.AlertType.ERROR , n.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBtn.setText("Update");
    }
}
