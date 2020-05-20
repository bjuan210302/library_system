package model.room;

import model.Item;

public class StudyRoom extends Room {
	
	public StudyRoom(String code, int numOfChairs, int numOfPlugs, String tableSize, String whiteboardSize) {
		super(code, numOfChairs, numOfPlugs);
		this.tableSize = tableSize;
		this.whiteboardSize = whiteboardSize;
	}
	public StudyRoom(String[] args) {
		/*
		 * args are code[0], numOfChairs[1], numOfPlugs[2]
		 * Study Room: tableSize[3], whiteboardSize[4]
		 */
		super(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		this.tableSize = args[3];
		this.whiteboardSize = args[4];
	}
	
	private String tableSize;
	private String whiteboardSize;
	
	@Override
	public boolean equals(Item t) {
		boolean equals;
		
		if(t instanceof StudyRoom) {
			equals = super.equals(t);
			
			if(equals) {
				StudyRoom sr = (StudyRoom) t; 
				equals = tableSize.equals(sr.tableSize) && whiteboardSize.equals(sr.whiteboardSize);
			}
		}else {
			equals = false;
		}
		
		return equals;
	}
	
}
