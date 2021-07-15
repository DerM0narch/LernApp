package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klassen.Datenbank;

public class ControllerFrageHinzufuegen {

    private final Datenbank db = new Datenbank();
    @FXML
    TextArea taFrage;
    @FXML
    TextField tfRichtig, tfFalsch1, tfFalsch2;

    @FXML
    public void intitialize(){}

    public void addQuestion( ) {
    }


}
