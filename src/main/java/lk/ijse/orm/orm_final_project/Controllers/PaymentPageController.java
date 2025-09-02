package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PaymentPageController {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEnrollmentId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane paymentRoot;

    @FXML
    private TableView<?> paymentTable;

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
