package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;

import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public abstract class AbstractController {
	
	@FXML
	protected StackPane rootPane;
	@FXML
	@ActionTrigger("back")
	protected JFXButton backBtn;
	@FXML
	@ActionTrigger("next")
	protected JFXButton nextBtn;
	@ActionHandler
	protected FlowActionHandler flowHandler;
	@FXML
	protected Label stepLabel;
	
	protected static JFXDialog dialog;
	protected JFXDialogLayout content = new JFXDialogLayout();
	protected ToggleGroup choiceGroup;
	
	protected static int step = 1;
	
	@ActionMethod("next")
	public abstract void onNext() throws VetoException, FlowException;
	
	
	@PostConstruct
	private void initLabel() {
		stepLabel.setText(String.valueOf(step));
	}
	
	public static JFXDialog getDialog() {
		return dialog;
	}
	
	public void showAlert(String heading, String body) {
		StackPane parent = (StackPane) rootPane.getParent();
		if (parent == null) {
			parent = rootPane;
		}
        dialog = new JFXDialog(parent, content, JFXDialog.DialogTransition.TOP);
                
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        dialog.show();
	}
	
	protected void handleChoice(Class<?> controller, String heading, String body) throws VetoException, FlowException {
		JFXRadioButton selected = (JFXRadioButton) choiceGroup.getSelectedToggle();
    	if (selected.getText().equalsIgnoreCase("Oui")) {
    		step++;
			flowHandler.navigate(controller);
		} else {
            showAlert(heading, body);
		}
	}
	
	@ActionMethod("back")
	private void back() throws VetoException, FlowException {
		step--;
		if(step < 1)
			step = 1;
		flowHandler.navigateBack();
	}
	
}
