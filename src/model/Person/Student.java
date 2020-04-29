package model.Person;

public class Student extends Person {

	private String code;
	private String career;
	private boolean hasScholarship;
	private boolean isMonitor;
	
	public Student(String name, String id, boolean suspended, String code, String career, boolean hasScholarship, boolean isMonitor) {
		super(name, id, suspended);
		this.code =  code;
		this.career = career;
		this.hasScholarship = hasScholarship;
		this.isMonitor = isMonitor;
	}
	
	public Student(String[] args) {
		/*
		 * args[0], [1], [2] are name, id, suspended
		 * if Student: args are code[3], career[4], hasScholarship[5], isMonitor[6]
		 */
		super(args[0], args[1], Boolean.parseBoolean(args[2]));
		this.code =  args[3];
		this.career = args[4];
		this.hasScholarship = Boolean.parseBoolean(args[5]);
		this.isMonitor = Boolean.parseBoolean(args[6]);
	}
	
	public boolean equals(Student s) {
		boolean equals = true;
		
		if(!name.equals(s.name)) equals = false;
		else if(!id.equals(s.id)) equals = false;
		else if(suspended != s.suspended) equals = false;
		else if(!code.equals(s.code)) equals = false;
		else if(!career.equals(s.career)) equals = false;
		else if(hasScholarship != s.hasScholarship) equals = false;
		else if(isMonitor != s.isMonitor) equals = false;
		
		return equals;
	}
	
}
