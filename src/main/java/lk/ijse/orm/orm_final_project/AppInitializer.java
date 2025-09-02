package lk.ijse.orm.orm_final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.orm.orm_final_project.Config.FactoryConfiguration;
import org.hibernate.Session;


public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/VIew/DashBoard.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
