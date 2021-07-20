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
    private int anzahlFragenBeantwortet = 0;
    private int anzahlFragenRichtig = 0;
    private int anzahlFragenFalsch = 0;

    @FXML
    private Button buttonabbrechen, buttonweiter, buttonpruefen, buttonzurueck, buttonZurueckHome;

    @FXML
    private RadioButton radio1, radio2, radio3;

    @FXML
    private TextArea textfrage;

    @FXML
    private CheckBox checkboxmakieren;

    @FXML
    private Label labelAnzeige, labelWieViele, labelWieVieleRichtig, labelWieVieleFalsch, textStatistik, labelFrage, labelAntwort;

    @FXML
    private ToggleGroup antworten;

    /**
     * initializiert Fragenliste, Frage, Antworten
     */
    @FXML
    public void initialize(){
        //textfrage.setW
        int anzahlFragen = db.selectCount();

        ArrayList<Integer> allIDs = db.selectAllFragenIDs();

        Collections.shuffle(allIDs);

        for (int i = 0; i < 20; i++) {
            fragenhash.add(allIDs.get(i));
        }
        /* while (fragenhash.size() < 20){
            fragenhash.add(new Random().nextInt(anzahlFragen));
        } */

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
        radio1.setText(zeilenUmbruch(antwortenarray[0]));
        radio2.setText(zeilenUmbruch(antwortenarray[1]));
        radio3.setText(zeilenUmbruch(antwortenarray[2]));
        buttonzurueck.setDisable(true);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenhash.size());

        textStatistik.setVisible(false);
        labelWieViele.setVisible(false);
        labelWieVieleRichtig.setVisible(false);
        labelWieVieleFalsch.setVisible(false);
        buttonZurueckHome.setDisable(true);
        buttonZurueckHome.setVisible(false);

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
        if (buttonweiter.getText().equals("Auswertung")) {

            buttonweiter.setVisible(false);
            buttonzurueck.setVisible(false);
            buttonabbrechen.setVisible(false);
            buttonpruefen.setVisible(false);
            radio1.setVisible(false);
            radio2.setVisible(false);
            radio3.setVisible(false);
            textfrage.setVisible(false);
            labelFrage.setVisible(false);
            labelAnzeige.setVisible(false);
            labelAntwort.setVisible(false);
            checkboxmakieren.setVisible(false);

            buttonweiter.setDisable(true);
            buttonzurueck.setDisable(true);
            buttonabbrechen.setDisable(true);
            buttonpruefen.setDisable(true);
            radio1.setDisable(true);
            radio2.setDisable(true);
            radio3.setDisable(true);
            checkboxmakieren.setDisable(true);

            textStatistik.setVisible(true);
            labelWieViele.setText("Du hast " + anzahlFragenBeantwortet + " von " + fragenhash.size() + " Fragen beantwortet");
            labelWieViele.setVisible(true);
            labelWieVieleRichtig.setText("Du hast " + anzahlFragenRichtig + " von " + anzahlFragenBeantwortet + " richtig beantwortet");
            labelWieVieleRichtig.setVisible(true);
            labelWieVieleFalsch.setText("Du hast " + anzahlFragenFalsch + " von " + anzahlFragenBeantwortet + " falsch beantwortet");
            labelWieVieleFalsch.setVisible(true);
            buttonZurueckHome.setDisable(false);
            buttonZurueckHome.setVisible(true);

        } else {
            try {
                //System.out.println(anzahlFragenBeantwortet);
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

                if (aktuelleFrage > (fragenarray.length - 2)){
                    buttonweiter.setText("Auswertung");
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
    }

    /**
     * vorherige Frage + Antworten werden geladen, in der fragenliste eine stelle zurück
     * @param event Knopfdruck
     */
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
            anzahlFragenBeantwortet++;
            if (antwort.equals(zeilenUmbruch(db.selectRichtig("SELECT * from fragen where id= "+ fragenarray[aktuelleFrage])))) {
                ausgewaehlt.setTextFill(Color.web("#00FF00"));
                db.execute("Update fragen set istfalsch = 0 where id = " + fragenarray[aktuelleFrage]);
                anzahlFragenRichtig++;
            } else {
                ausgewaehlt.setTextFill(Color.web("#FF0000"));
                db.execute("Update fragen set istfalsch = 1 where id = " + fragenarray[aktuelleFrage]);
                anzahlFragenFalsch++;
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

    /**
     * fügt einen Zeilenumbruch in einen zu langen String
     * @param antwort ist String aus der Datenbank kommt
     * @return gibt String zurück
     */
    public String zeilenUmbruch(String antwort){
        if (antwort.length() > 80) {
            String result = antwort.substring(0, 80) + "-\n" + antwort.substring(80);
            return result;
        }
        return antwort;
    }






















}