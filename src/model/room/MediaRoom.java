package model.room;

import model.Item;
public class MediaRoom extends Room {

	private String tvBrand;
	
	public MediaRoom(String code, int numOfChairs, int numOfPlugs, String tvBrand) {
		super(code, numOfChairs, numOfPlugs);
		this.tvBrand = tvBrand;
	}
	
	public MediaRoom(String[] args) {
		/*
		 * args are code[0], numOfChairs[1], numOfPlugs[2]
		 * Media Room: tvBrand[3]
		 */
		super(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		this.tvBrand = args[3];
	}
	
	@Override
	public boolean equals(Item t) {
		boolean equals;
		
		if (t instanceof MediaRoom) {
			equals = super.equals(t);
			
			if(equals) {
				MediaRoom mr = (MediaRoom) t;
				equals = tvBrand.equals(mr.tvBrand);
			}
		}else {
			equals = false;
		}
		
		return equals;
	}
	
}
