package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

@ViewController("/views/engagement.fxml")
public class EngagementController extends AbstractController {
	
    @FXML
    private ToggleGroup chuteGroup;
    
    @PostConstruct
    public void init() {
    	choiceGroup = chuteGroup;
    }
    
    @Override
    public void onNext() throws VetoException, FlowException {
    	step++;
    	flowHandler.navigate(DebitController.class);
    }
    
}
