package model.book;

public class LiteraryBook extends Book {

	public static final int LITERARY_BOOK_NOVEL = 1;
	public static final int LITERARY_BOOK_BIOGRAPHY  = 2;
	public static final int LITERARY_BOOK_POETRY  = 3;
	
	private String genre;
	private int type;
	
	public LiteraryBook(String code, String title, String author, String publicationDate, String editor,
			int numberOfPages, String genre, int type) {
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
		this.type = Integer.parseInt(args[7]);
	}
}
