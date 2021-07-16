package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lernApp.Datenbank;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class ControllerFrageSeite {

    private Set<Integer> fragenhash = new LinkedHashSet<Integer>();
    private Datenbank db = new Datenbank();
    private Integer[] fragenarray;
    private int aktuelleFrage = 0;

    @FXML
    private Button buttonabbrechen, buttonweiter, buttonpruefen, buttonzurueck;

    @FXML
    private RadioButton radio1, radio2, radio3;

    @FXML
    private TextArea textfrage;

    @FXML
    private CheckBox checkboxmakieren;

    @FXML
    private Label labelAnzeige;

    @FXML
    public void initialize(){
        //textfrage.setW
        int anzahlFragen = db.selectCount();

        while (fragenhash.size() < 20){
            fragenhash.add(new Random().nextInt(anzahlFragen));
        }

        fragenarray = new Integer[fragenhash.size()];
        fragenarray = fragenhash.toArray(fragenarray);

        for (int i = 0; i < fragenarray.length; i++){
            System.out.println(fragenarray[i]);
        }


        textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio1.setText(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio2.setText(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio3.setText(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        buttonzurueck.setDisable(true);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von 20");

    }


    @FXML
    public void zurueck(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();
    }

    public void naechsteFrage(ActionEvent event) {
        aktuelleFrage++;
        buttonzurueck.setDisable(false);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von 20");
        if (aktuelleFrage > 18){
            buttonweiter.setDisable(true); ;
        }

        textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio1.setText(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio2.setText(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio3.setText(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
    }

    public void vorherigeFrage(ActionEvent event) {
        aktuelleFrage--;
        buttonweiter.setDisable(false);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von 20");
        if (aktuelleFrage < 1){
            buttonzurueck.setDisable(true); ;
        }
        textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio1.setText(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio2.setText(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio3.setText(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));

    }




















}