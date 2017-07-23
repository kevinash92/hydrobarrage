package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/chute.fxml")
public class ChuteController extends AbstractController {
	
    @FXML
    private ToggleGroup chuteGroup;
    
    @PostConstruct
    public void init() {
    	choiceGroup = chuteGroup;
    }
    
    @Override
    public void onNext() throws VetoException, FlowException {
    	JFXButton okBtn = new JFXButton("OK");
		okBtn.setOnAction(e -> {
			System.exit(0);
		});
		content.setActions(okBtn);
    	handleChoice(SiteController.class, "Fin du programme", "Pas de MCH");
    }
    
}
