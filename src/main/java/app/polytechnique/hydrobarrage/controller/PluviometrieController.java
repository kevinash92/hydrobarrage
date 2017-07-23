package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/pluvio.fxml")
public class PluviometrieController extends AbstractController {
	
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
					step++;
					flowHandler.navigate(DonneesController2.class);
				} catch (VetoException | FlowException e1) {
					e1.printStackTrace();
				}
			});
			content.getActions().setAll(bouton);
			showAlert("Information", "La partie suivant est en version snapshot!!!");
		} else {
			JFXButton bouton = new JFXButton("J'ai compris");
			bouton.setOnAction(e-> {
				dialog.close();
				System.exit(0);
			});
			content.getActions().setAll(bouton);
			showAlert("Fin du programme", "Effectuez une collecte des débits sur un an");
		}
    }
    
    
    
}
