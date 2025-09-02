package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class RegistrationPageController {

    @FXML
    private AnchorPane ManagePageLoader;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private AnchorPane registrationRoot;

    @FXML
    private TableView<?> registrationTable;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

}
