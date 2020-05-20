package model.room;

import model.Item;

public abstract class Room extends Item {

	protected int numOfChairs;
	protected int numOfPlugs;
	
	private Room next;
	private Room prev;
	
	public Room(String code, int numOfChairs, int numOfPlugs) {
		super(code);
		this.numOfChairs = numOfChairs;
		this.numOfPlugs = numOfPlugs;
	}
	
	public Room getNext() {
		return next;
	}
	public void setNext(Room next) {
		this.next = next;
	}
	public Room getPrev() {
		return prev;
	}
	public void setPrev(Room prev) {
		this.prev = prev;
	}
	
	@Override
	public boolean equals(Item t) {
		Room room = (Room) t;
		return ((code.equals(room.getCode())) && (numOfChairs == room.numOfChairs) && (numOfPlugs == room.numOfPlugs));
	}
}
