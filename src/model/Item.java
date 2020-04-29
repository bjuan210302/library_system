package model;

public abstract class Item {

	protected String code;

	public Item(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public abstract boolean equals(Item t);
	
}
