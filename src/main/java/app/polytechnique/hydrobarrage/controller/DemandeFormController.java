package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;

import app.polytechnique.framework.Calcul;
import app.polytechnique.framework.Icalcul;
import app.polytechnique.hydrobarrage.domain.DataModel;
import app.polytechnique.model.Demande;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;

@ViewController("/views/demandeForm.fxml")
public class DemandeFormController extends AbstractController {

	@FXML
	private JFXTextField nField;

	@FXML
	private JFXTextField bmField;

	@FXML
	private JFXTextField poField;

	@FXML
	private JFXTextField alphaField;

	@FXML
	private JFXTextField mField;

	@PostConstruct
	public void init() {
		Demande demande = DataModel.getDemande();
		nField.setText(demande.getN()+"");
		bmField.setText(demande.getBm()+"");
		poField.setText(demande.getPo()+"");
		alphaField.setText(demande.getAlpha()+"");
		mField.setText(demande.getM()+"");
	}

	@ActionMethod("next") 
	public void onNext() throws VetoException, FlowException {
		String doubleError = "Veuillez entrer un réel => 0";
		String numberError = "Veuillez entrer un entier > 0";
		NumberValidator nValidator = new NumberValidator();
		DoubleValidator bmValidator = new DoubleValidator();
		DoubleValidator poValidator = new DoubleValidator();
		DoubleValidator alphaValidator = new DoubleValidator();
		NumberValidator mValidator = new NumberValidator();
		nValidator.setMessage(numberError);
		bmValidator.setMessage(doubleError);
		poValidator.setMessage(doubleError);
		alphaValidator.setMessage(doubleError);
		mValidator.setMessage(numberError);

		nField.getValidators().add(nValidator);
		bmField.getValidators().add(bmValidator);
		poField.getValidators().add(poValidator);
		alphaField.getValidators().add(alphaValidator);
		mField.getValidators().add(mValidator);

		boolean formIsOk = true;
		if (!nField.validate()) {
			formIsOk = false;
		}
		if (!bmField.validate()) {
			formIsOk = false;
		}
		if (!poField.validate()) {
			formIsOk = false;
		}
		if (!alphaField.validate()) {
			formIsOk = false;
		}
		if (!mField.validate()) {
			formIsOk = false;
		}
		
		int n = Integer.parseInt(nField.getText());
		int m = Integer.parseInt(mField.getText());
		
		if (n <= 0) {
			nField.setText("");
			nField.validate();
			nField.setText(n+"");
			formIsOk = false;
		}
		
		if (m <= 0) {
			mField.setText("");
			mField.validate();
			mField.setText(m+"");
			formIsOk = false;
		}
		
		if (formIsOk) {
			double bm = Double.parseDouble(bmField.getText());
			double po = Double.parseDouble(poField.getText());
			double alpha = Double.parseDouble(alphaField.getText());
			
			
			Demande demande = new Demande((long)-1, n, bm, po, alpha, m);
			
			DataModel.setDemande(demande);
			Icalcul calcul = new Calcul();
			double res = calcul.DemandeEnergetique(bm, po, alpha, m, n);
			DataModel.setDemandeEnergetique(res);
			
			System.out.println(DataModel.getDemandeEnergetique()+"");
			
			flowHandler.navigate(BesoinController.class);
			
		}



	}

}
