package lernAppGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import lernApp.Datenbank;

import java.io.IOException;

public class ControllerStartseite {
    private final Datenbank db = new Datenbank();

    @FXML
    public void intitialize(){}

// Button: Zufällige Fragen
    public void SWZufaelligeFragen(ActionEvent event) throws IOException {

        Parent fragenView = FXMLLoader.load(getClass().getResource("lernapp_gui_fragen.fxml"));
        Scene fragenViewScene = new Scene(fragenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(fragenViewScene);
        window.show();
    }


    // Button: Hilfe
    @FXML
    public void HilfeButton(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_hilfe.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();
    }


// Button: Schwerpunkte
    @FXML
    public void ButtonSchwerpunkte(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_schwerpunkte.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();

    }

    // Button: Fragen hinzufügen
    @FXML
    public void buttonFragehinzufuegen(ActionEvent event) throws IOException {

        Parent hinzufuegenView = FXMLLoader.load(getClass().getResource("lernapp_gui_neue_fragen.fxml"));
        Scene hinzufuegenViewScene = new Scene(hinzufuegenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hinzufuegenViewScene);
        window.show();
    }

    @FXML
    public void SWMarkierteFragen(ActionEvent event) throws IOException{
        Parent hinzufuegenView = FXMLLoader.load(getClass().getResource("lernapp_gui_markierteFragen.fxml"));
        Scene hinzufuegenViewScene = new Scene(hinzufuegenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hinzufuegenViewScene);
        window.show();
    }

}
