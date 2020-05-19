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
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BookRegister {

	//HEADER DECO
	@FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    
    
    //PANEL CHANGER
	private final double animationDuration = 0.4;
	private final double animationDurationOpacity= 0.2;
    @FXML
    private StackPane baseStackPane;
    @FXML
    private AnchorPane basicInfoPane;
    private AnchorPane specificationsPane;
    //BASE PANEL STUFF
    @FXML
    private JFXComboBox<String> bookTypeBox;
    @FXML
    private JFXButton nextButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton backButton;
    
    public void basicBookRegWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basicBookRegPane.fxml"));
    	fxmlLoader.setController(this);
    	BorderPane basicBookRegPane = fxmlLoader.load();
    	Scene scene = new Scene(basicBookRegPane);
    	
    	bookTypeBox.getItems().addAll("Literary", "Academic");
    	bookTypeBox.getSelectionModel().select(0);
    	
    	basicInfoPane = (AnchorPane) ((StackPane)basicBookRegPane.getCenter()).getChildren().get(0);
    	
    	Stage basicBookRegWindow = new Stage();
    	basicBookRegWindow.setScene(scene);
    	basicBookRegWindow.setResizable(false);
    	basicBookRegWindow.initModality(Modality.APPLICATION_MODAL);
    	basicBookRegWindow.initStyle(StageStyle.UNDECORATED);
    	
    	basicBookRegWindow.show();
	}
    
    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
    	animateHeader();
    	changePanel();
    }
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {
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
    @FXML
    public void addBookButtonAction(ActionEvent event) {
    	
    }
    @FXML
    public void backButtonAction(ActionEvent event) {
    	specificationsPane = (AnchorPane) backButton.getParent();
    	Timeline moveSpecificationsPane = new Timeline();
        KeyValue kv = new KeyValue(specificationsPane.translateXProperty(), baseStackPane.getWidth(), Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(animationDuration), kv);
        moveSpecificationsPane.getKeyFrames().add(kf);
        
        Timeline moveBasicPane = new Timeline();
        KeyValue kv2 = new KeyValue(basicInfoPane.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(animationDuration), kv2);
        moveBasicPane.getKeyFrames().add(kf2);
        
        Timeline basicPaneOpacityAnimation = new Timeline();
        KeyFrame key = new KeyFrame(Duration.seconds(animationDurationOpacity),
                       new KeyValue (basicInfoPane.opacityProperty(), 1)); 
        basicPaneOpacityAnimation.getKeyFrames().add(key);
        
        Timeline newPaneOpacityAnimation = new Timeline();
        KeyFrame key2 = new KeyFrame(Duration.seconds(animationDurationOpacity),
                       new KeyValue (specificationsPane.opacityProperty(), 0)); 
        newPaneOpacityAnimation.getKeyFrames().add(key2);
        
        newPaneOpacityAnimation.play();
        moveSpecificationsPane.play();
        basicPaneOpacityAnimation.play();
        moveBasicPane.play();
        
        animateHeader();
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
    	specificationsPane = fxmlLoader.load();
    	
    	specificationsPane.translateXProperty().set(baseStackPane.getWidth());
    	specificationsPane.setOpacity(0);
    	baseStackPane.getChildren().add(specificationsPane);
    	
    	Timeline moveSpecificationsPane = new Timeline();
        KeyValue kv = new KeyValue(specificationsPane.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(animationDuration), kv);
        moveSpecificationsPane.getKeyFrames().add(kf);
        
        Timeline moveBasicPane = new Timeline();
        KeyValue kv2 = new KeyValue(basicInfoPane.translateXProperty(), -baseStackPane.getWidth(), Interpolator.EASE_OUT);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(animationDuration), kv2);
        moveBasicPane.getKeyFrames().add(kf2);
        
        Timeline basicPaneOpacityAnimation = new Timeline();
        KeyFrame key = new KeyFrame(Duration.seconds(animationDurationOpacity),
                       new KeyValue (basicInfoPane.opacityProperty(), 0)); 
        basicPaneOpacityAnimation.getKeyFrames().add(key);
        
        Timeline newPaneOpacityAnimation = new Timeline();
        KeyFrame key2 = new KeyFrame(Duration.seconds(animationDurationOpacity),
                       new KeyValue (specificationsPane.opacityProperty(), 1)); 
        newPaneOpacityAnimation.getKeyFrames().add(key2);
        
        moveBasicPane.play();
        newPaneOpacityAnimation.play();
        moveSpecificationsPane.play();
        basicPaneOpacityAnimation.play();
    }
}
