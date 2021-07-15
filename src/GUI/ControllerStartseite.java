package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ControllerStartseite {

    @FXML
    private Button buttonzufaelligeFragen;

    @FXML
    public void intitialize(){}

    public void clickButton(){
        System.out.println("Knopf gedürckt");
    }

    public void SWZufaelligeFragen(ActionEvent event) throws IOException {

        Parent fragenView = FXMLLoader.load(getClass().getResource("lernapp_gui_fragen.fxml"));
        Scene fragenViewScene = new Scene(fragenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(fragenViewScene);
        window.show();
    }

}
