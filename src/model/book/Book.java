package model.book;

import model.Item;

public abstract class Book extends Item {

	protected String title;
	protected String author;
	protected String publicationDate;
	protected String editor;
	protected int numberOfPages;
	
	protected Book parent;
	protected Book right;
	protected Book left;
	
	public Book(String code, String title, String author, String publicationDate, String editor, int numberOfPages) {
		super(code);
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.editor = editor;
		this.numberOfPages = numberOfPages;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Book getParent() {
		return parent;
	}
	public void setParent(Book parent) {
		this.parent = parent;
	}
	public Book getRight() {
		return right;
	}
	public void setRight(Book right) {
		this.right = right;
	}
	public Book getLeft() {
		return left;
	}
	public void setLeft(Book left) {
		this.left = left;
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

	public int compareTo(Book b) {
		int sum = title.compareTo(b.title);
		
		if (sum == 0) sum =  author.compareTo(b.author);
		if (sum == 0) sum =  editor.compareTo(b.editor);
		if (sum == 0) sum =  publicationDate.compareTo(b.publicationDate);
		
		return sum;
	}
	
	public int compareTo(String title) {
		return this.title.compareTo(title);
	}
}
