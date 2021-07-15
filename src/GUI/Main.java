package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    /*@FXML
    ControllerStartseite controllerStartseite;
    @FXML
    public void intitialize(){
        controllerStartseite.injectMainController();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        primaryStage.setTitle("FiSi LernApp v1.0");
        primaryStage.setScene(new Scene(root, 366, 336));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
