package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/debit.fxml")
public class DebitController extends AbstractController {
	
    @FXML
    private ToggleGroup demandeGroup;

    @PostConstruct
    public void init () {
    	choiceGroup = demandeGroup;
    }
    
    @Override
    public void onNext() throws VetoException, FlowException {
    	JFXRadioButton radio = (JFXRadioButton) choiceGroup.getSelectedToggle();
    	if (radio.getText().equalsIgnoreCase("Oui")) {
			JFXButton bouton = new JFXButton("J'ai compris");
			bouton.setOnAction(e-> {
				dialog.close();
				try {
					flowHandler.navigate(DonneesController.class);
				} catch (VetoException | FlowException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			content.getActions().setAll(bouton);
			showAlert("Information", "Pour la suite, veuillez importer vos données depuis un fichier excel (.xlsx)"
					+ "valider.\n Utilisez le bouton \"charger\" pour cela.");
		} else {
			step++;
			flowHandler.navigate(PluviometrieController.class);
		}
    }
    
    
    
}
