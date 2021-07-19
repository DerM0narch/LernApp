package lernAppGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lernApp.Datenbank;

import java.io.IOException;
import java.util.Objects;

public class ControllerSchwerpunkt {

    private Datenbank db = new Datenbank();
    private String schwerpunkt;

    @FXML
    private Button zurueck, buttonNetzwerktechnik, buttonITSicherheit, buttonHardware, buttonSoftware;

    @FXML
    public void intitialize(){}

    public void clickButton(){
        System.out.println("Knopf gedürckt");
    }

    public void zurueck(ActionEvent event) throws IOException {

        Parent startseite = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        Scene startseiteScene = new Scene(startseite);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(startseiteScene);
        window.show();
    }

    public void schwerpunktAuswaehlen(ActionEvent event) throws IOException {

        if ((Button) event.getSource() == buttonNetzwerktechnik) {
            schwerpunkt = "Netzwerktechnik";
        } else if ((Button) event.getSource() == buttonITSicherheit) {
            schwerpunkt = "IT-Sicherheit";
        } else if ((Button) event.getSource() == buttonHardware){
            schwerpunkt = "Hardware";
        } else if ((Button) event.getSource() == buttonSoftware) {
            schwerpunkt = "Software";
        } else {
            System.out.println("Fehler");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("lernapp_gui_schwerpunktFragen.fxml"));
        Parent schwerpunktView = loader.load();
        Scene schwerpunktViewScene = new Scene(schwerpunktView);
        //System.out.println(schwerpunktView);
        ControllerSchwerpunktFrageSeite schwerpunktFrageSeite = (ControllerSchwerpunktFrageSeite) loader.getController();
        System.out.println("in Schwerpunkt" + schwerpunkt);
        schwerpunktFrageSeite.setSchwerpunkt(schwerpunkt);
        schwerpunktFrageSeite.manuellInitialisation();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(schwerpunktViewScene);
        window.show();
    }

    public String getSchwerpunkt() {
        return schwerpunkt;
    }

}

