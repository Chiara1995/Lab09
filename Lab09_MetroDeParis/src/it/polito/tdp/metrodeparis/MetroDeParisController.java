/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbStazionePartenza"
    private ComboBox<?> cmbStazionePartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbStazioneArrivo"
    private ComboBox<?> cmbStazioneArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaPercorso"
    private Button btnCalcolaPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbStazionePartenza != null : "fx:id=\"cmbStazionePartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbStazioneArrivo != null : "fx:id=\"cmbStazioneArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
}

