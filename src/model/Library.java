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
	private Room firstRoom;
	
	public Library() {
		this.sysTime = LocalDateTime.now();
		
		this.nextBookID = "b-1";
		this.nextRoomID = "r-1";
		this.nextComputerID = "c-1";
	}
	
	public ArrayList<Person> getUsers(){
		return users;
	}
	
	//USER
	public void loadUsers(String dataPath) throws IOException, UserLoaderException {
		users = InfoHandler.loadUsers(dataPath);
	}
	public void saveUsers(String dataPath) throws IOException {
		InfoHandler.saveUsers(users, dataPath);
	}
	public Person binarySearchUser(String id) {
		bubbleSortUsers();
		
		Person user = null;
	    int low = 0;
	    int high = users.size();
	    while (low <= high && user == null) {
	        int mid = (low + high) / 2;
	        if (users.get(mid).compareTo(id) < 0) {
	            low = mid + 1;
	        } else if (users.get(mid).compareTo(id) > 0) {
	            high = mid - 1;
	        } else if (users.get(mid).compareTo(id) == 0) {
	        	user = users.get(mid);
	        }
	    }
	    return user;

	}
	public void bubbleSortUsers() {
		int n = users.size();
	      Person aux = null;

	      for(int i = 0; i < n; i++) {
	         for(int j=1; j < (n-i); j++) {
	            if(users.get(j-1).compareTo(users.get(j)) > 0) {
	               aux = users.get(j-1);
	               users.set(j-1, users.get(j));
	               users.set(j, aux);
	            }
	         }
	      }
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
		
		Book alreadyPlacedBook = searchBookByTitle(bookTree_root, args[1]);
		if(alreadyPlacedBook != null && alreadyPlacedBook.equals(newBook)) {
			throw new ExistingObjectException(alreadyPlacedBook.getCode());
		}
		
		if(bookTree_root != null) addBook(bookTree_root,newBook);
		else bookTree_root = newBook;
		
		this.nextBookID = InfoHandler.advanceCode(nextBookID);
	}
	public void addBook(Book bookInTree, Book newBook) {
		if(bookInTree.compareTo(newBook) > 0) {
			if(bookInTree.getLeft() != null) {
				addBook(bookInTree.getLeft(), newBook);
			}else {
				newBook.setParent(bookInTree);
				newBook.setLeft(newBook);
			}
		}else {
			if(bookInTree.getRight() != null) {
				addBook(bookInTree.getRight(), newBook);
			}else {
				newBook.setParent(bookInTree);
				bookInTree.setRight(newBook);
			}
		}
	}
	
	public Book searchBookByTitle(Book bookInTree, String title) {
		Book found = null;
		
		if(bookInTree == null) {
		}else {
			found = null;
			
			if(bookInTree.compareTo(title) < 0) {
				found = searchBookByTitle(bookInTree.getLeft(), title);
			}else if(bookInTree.compareTo(title) > 0){
				found = searchBookByTitle(bookInTree.getRight(), title);
			}else {
				found = bookInTree;
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
		
		//Don't check whether it exist or no because for rooms you can have one or more exact same rooms (same number of chairs etc...)
		
		if(firstRoom == null) {
			firstRoom = newRoom;
		}else {
			addRoom(firstRoom, newRoom);
		}
		this.nextRoomID = InfoHandler.advanceCode(nextRoomID);
	}
	public void addRoom(Room roomInList,Room newRoom) {
		if(roomInList.getNext() == null) {
			roomInList.setNext(newRoom);
		}else {
			addRoom(roomInList.getNext(), newRoom);
		}
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
	
		if(firstComputer == null) {
			firstComputer = newComputer;
		}else {
			addComputer(firstComputer, newComputer);
		}
		this.nextComputerID = InfoHandler.advanceCode(nextComputerID);
	}
	public void addComputer(Computer comInList, Computer newComputer) {
		if(comInList.getNext() == null) {
			comInList.setNext(newComputer);
		}else {
			addComputer(comInList.getNext(), newComputer);
		}
	}
}
