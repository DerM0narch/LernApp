package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerStartseite {

    @FXML
    private Button buttonzufaelligeFragen;

    public void clickButton(){
        System.out.println("Knopf gedürckt");
    }

}
