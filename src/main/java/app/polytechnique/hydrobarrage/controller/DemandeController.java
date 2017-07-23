package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/demande.fxml")
public class DemandeController extends AbstractController {
	
    @FXML
    private ToggleGroup demandeGroup;

    @PostConstruct
    public void init () {
    	choiceGroup = demandeGroup;
    }
    
    @Override
    public void onNext() throws VetoException, FlowException {
    	JFXButton closeBtn = new JFXButton("OK");
    	closeBtn.setCursor(Cursor.HAND);
    	closeBtn.setOnAction(e -> {
    		dialog.close();
    		System.exit(0);
    	});
    	content.setActions(closeBtn);
    	
    	handleChoice(DemandeFormController.class, "Fin du programme", "La MCH n'est pas intérréssante!");
    	
    }
    
    
    
}
