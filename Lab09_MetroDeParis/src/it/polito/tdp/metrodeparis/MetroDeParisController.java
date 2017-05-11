/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbStazionePartenza"
    private ComboBox<Fermata> cmbStazionePartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbStazioneArrivo"
    private ComboBox<Fermata> cmbStazioneArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    public void setModel(Model model){
    	this.model=model;
    	cmbStazionePartenza.getItems().addAll(model.getFermate());
    	cmbStazioneArrivo.getItems().addAll(model.getFermate());
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	Fermata stazionePartenza=this.cmbStazionePartenza.getValue();
    	Fermata stazioneArrivo=this.cmbStazioneArrivo.getValue();
    	if(stazionePartenza==null){
    		txtResult.setText("Errore: nessuna stazione di partenza selezionata");
    		return;
    	}
    	if(stazioneArrivo==null){
    		txtResult.setText("Errore: nessuna stazione di arrivo selezionata");
    		return;
    	}
    	List<Fermata> result=model.getCamminoMinimo(stazionePartenza, stazioneArrivo);
    	if(result.size()==0){
    		txtResult.setText("Non esiste nessun cammino minimo tra "+stazionePartenza+" e "+stazioneArrivo);
    		return;
    	}
    	txtResult.setText("Percorso: "+result.toString()+"\n");
    	txtResult.appendText("Tempo di percorrenza stimato: "+model.getTempoPercorrenza(stazionePartenza, stazioneArrivo));
    	return;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbStazionePartenza != null : "fx:id=\"cmbStazionePartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbStazioneArrivo != null : "fx:id=\"cmbStazioneArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
}

