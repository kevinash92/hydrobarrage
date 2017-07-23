package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@ViewController("/view/simpleViews.fxml")
public class SimpleController {
 
    @FXML
    private Label resultLabel;
 
    @FXML
    @ActionTrigger("myAction")
    private Button actionButton;
 
    private int clickCount = 0;
 
    @PostConstruct
    public void init() {
        resultLabel.setText("Button was clicked " + clickCount + " times");
    }
 
    @ActionMethod("myAction")
    public void onAction() {
        clickCount++;
        resultLabel.setText("Button was clicked " + clickCount + " times");
    }
}