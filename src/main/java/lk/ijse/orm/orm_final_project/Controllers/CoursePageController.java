package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CoursePageController {

    @FXML
    private Button addBtn;

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

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

}
