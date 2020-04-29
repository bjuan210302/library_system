package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.book.*;
import model.computer.*;
import model.room.*;
import model.Person.*;

public class Library {

	private LocalDateTime sysTime;
	private ArrayList<Person> users;
	
	private String nextBookID;
	private String nextRoomID;
	private String nextComputerID;
	
	private Borrow firstLoan;
	private Book firstBook;
	private Computer comTree_root;
	private Room roomTree_root;
	
	public Library() {
		this.sysTime = LocalDateTime.now();
		
		this.nextBookID = "b-1";
		this.nextRoomID = "r-1";
		this.nextComputerID = "c-1";
	}
}
