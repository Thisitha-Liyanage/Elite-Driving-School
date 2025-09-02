package lk.ijse.orm.orm_final_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private Button PaymentBtn;

    @FXML
    private Button backbtn;

    @FXML
    private Button courseBtn;

    @FXML
    private Button courseInstructrbtn;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Button enrollmentBtn;

    @FXML
    private Button instructorBtn;

    @FXML
    private Button lessonBtn;

    @FXML
    private AnchorPane mainContentPane;

    @FXML
    private Button registrationbtn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button studentBtn;

    @FXML
    private Button usebtn;

    @FXML
    private Label userLabel;

    @FXML
    void PaymentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void backbtnOnAction(ActionEvent event) {

    }

    @FXML
    void courseBtnOnAction(ActionEvent event) {

    }

    @FXML
    void courseInstructrbtnOnAction(ActionEvent event) {

    }

    @FXML
    void enrollmentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void instructorBtnOnActon(ActionEvent event) {

    }

    @FXML
    void lessonBtnOnActon(ActionEvent event) {

    }

    @FXML
    void registrationbtnOnAction(ActionEvent event) {

    }

    @FXML
    void studentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void usebtnOnActon(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/VIew/StudentPage.fxml");
    }

    public void navigateTo(String path){
        try{
            mainContentPane.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            mainContentPane.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
