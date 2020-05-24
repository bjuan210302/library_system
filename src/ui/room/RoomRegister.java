package ui.room;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import customExceptions.ExistingObjectException;
import customExceptions.InvalidArgsLengthException;
import customExceptions.UnknownClassIdentifierException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Library;
import ui.notifications.Notification;

public class RoomRegister {
	
	private Library lib;
	
	public RoomRegister(Library lib) {
		this.lib = lib;
	}
	
	//BASIC PANE STUFF
	@FXML
    private JFXTextField numPlugsField;
	@FXML
    private JFXTextField numChairsField;
	@FXML
    private JFXComboBox<String> roomTypeBox;
	
    //STUDY ROOM
    @FXML
    private Label tableSizeLabel;
    @FXML
    private JFXComboBox<String> tableSizeBox;
    @FXML
    private Label whiteboardSizeLabel;
    @FXML
    private JFXComboBox<String> whiteboardSizeBox;
    
    //MEDIA ROOM
    @FXML
    private Label tvBrandFieldLabel;
    @FXML
    private JFXTextField tvBrandField;
    
    //BUTTONS
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton addRoomButton;
    
    public void roomRegWindow() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("roomRegPane.fxml"));
    	fxmlLoader.setController(this);
    	BorderPane roomRegPane = null;
		try {
			roomRegPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(roomRegPane);
    	
    	//INIT
    	roomTypeBox.getItems().addAll(Library.ROOM_IDENTIFIER_STUDY, Library.ROOM_IDENTIFIER_MEDIA);
    	roomTypeBox.setOnAction((ae) -> {
    		if(roomTypeBox.getValue().equals(Library.ROOM_IDENTIFIER_STUDY)) {
    			tableSizeLabel.setDisable(false);
    			tableSizeBox.setDisable(false);
    			whiteboardSizeLabel.setDisable(false);
    			whiteboardSizeBox.setDisable(false);
    			
    			tvBrandFieldLabel.setDisable(true);
    			tvBrandField.setDisable(true);
    		}else {
    			tableSizeLabel.setDisable(true);
    			tableSizeBox.setDisable(true);
    			whiteboardSizeLabel.setDisable(true);
    			whiteboardSizeBox.setDisable(true);
    			
    			tvBrandFieldLabel.setDisable(false);
    			tvBrandField.setDisable(false);
    		}
    	});
    	roomTypeBox.getSelectionModel().select(0);
    	
    	tableSizeBox.getItems().addAll(Library.SIZE_SMALL, Library.SIZE_MEDIUM, Library.SIZE_LARGE);
    	tableSizeBox.getSelectionModel().select(0);
    	
    	whiteboardSizeBox.getItems().addAll(Library.SIZE_SMALL, Library.SIZE_MEDIUM, Library.SIZE_LARGE);
    	whiteboardSizeBox.getSelectionModel().select(0);
    	
    	tvBrandFieldLabel.setDisable(true);
		tvBrandField.setDisable(true);
		
		RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
    	NumberValidator numberValidator = new NumberValidator();
    	
    	numPlugsField.getValidators().addAll(requiredFieldValidator, numberValidator);
    	numChairsField.getValidators().addAll(requiredFieldValidator, numberValidator);
    	tvBrandField.setValidators(requiredFieldValidator);
    	//
		
    	Stage roomRegWindow = new Stage();
    	roomRegWindow.setScene(scene);
    	roomRegWindow.setResizable(false);
    	roomRegWindow.initModality(Modality.APPLICATION_MODAL);
    	roomRegWindow.initStyle(StageStyle.UNDECORATED);
    	
    	roomRegWindow.show();
    }

    

    @FXML
    void addRoomButtonAction(ActionEvent event) {
    	
    	if (!numPlugsField.validate()) numPlugsField.requestFocus();
    	
    	else if (!numChairsField.validate()) numChairsField.requestFocus();

    	else {
    		ArrayList<String> args = new ArrayList<String>();

    		args.add(numPlugsField.getText());
    		args.add(numChairsField.getText());

    		switch(roomTypeBox.getValue()) {
    		case Library.ROOM_IDENTIFIER_STUDY:
    			args.add(tableSizeBox.getValue());
    			args.add(whiteboardSizeBox.getValue());
    			try {
					lib.addBook(roomTypeBox.getValue(), (String[])args.toArray());
					new Notification("The room was added!", "Everything went ok.", Notification.SUCCESS).show();
				} catch (UnknownClassIdentifierException | InvalidArgsLengthException | ExistingObjectException e) {
					new Notification("Somethig went wrong!", e.getMessage(), Notification.ERROR).show();
				}
    			break;
    		case Library.ROOM_IDENTIFIER_MEDIA:
    			if (!tvBrandField.validate()) tvBrandField.requestFocus();
    			else {
    				args.add(tvBrandField.getText());
    				try {
						lib.addBook(roomTypeBox.getValue(), (String[])args.toArray());
						new Notification("The room was added!", "Everything went ok.", Notification.SUCCESS).show();
					} catch (UnknownClassIdentifierException | InvalidArgsLengthException | ExistingObjectException e) {
						new Notification("Somethig went wrong!", e.getMessage(), Notification.ERROR).show();
					}
    			}break;
    		}
    		
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

