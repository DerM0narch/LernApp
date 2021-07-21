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

public class ControllerFrageSeite extends ControllerFragen {

    private Set<Integer> fragenhash = new LinkedHashSet<Integer>();

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


}