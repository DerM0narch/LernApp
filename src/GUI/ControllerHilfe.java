package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerHilfe {

    @FXML
    private Button buttonzufaelligeFragen;

    @FXML
    public void intitialize(){}

    public void clickButton(){
        System.out.println("Knopf gedürckt");
    }

}
