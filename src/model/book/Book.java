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
}
