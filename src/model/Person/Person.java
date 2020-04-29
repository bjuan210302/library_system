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
	
	public boolean equals(Person x) {
		boolean equals = true;
		
		if(!name.equals(x.name)) equals = false;
		else if(!id.equals(x.id)) equals = false;
		else if(suspended != x.suspended) equals = false;
		
		return equals;
	}
}
