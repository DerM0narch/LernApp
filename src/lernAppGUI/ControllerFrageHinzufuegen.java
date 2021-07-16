package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerFrageHinzufuegen {

    @FXML
    private Button buttonzufaelligeFragen;

    @FXML
    public void intitialize(){}

    @FXML
    public void zurueck(ActionEvent event) throws IOException {

        Parent startseite = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        Scene startseiteScene = new Scene(startseite);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(startseiteScene);
        window.show();
    }

}