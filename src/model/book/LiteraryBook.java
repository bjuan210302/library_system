package model.book;

import model.Item;

public class LiteraryBook extends Book {

	public static final String LITERARY_BOOK_NOVEL = "Novel";
	public static final String LITERARY_BOOK_BIOGRAPHY  = "Biography";
	public static final String LITERARY_BOOK_POETRY  = "Poetry";
	
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
		LiteraryBook lb = (LiteraryBook) t;
		boolean equals = super.equals(t);
		
		if(equals) {
			if(genre != lb.genre) equals = false;
			else if(type != lb.type) equals = false;
		}
		
		return equals;
	}
}
