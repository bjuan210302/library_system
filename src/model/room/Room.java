package model.room;

import model.Item;

public abstract class Room extends Item {

	public Room(String code) {
		super(code);
		
	}
	protected int numOfChairs;
	protected int numOfPlugs;
	
}
