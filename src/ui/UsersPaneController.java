package ui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import customExceptions.UserLoaderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import model.Library;
import ui.notifications.Notification;

public class UsersPaneController {
	
	private Library lib;
	
	public UsersPaneController(Library lib) {
		this.lib = lib;
	}
	
	@FXML
    private JFXCheckBox booksCheckBox;

    @FXML
    private JFXCheckBox studyRCheckBox;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    private ContextMenu contextMenuRegister;

    @FXML
    void buttonAddAction(ActionEvent event) {
    	Point mousePos = MouseInfo.getPointerInfo().getLocation();
    	contextMenuRegister.show(buttonAdd, mousePos.getX(), mousePos.getY());
    }

    @FXML
    void loadUsers(ActionEvent event) {
    	try {
			lib.loadUsers("data/normal/users.txt");
			new Notification("Users loaded!", "Users were successfuly loaded.", Notification.SUCCESS).show();
		} catch (IOException | UserLoaderException e) {
			new Notification("Somethig went wrong!", e.getMessage(), Notification.ERROR).show();
		}
    }

    @FXML
    void saveUsers(ActionEvent event) {
    	try {
			lib.saveUsers("data/normal/users.txt");
			new Notification("Users saved!", "Users were successfuly saved.", Notification.SUCCESS).show();
		} catch (IOException e) {
			new Notification("Somethig went wrong!", e.getMessage(), Notification.ERROR).show();
		}
    }
}
