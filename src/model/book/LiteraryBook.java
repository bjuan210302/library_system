package model.book;

import model.Item;

public class LiteraryBook extends Book {
	
	private String genre;
	private String type;
	
	public LiteraryBook(String code, String title, String author, String publicationDate, String editor,
			int numberOfPages, String genre, String type) {
		super(code, title, author, publicationDate, editor, numberOfPages);
		this.genre = genre;
		this.type = type;
	}
	
	public LiteraryBook(String[] args) {
		/*
		 * args are code[0], title[1], author[2], publicationDate[3], editor[4], numberOfPages[5] 
		 * Academic Book: gere[6], type[7]
		 */
		super(args[0], args[1], args[2], args[3], args[4], Integer.parseInt(args[5]));
		this.genre = args[6];
		this.type = args[7];
	}

	@Override
	public boolean equals(Item t) {
		boolean equals;

		if(t instanceof LiteraryBook) {
			equals = super.equals(t);

			if(equals) {
				LiteraryBook lb = (LiteraryBook) t;
				equals = ( (genre.equals(lb.genre)) && (type.equals(lb.type)) );
			}
		}else {
			equals = false;
		}
		
		return equals;
	}
}
