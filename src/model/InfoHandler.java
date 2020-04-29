package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import customExceptions.UserLoaderException;
import model.Person.*;
import model.book.AcademicBook;
import model.book.Book;
import model.book.LiteraryBook;
public class InfoHandler {

	private static final int LINE_ARGS_LENGTH = 2;
	private static final String PROFESSOR_CLASS_IDENTIFIER = "p";
	private static final String STUDENT_CLASS_IDENTIFIER = "s";
	private static final int STUDENT_ARGS_LENGTH = 7;
	private static final int PROFESSOR_ARGS_LENGTH = 4;
	
	
	private static final String LITERARYBOOK_CLASS_IDENTIFIER = "lb";
	private static final String ACADEMICBOOK_CLASS_IDENTIFIER = "ab";
	private static final int LITERARYBOOK_ARGS_LENGTH = 8;
	private static final int ACADEMICBOOK_ARGS_LENGTH = 8;
	
	public static ArrayList<Person> loadUsers(String dataPath) throws IOException, UserLoaderException {
		BufferedReader br = new BufferedReader(new FileReader(new File(dataPath)));
		ArrayList<Person> users = new ArrayList<Person>();
		/*
		 * A line in the file is compound like "identifier-args"
		 * being identifier a character to know if the user is a student or a professor. s or p respectively
		 * and args the info if the person separated by ';'
		 * 
		 * Also, a line will be ignored if it starts with '#', allowing comments in the file
		*/
		
		String line = br.readLine();
		while(line != null) {
			Person toAdd = null;
			
			int lineNumber = 1;
			if(!line.startsWith("#")) {
				String[] idenAndArgs = line.split("-"); 
				
				if(idenAndArgs.length != LINE_ARGS_LENGTH) { // If unknown formatted line
					throw new UserLoaderException(
						dataPath, lineNumber, "LINE FORMAT ARGS ("+idenAndArgs.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+LINE_ARGS_LENGTH+")" 
					);
				}
				
				String identifier = idenAndArgs[0];
				String[] args = idenAndArgs[1].split(";");
				if(!identifier.equals(STUDENT_CLASS_IDENTIFIER) && !identifier.equals(PROFESSOR_CLASS_IDENTIFIER)) { // If the identifier is not supported
					throw new UserLoaderException(dataPath, lineNumber, "UNKNOWN IDENTIFIER ("+identifier+")");
				}
				
				if(identifier.equals(STUDENT_CLASS_IDENTIFIER)) {
					if(args.length != STUDENT_ARGS_LENGTH) { // If the info doesn't match Student required info
						throw new UserLoaderException(
							dataPath, lineNumber, "STUDENT FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+STUDENT_ARGS_LENGTH+")" 
						);
					}
					toAdd = new Student(args);

				}else {
					if(args.length != PROFESSOR_ARGS_LENGTH) { // If the info doesn't match Professor required info
						throw new UserLoaderException(
								dataPath, lineNumber, "PROFESSOR FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+PROFESSOR_ARGS_LENGTH+")" 
						);
					}
					toAdd = new Professor(args);
				}
				
				users.add(toAdd);
			}
			
			line = br.readLine();
			lineNumber++;
		}
		
		br.close();
		return users;
	}

	public static Book createBook(String classIdentifier, String[] args) {
		Book book = null;
		
		switch(classIdentifier) {
		case LITERARYBOOK_CLASS_IDENTIFIER:
			if(args.length != LITERARYBOOK_ARGS_LENGTH) // Throw InvalidArgsLengthException
			book = new LiteraryBook(args);
			break;
		case ACADEMICBOOK_CLASS_IDENTIFIER:
			if(args.length != ACADEMICBOOK_ARGS_LENGTH)	// Throw InvalidArgsLengthException
			book = new AcademicBook(args);
			break;
		default:
			// Throw UnknownClassIdentifier exception
			break;
		}
		
		return book;
	}
}
