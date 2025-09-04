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
import lk.ijse.orm.orm_final_project.BO.Custom.StudentBO;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.DTO.TM.StudentTM;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.modelmapper.internal.bytebuddy.matcher.CollectionOneToOneMatcher;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {

    @FXML
    private AnchorPane ManagePageLoader;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<StudentTM, String> colContact;

    @FXML
    private TableColumn<StudentTM, LocalDate> colDob;

    @FXML
    private TableColumn<StudentTM, String > colName;

    @FXML
    private TableColumn<StudentTM, LocalDate> colRegDate;

    @FXML
    private TableColumn<StudentTM, String > colStudentId;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane studentRoot;

    @FXML
    private TableView<StudentTM> studentTable;

    @FXML
    private Button updateBtn;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    @FXML
    void addBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/SaveStudentPage.fxml");
        loadTable();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/DeletestudentPage.fxml");
        loadTable();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/UpdateStudentPage.fxml");
        loadTable();
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

    public void loadTable(){
        try {
            List<StudentDto> studentDtos= studentBO.getAll();

            ObservableList<StudentTM> list = FXCollections.observableArrayList();
            for (StudentDto studentDto : studentDtos){
                StudentTM studentTM = new StudentTM(
                        studentDto.getID(),
                        studentDto.getName(),
                        studentDto.getDOB(),
                        studentDto.getRegDate(),
                        studentDto.getContactNo()
                );
                list.add(studentTM);
            }
            studentTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStudentId     .setCellValueFactory(new PropertyValueFactory<>("ID"));
        colContact   .setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dOB"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));

        loadTable();
    }
}
