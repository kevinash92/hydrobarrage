package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import app.polytechnique.framework.Calcul;
import app.polytechnique.framework.Icalcul;
import app.polytechnique.hydrobarrage.domain.DataModel;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

@ViewController("/views/estimation.fxml")
public class EstimationController extends AbstractController {

	@FXML
    private JFXTextField qmField;

    @FXML
    private JFXTextField hmField;

    @FXML
    private JFXButton estimerBtn;

    @FXML
    private AnchorPane resultPane;

    @FXML
    private Label resultLabel;
    
    @FXML
    private JFXTextField bmField;

    @FXML
    private JFXTextArea observationLabel;
    
    private boolean isDemandeDurable = false;
    
	@PostConstruct
	public void init() {
		
		bmField.setText(DataModel.getDemandeEnergetique()+"");
		hmField.setText(DataModel.getChute().getHauteur()+"");
		nextBtn.setDisable(true);
		
		
		resultPane.setVisible(false);
		estimerBtn.setOnAction(e ->{
			if (!resultPane.isVisible()) {
				resultPane.setVisible(true);
				qmField.setEditable(true);
			} 
			this.estimate();
			nextBtn.setDisable(false);
		});
	}
	
	@Override
	public void onNext() throws VetoException, FlowException {
		if (!isDemandeDurable) {
			JFXButton okBtn = new JFXButton("OK");
			okBtn.setOnAction(e -> {
				dialog.close();;
			});
			content.getActions().setAll(okBtn);
			showAlert("Fin programme", "Le projet n'est pas favorable pour un développement durable\n"
					+ "impossible de continuer");
		}
		else {
			step++;
			flowHandler.navigate(EngagementController.class);
		}
	}
	
	private void estimate() {
		DoubleValidator qmValidator = new DoubleValidator();
		qmValidator.setMessage("Entrez un réel > 0");
		qmField.getValidators().add(qmValidator);
		
		if (!qmField.validate()) {
			return;
		}
		
		Icalcul calcul = new Calcul();
		
		double qm = Double.parseDouble(qmField.getText());
		double hm = DataModel.getChute().getHauteur();
		double pot = calcul.potentielEnergetique(qm, hm);
		
		DataModel.setDebit(qm);
		
		DataModel.setPotentielEnergetique(pot);
		resultLabel.setText(pot+"");
		
		this.isDemandeDurable = calcul.compaDemandePotentiel(DataModel.getDemandeEnergetique(), DataModel.getPotentielEnergetique());
		
		System.out.println(String.format("demande energetique:%f\npotentiel energetique:%f\ndemande durable?+%s", DataModel.getDemandeEnergetique(),DataModel.getPotentielEnergetique(), isDemandeDurable+""));
		
		String observation;
		
		if (this.isDemandeDurable) {
			observation = String.format("Le projet est favorable pour un developpement durable\n"
					+ "car le potentiel(%f) est supérieur à la demande (%f)", pot, DataModel.getDemandeEnergetique());
			Button okBtn = new JFXButton("OK");
			okBtn.setOnAction(e -> {
				dialog.close();
			});
			content.getActions().setAll(okBtn);
			showAlert("Information", "Le potentiel répond à la demande de façon durable");
		} else {
			observation = String.format("Le projet n'est pas favorable pour un developpement durable\n"
					+ "car le potentiel(%f) est inférieur à la demande (%f)", pot, DataModel.getDemandeEnergetique());
			Button okBtn = new JFXButton("OK");
			okBtn.setOnAction(e -> {
				dialog.close();
			});
			content.getActions().setAll(okBtn);
			showAlert("Information", "Le potentiel ne répond pas à la demande de façon durable");
		}
		
		observationLabel.setText(observation);
		
	}

}
