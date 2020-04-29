package model.Person;

public abstract class Person {

	protected String name;
	protected String id;
	protected boolean suspended;
	
	public Person(String name, String id, boolean suspended) {
		super();
		this.name = name;
		this.id = id;
		this.suspended = suspended;
	}
	
}
