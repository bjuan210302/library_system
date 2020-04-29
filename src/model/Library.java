package model;

import java.time.LocalDateTime;

import model.book.*;
import model.computer.*;
import model.room.*;

public class Library {

	private LocalDateTime sysTime;
	
	private Borrow firstLoan;
	private Book firstBook;
	private Computer comTree_root;
	private Room roomTree_root;
	
}
