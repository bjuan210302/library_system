package model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import customExceptions.ExistingObjectException;
import customExceptions.InvalidArgsLengthException;
import customExceptions.UnknownClassIdentifierException;
import customExceptions.UserLoaderException;
import model.book.*;
import model.computer.*;
import model.room.*;
import model.Person.*;

public class Library {

	public static final String BOOK_IDENTIFIER_LITERARY = "Literary";
	public static final String BOOK_IDENTIFIER_ACADEMIC = "Academic";
	public static final String LITERARY_BOOK_NOVEL = "Novel";
	public static final String LITERARY_BOOK_BIOGRAPHY  = "Biography";
	public static final String LITERARY_BOOK_POETRY  = "Poetry";
	
	public static final String ROOM_IDENTIFIER_MEDIA = "Media";
	public static final String ROOM_IDENTIFIER_STUDY = "Study";
	public static final String SIZE_SMALL = "Small";
	public static final String SIZE_MEDIUM = "Medium";
	public static final String SIZE_LARGE = "Large";
	
	private LocalDateTime sysTime;
	private ArrayList<Person> users;
	
	private String nextBookID;
	private String nextRoomID;
	private String nextComputerID;
	
	private Borrow borrowTree_root;
	private Book bookTree_root;
	
	private Computer firstComputer;
	private Computer lastComputer;
	private Room firstRoom;
	private Room lastRoom;
	
	public Library() {
		this.sysTime = LocalDateTime.now();
		
		this.nextBookID = "b-1";
		this.nextRoomID = "r-1";
		this.nextComputerID = "c-1";
	}
	
	public ArrayList<Person> getUsers(){
		return users;
	}
	
	public void loadUsers(String dataPath) throws IOException, UserLoaderException {
		users = InfoHandler.loadUsers(dataPath);
	}
	
	//BOOK
	public void addBook(String classIdentifier, String[] args) throws UnknownClassIdentifierException, InvalidArgsLengthException, ExistingObjectException {
		/*
		 * To use this method, fill the args array with info in [1 to 7], but leave empty args[0].
		 * args[0] is for the item code, and thats assigned in this method.
		 */
		args[0] = this.nextBookID;
		Book newBook = InfoHandler.createBook(classIdentifier, args);
		
		if(newBook instanceof LiteraryBook) { 
			newBook = (LiteraryBook) newBook;
		}else {
			newBook = (AcademicBook) newBook;
		}
		
		Book alreadyPlacedBook = searchBookByTitle(args[1]);
		if(alreadyPlacedBook != null && alreadyPlacedBook.equals(newBook)) {
			throw new ExistingObjectException(alreadyPlacedBook.getCode());
		}
		
		addBook(newBook);
		this.nextBookID = InfoHandler.advanceCode(nextBookID);
	}
	public void addBook(Book newBook) {
		Book parent = bookTree_root;
		Book child = bookTree_root;

		boolean setToRight = false;
		while(child != null) {
			parent = child;
			if(parent.compareTo(newBook) < 0) {
				child = parent.getRight();
				setToRight = true;
			}else {
				child = parent.getLeft();
				setToRight = false;
			}
		}
		child = newBook;
		child.setParent(parent);

		if(bookTree_root == null) {
			bookTree_root = child;
		}else if(setToRight) {
			parent.setRight(newBook);
		}else{
			parent.setLeft(newBook);
		}
	}
	
	public Book searchBookByTitle(String title) {
		Book actual = bookTree_root;
		Book found = null;
		
		while(actual != null && found == null) {
			if(actual.compareTo(title) < 0) {
				actual = actual.getRight();
			}else if(actual.compareTo(title) > 0){
				actual = actual.getLeft();
			}else {
				found = actual;
			}
		}
		return found;
	}
	
	//ROOM
	public void addRoom(String classIdentifier, String[] args) throws UnknownClassIdentifierException, InvalidArgsLengthException {
		/*
		 * To use this method, fill the args array with info in [1 to x], but leave empty args[0].
		 * args[0] is for the item code, and thats assigned in this method.
		 */
		args[0] = this.nextRoomID;
		Room newRoom = InfoHandler.createRoom(classIdentifier, args);
		
		if(newRoom instanceof StudyRoom) { 
			newRoom = (StudyRoom) newRoom;
		}else {
			newRoom = (MediaRoom) newRoom;
		}
		
		//Don't check whether it exist or no because for rooms you can have one or more exact same rooms (same number of chairs etc...)
		
		addRoom(newRoom);
		this.nextRoomID = InfoHandler.advanceCode(nextRoomID);
	}
	public void addRoom(Room newRoom) {
		lastRoom.setNext(newRoom);
		lastRoom = newRoom;
	}
	
	//COMPUTER
	public void addComputer(String[] args) throws InvalidArgsLengthException {
		/*
		 * To use this method, fill the args array with info in [1 to 3], but leave empty args[0].
		 * args[0] is for the item code, and thats assigned in this method.
		 */
		args[0] = this.nextComputerID;
		Computer newComputer = InfoHandler.createComputer(args);
		
		//Don't check whether it exist or no because for computers you can have one or more exact same devices (same brand and specs)
	
		addComputer(newComputer);
		this.nextComputerID = InfoHandler.advanceCode(nextComputerID);
	}
	public void addComputer(Computer newComputer) {
		if(lastComputer == null) {
			firstComputer = newComputer;
			lastComputer = newComputer;
		}else {
			lastComputer.setNext(newComputer);
			lastComputer = newComputer;
		}
	}
}
