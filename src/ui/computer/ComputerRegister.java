package ui.computer;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import customExceptions.InvalidArgsLengthException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Library;

public class ComputerRegister {

	private Library lib;
	public ComputerRegister(Library lib) {
		this.lib = lib;
	}
	
	//FIELDS
	@FXML
	private JFXTextField brandField;
	@FXML
	private JFXTextField ramField;
	@FXML
	private JFXTextField diskField;

	//BUTTONS
	@FXML
	private JFXButton cancelButton;
	@FXML
	private JFXButton addComputerButton;

	public void computerRegWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("computerRegPane.fxml"));
    	fxmlLoader.setController(this);
    	BorderPane computerRegPane = fxmlLoader.load();
    	Scene scene = new Scene(computerRegPane);
    	
    	//INIT
    	RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
    	brandField.setValidators(requiredFieldValidator);
    	ramField.setValidators(requiredFieldValidator);
    	diskField.setValidators(requiredFieldValidator);
    	//
    	
    	Stage computerRegWindow = new Stage();
    	computerRegWindow.setScene(scene);
    	computerRegWindow.setResizable(false);
    	computerRegWindow.initModality(Modality.APPLICATION_MODAL);
    	computerRegWindow.initStyle(StageStyle.UNDECORATED);
    	
    	computerRegWindow.show();
	}

	@FXML
	void addComputerButtonAction(ActionEvent event) throws InvalidArgsLengthException {
		
		if (!brandField.validate()) brandField.requestFocus();

		else if (!ramField.validate()) ramField.requestFocus();
		
		else if (!diskField.validate()) diskField.requestFocus();

		else {
			String args[] = new String[] {
					"code",
					brandField.getText(),
					diskField.getText(),
					ramField.getText()
			};
			
			lib.addComputer(args);
		}
	}

	@FXML
	void cancelButtonAction(ActionEvent event) {
		Timeline timeline = new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(100),
                       new KeyValue (cancelButton.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);   
        timeline.setOnFinished((ae) -> ((Stage)cancelButton.getScene().getWindow()).close());
        
        Timeline timeline2 = new Timeline();
        KeyValue kv2 = new KeyValue(cancelButton.getScene().getRoot().translateXProperty(), -100, Interpolator.EASE_OUT);
        KeyFrame kf2 = new KeyFrame(Duration.millis(150), kv2);
        timeline2.getKeyFrames().add(kf2);
        
        timeline.play();
        timeline2.play();
	}
}
