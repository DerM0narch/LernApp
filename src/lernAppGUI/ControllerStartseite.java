package lernAppGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import lernApp.Datenbank;

import java.io.IOException;
import java.util.Objects;

public class ControllerStartseite {
    private final Datenbank db = new Datenbank();

    @FXML
    private Button buttonFalscheFragen, buttonMarkierteFragen;

    /**
     * initialisiert funktion von Knöpfen
     */
    @FXML
    public void initialize(){

        if (db.Countfalsche() == 0) {
            buttonFalscheFragen.setDisable(true);
        }
        if (db.countMarkiert() == 0) {
            buttonMarkierteFragen.setDisable(true);
        }


    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button: Zufällige Fragen
    public void SWZufaelligeFragen(ActionEvent event) throws IOException {

        Parent fragenView = FXMLLoader.load(getClass().getResource("lernapp_gui_fragen.fxml"));
        Scene fragenViewScene = new Scene(fragenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(fragenViewScene);
        window.show();
    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button: Hilfe
    @FXML
    public void SWHilfe(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_hilfe.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();
    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button: Schwerpunkte
    @FXML
    public void SWSchwerpunkte(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_schwerpunkte.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();

    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button: Fragen hinzufügen
    @FXML
    public void SWFrageHinzufuegen(ActionEvent event) throws IOException {

        Parent hinzufuegenView = FXMLLoader.load(getClass().getResource("lernapp_gui_neue_fragen.fxml"));
        Scene hinzufuegenViewScene = new Scene(hinzufuegenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hinzufuegenViewScene);
        window.show();
    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button Markierte Fragen
    @FXML
    public void SWMarkierteFragen(ActionEvent event) throws IOException{

        Parent markierteFragenView = FXMLLoader.load(getClass().getResource("lernapp_gui_markierteFragen.fxml"));
        Scene markierteFragenViewScene = new Scene(markierteFragenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(markierteFragenViewScene);
        window.show();
    }

    /**
     * wechselt view
     * @param event Knopfdruck
     * @throws IOException
     */
    // Button Falsche Fragen
    @FXML
    public void SWfalscheFragen(ActionEvent event) throws IOException{

        Parent markierteFragenView = FXMLLoader.load(getClass().getResource("lernapp_gui_falscheFragen.fxml"));
        Scene markierteFragenViewScene = new Scene(markierteFragenView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(markierteFragenViewScene);
        window.show();
    }

}
