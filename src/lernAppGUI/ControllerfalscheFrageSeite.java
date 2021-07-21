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

public class ControllerfalscheFrageSeite extends ControllerFragen{


    /**
     * initializiert Fragenliste, Frage, Antworten
     */
    @FXML
    public void initialize(){


        fragenarray = db.falscheFragen();


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
        if (aktuelleFrage == (fragenarray.length - 1)) {
            buttonweiter.setText("Auswertung");
        }
        buttonzurueck.setDisable(true);
        labelAnzeige.setText("Frage " + (aktuelleFrage + 1) + " von " + fragenarray.length);

        textStatistik.setVisible(false);
        labelWieViele.setVisible(false);
        labelWieVieleRichtig.setVisible(false);
        labelWieVieleFalsch.setVisible(false);
        buttonZurueckHome.setDisable(true);
        buttonZurueckHome.setVisible(false);

        checkboxmakieren.setSelected(db.selectMarkiert("Select * from fragen where id = " + fragenarray[aktuelleFrage]));


        antworthash.clear();

    }
}