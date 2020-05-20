package model.book;

import model.Item;

public class AcademicBook extends Book {

	private String coursesUsingThisBook;
	private int edition;
	
	public AcademicBook(String code, String title, String author, String publicationDate, String editor,
			int numberOfPages, String coursesUsingThisBook, int edition) {
		super(code, title, author, publicationDate, editor, numberOfPages);
		this.coursesUsingThisBook = coursesUsingThisBook;
		this.edition = edition;
	}
	
	public AcademicBook(String args[]) {
		/*
		 * args are code[0], title[1], author[2], publicationDate[3], editor[4], numberOfPages[5] 
		 * Academic Book: coursesUsingThisBook[6], edition[7]
		 */
		super(args[0], args[1], args[2], args[3], args[4], Integer.parseInt(args[5]));
		this.coursesUsingThisBook = args[6];
		this.edition = Integer.parseInt(args[7]);
	}
	
	@Override
	public boolean equals(Item t) {
		boolean equals;
		
		if(t instanceof AcademicBook) {
			equals = super.equals(t);
			
			if(equals) {
				AcademicBook ab = (AcademicBook) t;
				equals = ( (coursesUsingThisBook.equals(ab.coursesUsingThisBook)) && (edition == ab.edition) );
			}
		}else {
			equals = false;
		}
		
		return equals;
	}
}
