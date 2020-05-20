package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Library;

public class PrincipalController {

	private Library lib;
	private int currentPanel;
    private BorderPane firstPane;
    private BorderPane secondPane;
    private BorderPane thirdPane;
    
	public PrincipalController() {
		this.lib = new Library();
		this.currentPanel = 1;
	}
	//CONTROLLER
	private ItemsPaneController itemsPaneController;
	
	//ANIMATION STUFF
	static final double ANIMATION_DURATION = 0.2;
	Timeline timeline;
    Timeline timeline2;
    Timeline timeline3;
    
	//PRINCIPAL
    @FXML
    private JFXButton itemsButton;
    @FXML
    private JFXButton usersButton;
    @FXML
    private JFXButton borrowsButton;
    @FXML
    private StackPane principalPane;
    
    @FXML
    public void itemsButtonAction(ActionEvent event) {
    	timeline = moveTabTo(firstPane, 0);
    	timeline2 = moveTabTo(secondPane, principalPane.getHeight());
    	
    	switch (currentPanel) {
    	case 2:
            timeline.play();
            timeline2.play();
            thirdPane.translateYProperty().set(2*principalPane.getHeight());
    		break;
    	case 3: 
            timeline3 = moveTabTo(thirdPane, 2*principalPane.getHeight());
            timeline.play();
            timeline2.play();
            timeline3.play();
    		break;
    	}
    	
    	currentPanel = 1;
    }
    @FXML
    public void usersButtonAction(ActionEvent event) {
    	timeline = moveTabTo(secondPane, 0);
    	
    	switch (currentPanel) {
    	case 1:
            timeline2 = moveTabTo(firstPane, -principalPane.getHeight());
            thirdPane.translateYProperty().set(principalPane.getHeight());
    		break;
    	case 3:
            timeline2 = moveTabTo(thirdPane, principalPane.getHeight());
            firstPane.translateYProperty().set(-principalPane.getHeight());
    		break;
		}
    	
    	timeline.play();
        timeline2.play();
    	currentPanel = 2;
    }
    @FXML
    public void borrowsButtonAction(ActionEvent event) {
    	timeline = moveTabTo(thirdPane, 0);
    	timeline2 = moveTabTo(secondPane, -principalPane.getHeight());
    	
    	switch (currentPanel) {
    	case 1:
            timeline3 = moveTabTo(firstPane, -2*principalPane.getHeight());
            timeline.play();
            timeline2.play();
            timeline3.play();
    		break;
    	case 2:
            timeline.play();
            timeline2.play();
            firstPane.translateYProperty().set(-2*principalPane.getHeight());
    		break;
    	}
    	
    	currentPanel = 3;
    }
    public Timeline moveTabTo(Node tab, double yCoordinate) {
    	Timeline timeline = new Timeline();
    	KeyValue kv = new KeyValue(tab.translateYProperty(), yCoordinate, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(ANIMATION_DURATION), kv);
        timeline.getKeyFrames().add(kf);
        
        return timeline;
    }

    public void whenInitializing() {
    	
    	itemsPaneController = new ItemsPaneController(lib);
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemsPane.fxml"));
    	fxmlLoader.setController(itemsPaneController);
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