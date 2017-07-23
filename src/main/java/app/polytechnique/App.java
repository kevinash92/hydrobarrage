package app.polytechnique;

import app.polytechnique.hydrobarrage.controller.LoginController;
import io.datafx.controller.flow.Flow;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	public static void main(String[] args) {
        launch(args);
    }
	
    @Override
    public void start(Stage stage) throws Exception {
    	Flow loginFlow = new Flow(LoginController.class);
        Parent parent = loginFlow.start();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
