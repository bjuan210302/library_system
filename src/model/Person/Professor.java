package model.Person;

import model.InfoHandler;

public class Professor extends Person {

	private String courses; // separate,per,commas

	public Professor(String name, String id, boolean suspended, String courses) {
		
		super(name, id, suspended);
		this.courses = courses;
	}

	public Professor(String[] args) {
		/*
		 * args[0], [1], [2] are name, id, suspended
		 * if Professor: args[3] is courses
		 */
		super(args[0], args[1], Boolean.parseBoolean(args[2]));
		this.courses = args[3];
	}
	
	public boolean equals(Person x) {
		Professor p = (Professor) x;
		boolean equals = super.equals(x);
		
		if(equals) {
			if(!courses.equals(p.courses)) equals = false;
		}
		
		return equals;
	}

	@Override
	public String getFormattedToSaveInfo() {
		String formattedCourses = courses.replace("\n", ",");
		return InfoHandler.PROFESSOR_CLASS_IDENTIFIER+"-"+name+";"+id+";"+suspended+";"+formattedCourses;
	}
}
