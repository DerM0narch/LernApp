package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lernApp.Datenbank;
import org.w3c.dom.Text;

import java.io.IOException;


public class ControllerFrageHinzufuegen {

    Datenbank db = new Datenbank();
    private String frage = "";
    private String richtig = "";
    private String erstefalsch = "";
    private String zweitefalsch = "";

    @FXML
    private Button buttonzufaelligeFragen;

    @FXML
    private TextArea textAreaFrage;

    @FXML
    private TextField textAreaRichtig, textAreaErsteFalsch, textAreaZweiteFalsch;

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

    public void frageHinzufuegen(ActionEvent event) {

        frage = textAreaFrage.getText();
        richtig = textAreaRichtig.getText();
        erstefalsch = textAreaErsteFalsch.getText();
        zweitefalsch = textAreaZweiteFalsch.getText();

        db.insert("Insert into 'fragen' (frage, richtig, ersteFalsch, zweiteFalsch) values ('" + frage + "', '" + richtig + "', '" + erstefalsch + "', '" + zweitefalsch + "')");

        textAreaFrage.setText("");
        textAreaRichtig.setText("");
        textAreaErsteFalsch.setText("");
        textAreaZweiteFalsch.setText("");

    }

}