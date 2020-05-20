package model.computer;

import model.Item;

public class Computer extends Item {

	private String brand;
	private int diskCapacity;
	private int ramMemory;
	
	private Computer next;
	private Computer prev;
	
	public Computer(String code, String brand, int diskCapacity, int ramMemory) {
		super(code);
		this.brand = brand;
		this.diskCapacity = diskCapacity;
		this.ramMemory = ramMemory;
	}
	public Computer(String[] args) {
		/*
		 * args are code[0], brand[1], diskCapacity[2], ramMemory[3]
		 */
		super(args[0]);
		this.brand = args[1];
		this.diskCapacity = Integer.parseInt(args[2]);
		this.ramMemory = Integer.parseInt(args[3]);
	}
	
	
	
	
	public Computer getNext() {
		return next;
	}
	public void setNext(Computer next) {
		this.next = next;
	}
	public Computer getPrev() {
		return prev;
	}
	public void setPrev(Computer prev) {
		this.prev = prev;
	}
	public String getBrand() {
		return brand;
	}
	public int getDiskCapacity() {
		return diskCapacity;
	}
	
	@Override
	public boolean equals(Item t) {
		Computer com = (Computer) t;
		return ((code.equals(com.code)) && (brand.equals(com.brand)) && (diskCapacity == com.diskCapacity) && (ramMemory == com.ramMemory));
	}
}
