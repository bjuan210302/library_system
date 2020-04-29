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
}
