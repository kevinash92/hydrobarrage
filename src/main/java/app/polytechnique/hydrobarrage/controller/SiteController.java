package app.polytechnique.hydrobarrage.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;

import app.polytechnique.hydrobarrage.domain.DataModel;
import app.polytechnique.model.Chute;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;

@ViewController("/views/site.fxml")
public class SiteController extends AbstractController {


	@FXML
	private JFXComboBox<String> arrondissementBox;

	@FXML
	private JFXTextField quartierField;

	@ViewNode("hauteurField")
	private JFXTextField hm;

	@FXML
	private JFXComboBox<String> departmentBox;

	@FXML
	private JFXComboBox<String> regionBox;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	@ActionTrigger("next")
	private JFXButton saveBtn;


	public JFXButton getSaveBtn() {
		return saveBtn;
	}

	@PostConstruct
	public void init() {
		Chute chute = DataModel.getChute();
		if(chute != null) {
			hm.setText(chute.getHauteur()+"");
		}
	}

	@Override
	public void onNext() throws VetoException, FlowException {
		
		DoubleValidator numberValidator = new DoubleValidator();
		numberValidator.setMessage("hauteur de la chute incorrecte!");
		hm.getValidators().add(numberValidator);

		if (!hm.validate()) {
			return;
		}


		String region = regionBox.getSelectionModel().getSelectedItem();
		String department = departmentBox.getSelectionModel().getSelectedItem();
		String arrondissement = arrondissementBox.getSelectionModel().getSelectedItem();
		String quartier = quartierField.getText();
		String hauteur = hm.getText();

		Chute chute = new Chute();
		chute.setId((long) -1);
		chute.setRegion(region);
		chute.setDepartment(department);
		chute.setArrondissement(arrondissement);
		chute.setHauteur(Double.parseDouble(hauteur));
		chute.setQuartier(quartier);

		DataModel.setChute(chute);
		step++;
		flowHandler.navigate(DemandeController.class);



	}

}
