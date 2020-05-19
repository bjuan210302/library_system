package ui.book;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Library;

public class BookRegister {

	private Library lib;
	
	public BookRegister(Library lib) {
		this.lib = lib;
	}
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
    private JFXDatePicker dateOfPublicationField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField editorField;
    @FXML
    private JFXTextField numPagesField;
    
    //LITERARY BOOK PANE STUFF
    @FXML
    private JFXComboBox<String> literaryBookTypeBox;
    @FXML
    private JFXTextField literaryBookGenreField;

    //ACADEMIC BOOK PANE STUFF
    @FXML
    private JFXTextArea academicBookCoursesList;
    @FXML
    private JFXTextField academicBookEditionField;
    
    //BUTTONS
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
    	
    	//
    	bookTypeBox.getItems().addAll(Library.BOOK_IDENTIFIER_LITERARY, Library.BOOK_IDENTIFIER_ACADEMIC);
    	bookTypeBox.getSelectionModel().select(0);
    	basicInfoPane = (AnchorPane) ((StackPane)basicBookRegPane.getCenter()).getChildren().get(0);
    	
    	RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
    	NumberValidator numberValidator = new NumberValidator();
    	
    	titleField.getValidators().add(requiredFieldValidator);
    	authorField.getValidators().add(requiredFieldValidator);
    	editorField.getValidators().add(requiredFieldValidator);
    	dateOfPublicationField.getValidators().add(requiredFieldValidator);
    	numPagesField.getValidators().addAll(requiredFieldValidator, numberValidator);
    	//
    	
    	Stage basicBookRegWindow = new Stage();
    	basicBookRegWindow.setScene(scene);
    	basicBookRegWindow.setResizable(false);
    	basicBookRegWindow.initModality(Modality.APPLICATION_MODAL);
    	basicBookRegWindow.initStyle(StageStyle.UNDECORATED);
    	
    	basicBookRegWindow.show();
	}
    
    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
    	if(!titleField.validate()) titleField.requestFocus();
    	
    	else if(!authorField.validate()) authorField.requestFocus();
    	
    	else if(!editorField.validate()) editorField.requestFocus();
    	
    	else if(!numPagesField.validate()) numPagesField.requestFocus();
    	
    	else if(!dateOfPublicationField.validate()) dateOfPublicationField.requestFocus();
    	
    	else {
    		animateHeader();
    		changePanel();
    	}
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
    @FXML
    public void addBookButtonAction(ActionEvent event) throws UnknownClassIdentifierException, InvalidArgsLengthException, ExistingObjectException {
    	String[] args = new String[8];
    	
    	args[1] = titleField.getText();
    	args[2] = authorField.getText();
    	args[3] = dateOfPublicationField.getValue().toString();
    	args[4] = editorField.getText();
    	args[5] = numPagesField.getText();
    	
    	switch(bookTypeBox.getValue()) {
    	case Library.BOOK_IDENTIFIER_LITERARY:
    		args[6] = literaryBookGenreField.getText();
    		args[7] = literaryBookTypeBox.getValue();
    		break;
    	case Library.BOOK_IDENTIFIER_ACADEMIC:
    		args[6] = academicBookCoursesList.getText();
    		args[7] = academicBookEditionField.getText();
    		break;
    	}
    	
    	lib.addBook(bookTypeBox.getValue(), args);
    }
    
    public void animateHeader() {
    	Paint aux = secondLabel.getTextFill();
    	secondLabel.setTextFill(firstLabel.getTextFill());
    	firstLabel.setTextFill(aux);
    }
    public void changePanel() throws IOException {
    	String type = bookTypeBox.getValue();
    	
    	switch(type) {
    	case Library.BOOK_IDENTIFIER_LITERARY:
    		loadSpecificationsBookPane("literaryBookPane.fxml", Library.BOOK_IDENTIFIER_LITERARY);
    		break;
    	case Library.BOOK_IDENTIFIER_ACADEMIC:
    		loadSpecificationsBookPane("academicBookPane.fxml", Library.BOOK_IDENTIFIER_ACADEMIC);
    		break;
    	}
    }
    
    public void loadSpecificationsBookPane(String url, String bookType) throws IOException {
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
        
        switch (bookType) {
		case Library.BOOK_IDENTIFIER_LITERARY:
			literaryBookGenreField.getValidators().add(new RequiredFieldValidator());
			literaryBookTypeBox.getItems().addAll(Library.LITERARY_BOOK_NOVEL, Library.LITERARY_BOOK_BIOGRAPHY, Library.LITERARY_BOOK_POETRY);
			literaryBookTypeBox.getSelectionModel().select(0);
			break;
			
		case Library.BOOK_IDENTIFIER_ACADEMIC:
			academicBookEditionField.getValidators().addAll(new RequiredFieldValidator(), new NumberValidator());
			break;
		}
    }
}
