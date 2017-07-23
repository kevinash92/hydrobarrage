package app.polytechnique.hydrobarrage.controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;

@ViewController("/views/rappels.fxml")
public class RappelsController extends AbstractController {

	@Override
	public void onNext() throws VetoException, FlowException {
		step++;
		flowHandler.navigate(EstimationController.class);
	}
	
}
