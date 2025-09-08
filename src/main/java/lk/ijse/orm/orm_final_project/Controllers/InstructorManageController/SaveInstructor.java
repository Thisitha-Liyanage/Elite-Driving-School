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
import lk.ijse.orm.orm_final_project.BO.Custom.IMPL.InstructorBOIMPL;
import lk.ijse.orm.orm_final_project.DTO.InstructorDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SaveInstructor implements Initializable {

    @FXML
    private CheckBox chkAvailability;

    @FXML
    private Button closeBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private AnchorPane saveInstructorAnc;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtSpecification;

    @FXML
    private TextField txtName;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    @FXML
    void closeBtnOnAction(ActionEvent event) {
        saveInstructorAnc.getChildren().clear();
        saveInstructorAnc.setVisible(false);
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        txtContact.clear();
        txtName.clear();
        chkAvailability.setSelected(false);
        txtSpecification.clear();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
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
            new Alert(Alert.AlertType.WARNING , "Contact Number Not Valid");
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
            if (instructorBO.save(instructorDto)) {
                new Alert(Alert.AlertType.INFORMATION , "Instructor Saved").show();
                resetBtnOnAction(null);
                nextID();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void nextID(){
        try {
            txtInstructorId.setText(instructorBO.getNextID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextID();
    }
}
