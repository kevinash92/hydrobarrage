/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.polytechnique.hydrobarrage.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.annotation.PostConstruct;

/**
 *
 * @author kevinash
 */
@ViewController("/views/login.fxml")
public class LoginController {
	
    @FXML
    private AnchorPane rootPane;

    @ViewNode
    @ActionTrigger("link")
    private Hyperlink copyrightLink;

    @ViewNode
    private JFXTextField usernameField;

    @ViewNode
    private JFXPasswordField passwordField;

    @ViewNode
    @ActionTrigger("login")
    private JFXButton loginButton;

    @ViewNode
    @ActionTrigger("close")
    private JFXButton closeButton;

    @ViewNode
    private Label messageLabel;
    
    @ActionHandler
    private FlowActionHandler flowHandler;

    @PostConstruct
    public void init() {
        messageLabel.setVisible(false);
    }

    @ActionMethod("login")
    public void login() {
     
        Flow flow = new Flow(ChuteController.class);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNIFIED);
        Parent parent = null;
        try {
            parent = flow.start();
        } catch (FlowException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stage.setScene(new Scene(parent));
        stage.show();
        rootPane.getScene().getWindow().hide();
    }

    @ActionMethod("close")
    public void close() {
        System.exit(0);
    }
}
