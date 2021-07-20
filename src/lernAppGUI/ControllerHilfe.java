package lernAppGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ControllerHilfe {

    /**
     * ermöglicht zurück auf Startseite
     * @param event Knopf wird gedrückt
     * @throws IOException wenn die datei nicht gefunden wird
     */
    @FXML
    public void zurueck(ActionEvent event) throws IOException {

        Parent startView = FXMLLoader.load(getClass().getResource("lernapp_gui_start.fxml"));
        Scene startViewScene = new Scene(startView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(startViewScene);
        window.show();
    }

}
