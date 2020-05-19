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
	
	private LocalDateTime sysTime;
	private ArrayList<Person> users;
	
	private String nextBookID;
	private String nextRoomID;
	private String nextComputerID;
	
	private Borrow firstLoan;
	private Book firstBook;
	private Book lastBook;
	private Computer comTree_root;
	private Room roomTree_root;
	
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
	
	public void addBook(String classIdentifier, String[] args) throws UnknownClassIdentifierException, InvalidArgsLengthException, ExistingObjectException {
		/*
		 * To use this method, fill the args array with info in [1 to 7], but leave empty args[0].
		 * args[0] is for item code, and thats assigned in this method.
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
		
		lastBook.setNext(newBook);
		lastBook = newBook;
		this.nextBookID = InfoHandler.advanceCode(nextBookID);
	}
	
	public Book searchBookByTitle(String title) {
		Book found = null;
		
		Book actual = firstBook;
		while(actual != null && found == null) {
			if(actual.getTitle().equals(title)) found = actual;
			actual = actual.getNext();
		}
		
		return found;
	}
}
