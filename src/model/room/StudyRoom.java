package model.room;

import model.Item;

public class StudyRoom extends Room {

	public StudyRoom(String code) {
		super(code);
		
	}
	public static final int SIZE_S = 1;
	public static final int SIZE_M = 2;
	public static final int SIZE_L = 3;
	
	private char tableSize;
	private char whiteboardSize;
	@Override
	public boolean equals(Item t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
