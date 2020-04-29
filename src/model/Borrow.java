package model;

import java.time.LocalDateTime;

import model.Person.Person;

public class Borrow {

	private LocalDateTime initialTime;
	private float duration; // In hours
	private Person personBorrowing;
	private Item objectBorrowed;
}
