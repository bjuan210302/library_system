package ui.book;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BookRegister {

	//HEADER DECO
	@FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    
    
    //PANEL CHANGER
    @FXML
    private StackPane baseStackPane;

    //BASE PANEL STUFF
    @FXML
    private AnchorPane BasicInfoStackPane;
    @FXML
    private JFXComboBox<String> bookTypeBox;
    @FXML
    private JFXButton nextButton;
    
    public void basicBookRegWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basicBookRegPane.fxml"));
    	fxmlLoader.setController(this);
    	BorderPane basicBookRegPane = fxmlLoader.load();
    	Scene scene = new Scene(basicBookRegPane);
    	
    	bookTypeBox.getItems().addAll("Literary", "Academic");
    	bookTypeBox.getSelectionModel().select(0);
    	
    	Stage basicBookRegWindow = new Stage();
    	basicBookRegWindow.setScene(scene);
    	basicBookRegWindow.setResizable(false);
    	basicBookRegWindow.initModality(Modality.APPLICATION_MODAL);

    	basicBookRegWindow.show();
	}
    
    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
    	animateHeader();
    	changePanel();
    }
    public void animateHeader() {
    	
    	Paint aux = secondLabel.getTextFill();
    	secondLabel.setTextFill(firstLabel.getTextFill());
    	firstLabel.setTextFill(aux);
    }
    public void changePanel() throws IOException {
    	String type = bookTypeBox.getValue();
    	
    	switch(type) {
    	case "Literary":
    		loadSpecificationsBookPane("literaryBookPane.fxml");
    		break;
    	case "Academic":
    		loadSpecificationsBookPane("academicBookPane.fxml");
    		break;
    	}
    }
    
    public void loadSpecificationsBookPane(String url) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
    	fxmlLoader.setController(this);
    	AnchorPane newPane = fxmlLoader.load();
    	
    	newPane.translateXProperty().set(baseStackPane.getWidth());
    	baseStackPane.getChildren().add(newPane);
    	
    	Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(newPane.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.1), kv);
        timeline.getKeyFrames().add(kf);
        
        BasicInfoStackPane.translateXProperty().set(-baseStackPane.getWidth());
        Timeline timeline2 = new Timeline();
        KeyValue kv2 = new KeyValue(BasicInfoStackPane.translateXProperty(), -baseStackPane.getWidth(), Interpolator.EASE_OUT);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.2), kv2);
        timeline2.getKeyFrames().add(kf2);
        
        timeline.play();
        timeline2.play();
    }
}
