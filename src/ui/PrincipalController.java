package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PrincipalController { //WARNING: THIS CLASS IS A FUCKING MESS

	//ANIMATION STUFF
	static final double ANIMATION_DURATION = 0.2;
	Timeline timeline;
	KeyValue kv;
    KeyFrame kf;
    
    Timeline timeline2;
	KeyValue kv2;
    KeyFrame kf2;
    
    Timeline timeline3;
	KeyValue kv3;
    KeyFrame kf3;
    
	//PRINCIPAL
    @FXML
    private JFXButton itemsButton;
    @FXML
    private JFXButton usersButton;
    @FXML
    private JFXButton borrowsButton;
    @FXML
    private StackPane principalPane;
    
    private int currentPanel;
    private BorderPane firstPane;
    private BorderPane secondPane;
    private BorderPane thirdPane;

    public PrincipalController() {
		this.currentPanel = 1;
	}
    
    @FXML
    public void itemsButtonAction(ActionEvent event) throws IOException {
    	switch (currentPanel) {
    	case 2:
    		timeline = new Timeline();
            kv = new KeyValue(secondPane.translateYProperty(), principalPane.getHeight(), Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(firstPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);
            
            timeline.play();
            timeline2.play();
            
            thirdPane.translateYProperty().set(2*principalPane.getHeight());
            
            currentPanel = 1;
    		break;
    	case 3:
    		timeline = new Timeline();
            kv = new KeyValue(thirdPane.translateYProperty(), 2*principalPane.getHeight(), Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(secondPane.translateYProperty(), principalPane.getHeight(), Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);
            
            timeline3 = new Timeline();
            kv3 = new KeyValue(firstPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf3 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv3);
            timeline3.getKeyFrames().add(kf3);
            
            timeline.play();
            timeline2.play();
            timeline3.play();
            
            thirdPane.translateYProperty().set(2*principalPane.getHeight());
            
    		currentPanel = 1;
    		break;
    	}
    }

    @FXML
    public void usersButtonAction(ActionEvent event) {
    	switch (currentPanel) {
    	case 1:
    		timeline = new Timeline();
            kv = new KeyValue(secondPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(firstPane.translateYProperty(), -principalPane.getHeight(), Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);
            
            timeline.play();
            timeline2.play();
            
            thirdPane.translateYProperty().set(principalPane.getHeight());
            currentPanel = 2;
    		break;
    		
    	case 3:
    		timeline = new Timeline();
            kv = new KeyValue(secondPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(thirdPane.translateYProperty(), principalPane.getHeight(), Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);

            timeline.play();
            timeline2.play();
            
            firstPane.translateYProperty().set(-principalPane.getHeight());
            currentPanel = 2;
    		break;
		}
    }

    @FXML
    public void borrowsButtonAction(ActionEvent event) {
    	switch (currentPanel) {
    	case 1:
    		timeline = new Timeline();
            kv = new KeyValue(firstPane.translateYProperty(), -2*principalPane.getHeight(), Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(secondPane.translateYProperty(), -principalPane.getHeight(), Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);
            
            timeline3 = new Timeline();
            kv3 = new KeyValue(thirdPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf3 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv3);
            timeline3.getKeyFrames().add(kf3);
            
            timeline.play();
            timeline2.play();
            timeline3.play();
            
            thirdPane.translateYProperty().set(2*principalPane.getHeight());
    		currentPanel = 3;
    		
    		break;
    	case 2:
    		timeline = new Timeline();
            kv = new KeyValue(secondPane.translateYProperty(), -principalPane.getHeight(), Interpolator.EASE_OUT);
            kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
            timeline.getKeyFrames().add(kf);
            
            timeline2 = new Timeline();
            kv2 = new KeyValue(thirdPane.translateYProperty(), 0, Interpolator.EASE_OUT);
            kf2 = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv2);
            timeline2.getKeyFrames().add(kf2);

            timeline.play();
            timeline2.play();
            
            firstPane.translateYProperty().set(-2*principalPane.getHeight());
            currentPanel = 3;
    		break;
    	}
    }
    
    public void whenInitializing() throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemsPane.fxml"));
    	fxmlLoader.setController(this);
    	firstPane = fxmlLoader.load();
    	firstPane.translateYProperty().set(0);
    	principalPane.getChildren().add(firstPane);
    	
    	fxmlLoader = new FXMLLoader(getClass().getResource("usersPane.fxml"));
    	fxmlLoader.setController(this);
    	secondPane = fxmlLoader.load();
    	secondPane.translateYProperty().set(principalPane.getHeight());
    	principalPane.getChildren().add(secondPane);
    	
    	fxmlLoader = new FXMLLoader(getClass().getResource("borrowsPane.fxml"));
    	fxmlLoader.setController(this);
    	thirdPane = fxmlLoader.load();
    	thirdPane.translateYProperty().set(2*principalPane.getHeight());
    	principalPane.getChildren().add(thirdPane);
    }
}