package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StudentPageController {

    @FXML
    private AnchorPane ManagePageLoader;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane studentRoot;

    @FXML
    private TableView<?> studentTable;

    @FXML
    private Button updateBtn;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/SaveStudentPage.fxml");
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/DeletestudentPage.fxml");
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/StudentManage/UpdateStudentPage.fxml");
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

}
