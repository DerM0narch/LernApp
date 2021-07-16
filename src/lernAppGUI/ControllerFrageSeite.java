package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lernApp.Datenbank;

import java.io.IOException;
import java.util.*;

public class ControllerFrageSeite {

    private Set<Integer> fragenhash = new LinkedHashSet<Integer>();
    private Datenbank db = new Datenbank();
    private Integer[] fragenarray;
    private int aktuelleFrage = 0;
    private ArrayList<String> antworthash = new ArrayList<String>();
    private String[] antwortenarray;

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
    private ToggleGroup antworten;

    @FXML
    public void initialize(){
        //textfrage.setW
        int anzahlFragen = db.selectCount();

        while (fragenhash.size() < 20){
            fragenhash.add(new Random().nextInt(anzahlFragen));
        }

        fragenarray = new Integer[fragenhash.size()];
        fragenarray = fragenhash.toArray(fragenarray);


        /*for (int i = 0; i < fragenarray.length; i++){
            System.out.println(fragenarray[i]);
        }*/

        antworthash.add(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        antworthash.add(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        antworthash.add(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));

        Collections.shuffle(antworthash);

        antwortenarray = new String[antworthash.size()];
        antwortenarray = antworthash.toArray(antwortenarray);



        textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
        radio1.setText(antwortenarray[0]);
        radio2.setText(antwortenarray[1]);
        radio3.setText(antwortenarray[2]);
        buttonzurueck.setDisable(true);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenhash.size());

        antworthash.clear();

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
        try {
            ++aktuelleFrage;
            buttonzurueck.setDisable(false);
            labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenhash.size());
            antworthash.clear();
            antworthash.add(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));

            Collections.shuffle(antworthash);

            antwortenarray = new String[antworthash.size()];
            antwortenarray = antworthash.toArray(antwortenarray);

            if (aktuelleFrage > 18){
                buttonweiter.setDisable(true); ;
            }

            textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            radio1.setText(antwortenarray[0]);
            radio2.setText(antwortenarray[1]);
            radio3.setText(antwortenarray[2]);

            RadioButton ausgewaehlt = (RadioButton) antworten.getSelectedToggle();
            ausgewaehlt.setSelected(false);
            ausgewaehlt.setTextFill(Color.web("#ffe667"));
            buttonpruefen.setDisable(false);
        } catch (NullPointerException e) {

        }
    }

    public void vorherigeFrage(ActionEvent event) {
        try {
            --aktuelleFrage;
            buttonweiter.setDisable(false);
            labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenhash.size());
            antworthash.clear();
            antworthash.add(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));

            Collections.shuffle(antworthash);

            antwortenarray = new String[antworthash.size()];
            antwortenarray = antworthash.toArray(antwortenarray);

            if (aktuelleFrage < 1){
                buttonzurueck.setDisable(true); ;
            }
            textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            radio1.setText(antwortenarray[0]);
            radio2.setText(antwortenarray[1]);
            radio3.setText(antwortenarray[2]);

            buttonpruefen.setDisable(false);

        } catch (NullPointerException e) {

        }
    }

    public void antwortPruefen(ActionEvent event) {
        try {
            RadioButton ausgewaehlt = (RadioButton) antworten.getSelectedToggle();
            String antwort = ausgewaehlt.getText();

            if (antwort.equals(db.selectRichtig("SELECT * from fragen where id= "+ fragenarray[aktuelleFrage]))) {
                ausgewaehlt.setTextFill(Color.web("#00FF00"));
            } else {
                ausgewaehlt.setTextFill(Color.web("#FF0000"));
            }

            buttonpruefen.setDisable(true);
        } catch (NullPointerException e) {

        }
    }




















}