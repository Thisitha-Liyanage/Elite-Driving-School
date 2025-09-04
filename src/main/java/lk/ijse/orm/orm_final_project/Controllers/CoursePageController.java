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
import lk.ijse.orm.orm_final_project.BO.Custom.CourseBO;
import lk.ijse.orm.orm_final_project.DAO.Custom.CourseDAO;
import lk.ijse.orm.orm_final_project.DTO.CourseDto;
import lk.ijse.orm.orm_final_project.DTO.StudentDto;
import lk.ijse.orm.orm_final_project.DTO.TM.CourseTM;
import lk.ijse.orm.orm_final_project.DTO.TM.StudentTM;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {

    @FXML
    private Button addBtn;


    @FXML
    private AnchorPane ancPageLoader;

    @FXML
    private TableColumn<CourseTM ,String > colCourseId;

    @FXML
    private TableColumn<CourseTM,String> colCourseName;

    @FXML
    private TableColumn<CourseTM, String > colDescription;

    @FXML
    private TableColumn<CourseTM, Integer > colDuration;

    @FXML
    private TableColumn<CourseTM, String > colDurationType;

    @FXML
    private TableColumn<CourseTM, BigDecimal> colFee;

    @FXML
    private AnchorPane courseRoot;

    @FXML
    private TableView<CourseTM> courseTable;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    @FXML
    void addBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/CourseManage/saveCoursePage.fxml");
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/CourseManage/DeleteCoursePage.fxml");
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        manageLoader("/VIew/CourseManage/UpdateCoursePage.fxml");
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

    public void loadTable(){
        try {
            List<CourseDto> courseDtos= courseBO.getAll();

            ObservableList<CourseTM> list = FXCollections.observableArrayList();
            for (CourseDto courseDto : courseDtos){
                CourseTM courseTM = new CourseTM(
                        courseDto.getId(),
                        courseDto.getName(),
                        courseDto.getDurationType(),
                        courseDto.getDuration_time(),
                        courseDto.getFee(),
                        courseDto.getDescription()
                );
                list.add(courseTM);
            }
            courseTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCourseId     .setCellValueFactory(new PropertyValueFactory<>("id"));
        colCourseName   .setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration_time"));
        colDurationType.setCellValueFactory(new PropertyValueFactory<>("durationType"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTable();
    }
}
