package lk.ijse.orm.orm_final_project.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm.orm_final_project.BO.BOFactory;
import lk.ijse.orm.orm_final_project.BO.BOTypes;
import lk.ijse.orm.orm_final_project.BO.Custom.InstructorBO;
import lk.ijse.orm.orm_final_project.DTO.InstructorDto;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.DTO.TM.InstructorTM;
import lk.ijse.orm.orm_final_project.DTO.TM.StudentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorPageController implements Initializable {

    @FXML
    private AnchorPane ManagePageLoader;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<InstructorTM, String > colAvailability;

    @FXML
    private TableColumn<InstructorTM, String > colContact;

    @FXML
    private TableColumn<InstructorTM, String > colInstructorId;

    @FXML
    private TableColumn<InstructorTM, String> colName;

    @FXML
    private TableColumn<InstructorTM, String > colSpecification;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane instructorRoot;

    @FXML
    private TableView<InstructorTM> instructorTable;

    @FXML
    private Button updateBtn;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    @FXML
    void addBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/ManageInstructor/SaveInstructorPage.fxml");
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/ManageInstructor/DeleteInstructorPage.fxml");
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/ManageInstructor/UpdateInstructorPage.fxml");
    }

    public void manageLoader(String path){
        try{
            ManagePageLoader.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInstructorId     .setCellValueFactory(new PropertyValueFactory<>("ID"));
        colContact   .setCellValueFactory(new PropertyValueFactory<>("contact"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colSpecification.setCellValueFactory(new PropertyValueFactory<>("specification"));

        loadTable();
    }

    public void loadTable(){
        try {
            List<InstructorDto> instructorDtos= instructorBO .getAll();

            ObservableList<InstructorTM> list = FXCollections.observableArrayList();
            for (InstructorDto instructorDto : instructorDtos){
                InstructorTM instructorTM = new InstructorTM(
                        instructorDto.getID(),
                        instructorDto.getName(),
                        instructorDto.getAvailability(),
                        instructorDto.getContact(),
                        instructorDto.getSpecification()
                );
                list.add(instructorTM);
            }
            instructorTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
