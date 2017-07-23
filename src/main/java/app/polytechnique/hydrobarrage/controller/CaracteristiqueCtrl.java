package app.polytechnique.hydrobarrage.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;

import app.polytechnique.framework.Calcul;
import app.polytechnique.framework.Icalcul;
import app.polytechnique.hydrobarrage.domain.DataModel;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@ViewController("/views/caracteristique.fxml")
public class CaracteristiqueCtrl extends AbstractController {
	@FXML
	private JFXTextField nField;

	@FXML
	private JFXTextField dField;

	@FXML
	private JFXTextField eField;

	@FXML
	private Label lLabel;

	@FXML
	private JFXTextField qField;

	@FXML
	private JFXTextField lpField;

	@FXML
	private JFXTextField hbField;

	@FXML
	private Label dpLabel;

	@FXML
	private Label tpLabel;

	@FXML
	private JFXTextField vDepotField;

	@FXML
	private Label scLabel;

	@FXML
	private JFXTextField vBarrageField;

	@FXML
	private JFXTextField diametreBarField;

	@FXML
	private JFXTextField vEcoulementField;

	@FXML
	private Label sLabel;

	@FXML
	private Label hLabel;

	@FXML
	@ActionTrigger("calcul_barrage")
    private JFXButton calculerBarrageBtn;

    @FXML
    @ActionTrigger("calcul_canal")
    private JFXButton calculerCanalBtn;
    
    @FXML
    @ActionTrigger("calcul_central")
    private JFXButton calculCentralBtn;
    
    @FXML
    @ActionTrigger("calcul_charge")
    private JFXButton calculMiseChargeBtn;
    
    @FXML
    @ActionTrigger("calcul_conduite")
    private JFXButton calculConduiteBtn;

	private Icalcul calcul = new Calcul();

	@PostConstruct
	public void init() {
		nextBtn.setDisable(true);
	}
	
	@ActionMethod("calcul_central")
	public void calcul1() {
		boolean formIsOk = true;
		String msg = "veillez entrer un réel > 0";
		DoubleValidator dValidator = new DoubleValidator();
		DoubleValidator eValidator = new DoubleValidator();
		
		dValidator.setMessage(msg);
		eValidator.setMessage(msg);
		
		dField.getValidators().clear();
		dField.getValidators().addAll(dValidator);
		eField.getValidators().clear();
		eField.getValidators().addAll(eValidator);
		
		
		if (!dField.validate()) {
			formIsOk = false;
		}
		
		if (!eField.validate()) {
			formIsOk = false;
		}
		
		if (!formIsOk) {
			return;
		}
		
		double d = Double.parseDouble(dField.getText());
		double e = Double.parseDouble(eField.getText());
		
		lLabel.setText(calcul.caracteristiqueUsine(0.85, e, d)+"");
	}
	
	@ActionMethod("calcul_conduite")
	public void calcul2() {
		boolean formIsOk = true;
		String msg = "entrez une valeur > 0";
		DoubleValidator qValidator = new DoubleValidator();
		DoubleValidator lpValidator = new DoubleValidator();
		
		qValidator.setMessage(msg);
		lpValidator.setMessage(msg);
		
		qField.getValidators().clear();
		qField.getValidators().addAll(qValidator);
		lpField.getValidators().clear();
		lpField.getValidators().addAll(lpValidator);
		
		
		if (!qField.validate()) {
			formIsOk = false;
		}
		
		if (!lpField.validate()) {
			formIsOk = false;
		}
		
		if (!formIsOk) {
			return;
		}
		
		double q = Double.parseDouble(qField.getText());
		double lp = Double.parseDouble(lpField.getText());
		
		List<Double> list = calcul.dimensionConduite(q, DataModel.getChute().getHauteur(), lp);
		
		double dp = list.get(1);
		double tp = list.get(0);
		
		dpLabel.setText(String.valueOf(dp));
		tpLabel.setText(String.valueOf(tp));
	}
	
	@ActionMethod("calcul_barrage")
	public void calcul3() {
		boolean formIsOk = true;
		String msg = "veillez entrer un réel > 0";
		DoubleValidator vValidator = new DoubleValidator();
		DoubleValidator dValidator = new DoubleValidator();
		
		dValidator.setMessage(msg);
		vValidator.setMessage(msg);
		
		vBarrageField.getValidators().clear();
		vBarrageField.getValidators().addAll(vValidator);
		diametreBarField.getValidators().clear();
		diametreBarField.getValidators().addAll(dValidator);
		
		
		if (!vBarrageField.validate()) {
			formIsOk = false;
		}
		
		if (!diametreBarField.validate()) {
			formIsOk = false;
		}
		
		if (!formIsOk) {
			return;
		}
		
		double v = Double.parseDouble(vBarrageField.getText());
		double d = Double.parseDouble(diametreBarField.getText());
		
		double pr = calcul.priseEau(d, v);
		
		sLabel.setText(String.valueOf(pr));
	}
	
	@ActionMethod("calcul_charge")
	public void calcul4() {
		
		String msg = "entrez une valeur > 0";
		DoubleValidator vValidator = new DoubleValidator();
		
		vValidator.setMessage(msg);
		
		vDepotField.getValidators().clear();
		vDepotField.getValidators().addAll(vValidator);
		
		
		if (!vDepotField.validate()) {
			return;
		}
		
		double v = Double.parseDouble(vDepotField.getText());
		double q = 0;
		
		try {
			q = Double.parseDouble(qField.getText());
		} catch (Exception e) {

		}
		
		
		double dim = calcul.dimensionChambre(q, v);
		scLabel.setText(String.valueOf(dim));
	}
	
	@ActionMethod("calcul_canal")
	public void calcul5() {
		
		String msg = "entrez une valeur > 0";
		DoubleValidator vValidator = new DoubleValidator();
		
		vValidator.setMessage(msg);
		
		vEcoulementField.getValidators().clear();
		vEcoulementField.getValidators().addAll(vValidator);
		
		
		if (!vEcoulementField.validate()) {
			return;
		}
		
		double v = Double.parseDouble(vEcoulementField.getText());
		double q = 0;
		
		try {
			q = Double.parseDouble(qField.getText());
		} catch (Exception e) {
			
		}
		
		double pr = calcul.dimensionCanal(q	, v);
		hLabel.setText(String.valueOf(pr));
	}

	@Override
	public void onNext() throws VetoException, FlowException {
		flowHandler.navigate(RecommandationCtrl.class);
	}


}
