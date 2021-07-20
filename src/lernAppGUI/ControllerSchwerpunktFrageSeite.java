package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lernApp.Datenbank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ControllerSchwerpunktFrageSeite {

    private Datenbank db = new Datenbank();
    private Integer[] fragenarray;
    private int aktuelleFrage = 0;
    private ArrayList<String> antworthash = new ArrayList<String>();
    private String[] antwortenarray;
    private String schwerpunkt;

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

    /**
     * initializiert Fragenliste, Frage, Antworten
     */
    public void manuellInitialisation(){
        /*ControllerSchwerpunkt schwerpunkt = new ControllerSchwerpunkt();
        System.out.println(schwerpunkt.getSchwerpunkt()); */
        fragenarray = db.schwerpunktFragen(schwerpunkt);
        System.out.println(fragenarray.length);

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
        radio1.setText(zeilenUmbruch(antwortenarray[0]));
        radio2.setText(zeilenUmbruch(antwortenarray[1]));
        radio3.setText(zeilenUmbruch(antwortenarray[2]));
        if (aktuelleFrage == (fragenarray.length -1)) {
            buttonweiter.setDisable(true);
        }
        buttonzurueck.setDisable(true);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenarray.length);

        checkboxmakieren.setSelected(db.selectMarkiert("Select * from fragen where id = " + fragenarray[aktuelleFrage]));


        antworthash.clear();

    }

    /**
     * ermöglicht zurück auf Startseite
     * @param event Knopf wird gedrückt
     * @throws IOException wenn die datei nicht gefunden wird
     */
    @FXML
    public void zurueck(ActionEvent event) throws IOException {

        Parent hilfeView = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        Scene hilfeViewScene = new Scene(hilfeView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(hilfeViewScene);
        window.show();
    }

    /**
     * neue Frage + Antworten werden geladen, in der fragenliste eine stelle weiter
     * @param event Knopfdruck
     */
    public void naechsteFrage(ActionEvent event) {
        try {
            ++aktuelleFrage;
            if (aktuelleFrage > (fragenarray.length - 2)){
                buttonweiter.setDisable(true);
            }
            buttonzurueck.setDisable(false);
            labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenarray.length);
            antworthash.clear();
            antworthash.add(db.selectRichtig("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectErsteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            antworthash.add(db.selectZweiteFalsch("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));

            Collections.shuffle(antworthash);

            antwortenarray = new String[antworthash.size()];
            antwortenarray = antworthash.toArray(antwortenarray);



            textfrage.setText(db.selectFrage("SELECT * from fragen where id= " + fragenarray[aktuelleFrage]));
            radio1.setText(zeilenUmbruch(antwortenarray[0]));
            radio2.setText(zeilenUmbruch(antwortenarray[1]));
            radio3.setText(zeilenUmbruch(antwortenarray[2]));

            RadioButton ausgewaehlt = (RadioButton) antworten.getSelectedToggle();
            if (ausgewaehlt != null) {
                ausgewaehlt.setSelected(false);
            }
            radio1.setTextFill(Color.web("#ffe667"));
            radio2.setTextFill(Color.web("#ffe667"));
            radio3.setTextFill(Color.web("#ffe667"));
            buttonpruefen.setDisable(false);

            checkboxmakieren.setSelected(db.selectMarkiert("Select * from fragen where id = " + fragenarray[aktuelleFrage]));

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * vorherige Frage + Antworten werden geladen, in der fragenliste eine stelle zurück
     * @param event Knopfdruck
     */
    public void vorherigeFrage(ActionEvent event) {
        try {
            --aktuelleFrage;
            buttonweiter.setDisable(false);
            labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenarray.length);
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
            radio1.setText(zeilenUmbruch(antwortenarray[0]));
            radio2.setText(zeilenUmbruch(antwortenarray[1]));
            radio3.setText(zeilenUmbruch(antwortenarray[2]));
            RadioButton ausgewaehlt = (RadioButton) antworten.getSelectedToggle();
            if (ausgewaehlt != null) {
                ausgewaehlt.setSelected(false);
            }

            radio1.setTextFill(Color.web("#ffe667"));
            radio2.setTextFill(Color.web("#ffe667"));
            radio3.setTextFill(Color.web("#ffe667"));
            buttonpruefen.setDisable(false);

            checkboxmakieren.setSelected(db.selectMarkiert("Select * from fragen where id = " + fragenarray[aktuelleFrage]));

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prüft ob die angegebene Antwort die richtige ist, verändert ggf. Wert in db
     * @param event Knopfdruck
     */
    public void antwortPruefen(ActionEvent event) {
        try {
            RadioButton ausgewaehlt = (RadioButton) antworten.getSelectedToggle();
            String antwort = ausgewaehlt.getText();

            if (antwort.equals(db.selectRichtig("SELECT * from fragen where id= "+ fragenarray[aktuelleFrage]))) {
                ausgewaehlt.setTextFill(Color.web("#00FF00"));
                db.execute("Update fragen set istfalsch = 0 where id = " + fragenarray[aktuelleFrage]);
            } else {
                ausgewaehlt.setTextFill(Color.web("#FF0000"));
                db.execute("Update fragen set istfalsch = 1 where id = " + fragenarray[aktuelleFrage]);
            }

            buttonpruefen.setDisable(true);
        } catch (NullPointerException e) {

        }
    }

    /**
     * verändert den Wert "markiert" in Datenbank
     * @param event Knopfdruck
     */
    public void makierenFragen(ActionEvent event) {

        if (db.selectMarkiert("Select * from fragen where id= " + fragenarray[aktuelleFrage])){
            db.execute("Update fragen set markiert = 0 where id= " + fragenarray[aktuelleFrage]);
        } else {
            db.execute("Update fragen set markiert = 1 where id= " + fragenarray[aktuelleFrage]);
        }

    }

    public void setSchwerpunkt(String schwerpunkt) {
        this.schwerpunkt = schwerpunkt;
    }

    /**
     * fügt einen Zeilenumbruch in einen zu langen String
     * @param antwort ist String aus der Datenbank kommt
     * @return gibt String zurück
     */
    public String zeilenUmbruch(String antwort){
        if (antwort.length() > 59) {
            String result = antwort.substring(0, 59) + "-\n" + antwort.substring(59);
            return result;
        }
        return antwort;
    }

}