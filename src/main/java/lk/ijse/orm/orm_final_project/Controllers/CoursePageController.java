package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CoursePageController {

    @FXML
    private Button addBtn;


    @FXML
    private AnchorPane ancPageLoader;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colDurationType;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private AnchorPane courseRoot;

    @FXML
    private TableView<?> courseTable;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    @FXML
    void addBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/CourseManage/saveCoursePage.fxml");
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

    public void manageLoader(String path){
        try{
            ancPageLoader.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancPageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
