package model.computer;

import model.Item;

public class Computer extends Item {

	public Computer(String code) {
		super(code);
		
	}
	private String brand;
	private int diskCapacity;
	private int ramMemory;
	@Override
	public boolean equals(Item t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
