package model.Person;

public class Professor extends Person {

	private String courses; // separate,per,commas

	public Professor(String name, String id, boolean suspended, String courses) {
		super(name, id, suspended);
		this.courses = courses;
	}
	
}
