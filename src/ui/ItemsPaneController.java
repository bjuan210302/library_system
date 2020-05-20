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
import javafx.scene.paint.Paint;
import model.Library;
import ui.book.BookRegister;
import ui.computer.ComputerRegister;
import ui.room.RoomRegister;

public class ItemsPaneController {
	private Library lib;
	
	public ItemsPaneController(Library lib) {
		this.lib = lib;
	}
	
	//SEARCH AND VIEW
    @FXML
    private JFXCheckBox booksCheckBox;
    @FXML
    private JFXCheckBox studyRCheckBox;
    @FXML
    private JFXCheckBox computersCheckBox;
    @FXML
    private JFXTextField searchField;
    
  //ADD, DELETE, MORE INFO
    @FXML
    private JFXButton buttonRegister;
    @FXML
    private ContextMenu contextMenuRegister;

    @FXML
    public void buttonRegisterAction(ActionEvent event) {
    	Point mousePos = MouseInfo.getPointerInfo().getLocation();
    	contextMenuRegister.show(buttonRegister, mousePos.getX(), mousePos.getY());
    }
    
    @FXML
    void registerBook(ActionEvent event) throws IOException {
    	BookRegister bookRegister = new BookRegister(lib);
    	bookRegister.basicBookRegWindow();
    }

    @FXML
    void registerRoom(ActionEvent event) throws IOException {
    	RoomRegister roomRegister = new RoomRegister(lib);
    	roomRegister.roomRegWindow();
    }
    
    @FXML
    void registerComputer(ActionEvent event) throws IOException {
    	ComputerRegister computerRegister = new ComputerRegister(lib);
    	computerRegister.computerRegWindow();
    }
    
}