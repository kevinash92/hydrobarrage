package app.polytechnique.hydrobarrage.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXCheckBox;

import app.polytechnique.framework.Calcul;
import app.polytechnique.framework.Icalcul;
import app.polytechnique.hydrobarrage.domain.DataModel;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/analyse.fxml")
public class AnalyseCtrl extends AbstractController {
	
    @FXML
    private ToggleGroup demandeGroup;
    
    @FXML
    private Label piLabel;

    @FXML
    private Label pgLabel;

    @FXML
    private Label prodLabel;

    @FXML
    private Label recLabel;

    @FXML
    private Label chargeLabel;
    
    @FXML
    private JFXCheckBox localCheck;
    
    @FXML
    private Label observationLabel;
    
    
    private Icalcul calcul;

    @PostConstruct
    public void init () {
    	calcul = new Calcul();
    	
    	double pi = calcul.puissanceInst(DataModel.getDebit(), DataModel.getChute().getHauteur());
    	double pg = calcul.puissanceGarantie(DataModel.getDebit()*90.0/100, DataModel.getChute().getHauteur());
    	double prod = calcul.productible(pi);
    	double rec = calcul.recette(prod);
    	
    	
    	piLabel.setText(String.valueOf(pi));
    	pgLabel.setText(String.valueOf(pg));
    	prodLabel.setText(String.valueOf(prod));
    	recLabel.setText(String.valueOf(rec));
    	this.gestionCharges(pi);
    	
    	localCheck.setOnAction(evt->{
    		DataModel.setPuissanceInstallee(pi);
    		this.gestionCharges(pi);
    	});
    	
    	
    }
    
    private void gestionCharges(double pi) {
    	List<Integer> cout = calcul.coutOuvrage(pi, localCheck.isSelected());
    	switch (cout.size()) {
		case 0:
			chargeLabel.setText("--");
			observationLabel.setText("Le projet n'est pas pris en charge");
			nextBtn.setDisable(true);
			break;
		case 1:
			nextBtn.setDisable(false);
			chargeLabel.setText(""+cout.get(0));
			observationLabel.setText(String.format("La valeur du coût est <= %f, nous le prendrons égale %f"+cout.get(0), cout.get(0)));
			break;
		case 2:
			nextBtn.setDisable(false);
			Integer valeurCout = (int) ((cout.get(0)*1.0+cout.get(1))/2);
			chargeLabel.setText(String.valueOf(valeurCout));
			observationLabel.setText(String.format("Le coût est compris dans l'intervalle [%d, %d]\n"
					+ "Pour le calcul des charges nous l'estimons égal à la moyenne = %d", cout.get(0), cout.get(1), valeurCout));
			break;
		}
	}

	@Override
    public void onNext() throws VetoException, FlowException {
    	step++;
    	flowHandler.navigate(RecommandationCtrl.class);
    }
    
    
    
}
