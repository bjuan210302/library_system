package model.Person;

import model.InfoHandler;

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
	
	@Override
	public boolean equals(Person x) {
		Student s = (Student) x;
		boolean equals = super.equals(x);

		if(equals) {
			if(!code.equals(s.code)) equals = false;
			else if(!career.equals(s.career)) equals = false;
			else if(hasScholarship != s.hasScholarship) equals = false;
			else if(isMonitor != s.isMonitor) equals = false;
		}
		
		return equals;
	}

	@Override
	public String getFormattedToSaveInfo() {
		return InfoHandler.STUDENT_CLASS_IDENTIFIER +"-"+name+";"+id+";"+suspended+";"+code+";"+career+";"+hasScholarship+";"+isMonitor;
	}
	
}
