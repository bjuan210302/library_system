package model.book;

import model.Item;

public abstract class Book extends Item {

	protected String title;
	protected String author;
	protected String publicationDate;
	protected String editor;
	protected int numberOfPages;
	
	public Book(String code, String title, String author, String publicationDate, String editor, int numberOfPages) {
		super(code);
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.editor = editor;
		this.numberOfPages = numberOfPages;
	}
	
	@Override
	public boolean equals(Item t) {
		boolean equals = true;
		Book b = (Book) t;
		
		if(!title.equals(b.title)) equals = false;
		else if(!author.equals(b.author)) equals = false;
		else if(publicationDate != b.publicationDate) equals = false;
		else if(!editor.equals(b.editor)) equals = false;
		else if(numberOfPages != b.numberOfPages) equals = false;
		
		return equals;
	}
}
