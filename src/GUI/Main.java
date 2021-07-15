package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lernapp_gui_start.fxml")));
        primaryStage.setTitle("FiSi LernApp v1.0");
        primaryStage.getIcons().add(new Image("file:src/GUI/images/lernapp_icon.png"));
        primaryStage.setScene(new Scene(root, 366, 336));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
