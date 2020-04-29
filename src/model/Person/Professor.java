package model.Person;

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
		this.courses = args[4];
	}
	
	public boolean equals(Professor p) {
		boolean equals = true;
		
		if(!name.equals(p.name)) equals = false;
		else if(!id.equals(p.id)) equals = false;
		else if(suspended != p.suspended) equals = false;
		else if(!courses.equals(p.courses)) equals = false;
		
		return equals;
	}
}
