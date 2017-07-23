package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

@ViewController("/views/layout.fxml")
public class LayoutController {
	@FXML
    private StackPane centerPane;

    @FXML
    private Label stepLabel;

    @FXML
    private Label titleLabel;
    
    private FlowHandler flowHandler;
    
    @PostConstruct
    public void init() throws FlowException {
    	Flow innerFlow = new Flow(ChuteController.class)
    			.withGlobalLink("site", SiteController.class)
    			.withGlobalLink("demande", SiteController.class);
    	flowHandler = innerFlow.createHandler();
    	centerPane.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.millis(1000), ContainerAnimations.SWIPE_LEFT)));
    }
    
    @ActionMethod("next")
    public void nextStep() throws VetoException, FlowException {
    	flowHandler.handle("site");
    }
    
}
