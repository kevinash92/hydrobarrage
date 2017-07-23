package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;

import io.datafx.controller.ViewController;
import javafx.fxml.FXML;

@ViewController("/views/actionBar.fxml")
public class MyController {
	
//	@ActionHandler
//	private FlowActionHandler handler;
//
//  @ViewNode
//  @ActionTrigger("")
//  private JFXButton next;
//  
//  @ViewNode
//  @ActionTrigger("action-id")
//  private Button myButton;
	@FXML
	private JFXButton testId;
  
  @PostConstruct
  private void init() {
//	  next.setText("goto");
//	  System.out.println(DataModel.getNom());
	  System.out.println(testId);
  }
  
//  @ActionMethod("action-id")
//  private void onAction() throws VetoException, FlowException {
//    System.out.println("clicked:");
//    handler.navigate(ChuteController.class);
//  }
  
}