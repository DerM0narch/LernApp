package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import klassen.Datenbank;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
/**
 * The type Main.
 */
public class Main extends Application {

    /*@FXML
    ControllerStartseite controllerStartseite;
    @FXML
    public void intitialize(){
        controllerStartseite.injectMainController();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lernapp_gui_start.fxml")));
        primaryStage.setTitle("FiSi LernApp v1.0");
        primaryStage.getIcons().add(new Image("file:src/GUI/images/lernapp_icon.png"));
        primaryStage.setScene(new Scene(root, 366, 336));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
