package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class LessonPageController {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEnrollmentId;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colLessonId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane lessonRoot;

    @FXML
    private TableView<?> lessonTable;

    @FXML
    private Button updateBtn;

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

}
