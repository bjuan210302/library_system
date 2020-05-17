package ui;


import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import ui.book.BookRegister;

public class ItemsPaneController {
	
    @FXML
    private JFXCheckBox booksCheckBox;

    @FXML
    private JFXCheckBox studyRCheckBox;

    @FXML
    private JFXCheckBox computersCheckBox;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton buttonRegister;

    @FXML
    private ContextMenu contextMenuRegister;

    @FXML
    void buttonRegisterAction(ActionEvent event) {
    	Point mousePos = MouseInfo.getPointerInfo().getLocation();
    	contextMenuRegister.show(buttonRegister, mousePos.getX(), mousePos.getY());
    }
    
    @FXML
    void registerBook(ActionEvent event) throws IOException {
    	BookRegister bookRegister = new BookRegister();
    	bookRegister.basicBookRegWindow();
    }

    @FXML
    void registerComputer(ActionEvent event) {

    }

    @FXML
    void registerRoom(ActionEvent event) {

    }
}