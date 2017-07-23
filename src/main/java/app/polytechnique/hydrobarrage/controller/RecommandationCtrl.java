package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import app.polytechnique.framework.Calcul;
import app.polytechnique.framework.Icalcul;
import app.polytechnique.hydrobarrage.domain.DataModel;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;

@ViewController("/views/recommandations.fxml")
public class RecommandationCtrl extends AbstractController {
	@FXML
	private JFXToggleButton autoToggle;

	@FXML
	private JFXTextField rField;

	@FXML
	private JFXTextField cField;
	
	@FXML
	@ActionTrigger("evaluate")
	private JFXButton evaluerBtn;
	
	private Icalcul calcul;
	
	@PostConstruct
	public void init() {
		calcul = new Calcul();
		
		autoToggle.setOnAction(value -> {
			String regime = calcul.regimeJuridiqiue(DataModel.getPuissanceInstallee(), autoToggle.isSelected());
			rField.setText(regime);
			String choix = calcul.choixOption(DataModel.getChute().getHauteur(), DataModel.getDebit());
			cField.setText(choix);
		});
		
		String regime = calcul.regimeJuridiqiue(DataModel.getPuissanceInstallee(), autoToggle.isSelected());
		rField.setText(regime);
		String choix = calcul.choixOption(DataModel.getChute().getHauteur(), DataModel.getDebit());
		cField.setText(choix);
	}
	
	@Override
	public void onNext() throws VetoException, FlowException {
		flowHandler.navigate(CaracteristiqueCtrl.class);
	}
}
