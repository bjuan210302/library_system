package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import customExceptions.InvalidArgsLengthException;
import customExceptions.UnknownClassIdentifierException;
import customExceptions.UserLoaderException;
import model.Person.*;
import model.book.AcademicBook;
import model.book.Book;
import model.book.LiteraryBook;
import model.computer.Computer;
import model.room.MediaRoom;
import model.room.Room;
import model.room.StudyRoom;
public class InfoHandler {

	private static final int LINE_ARGS_LENGTH = 2;
	private static final String PROFESSOR_CLASS_IDENTIFIER = "p";
	private static final String STUDENT_CLASS_IDENTIFIER = "s";
	private static final int STUDENT_ARGS_LENGTH = 7;
	private static final int PROFESSOR_ARGS_LENGTH = 4;
	
	private static final int LITERARYBOOK_ARGS_LENGTH = 8;
	private static final int ACADEMICBOOK_ARGS_LENGTH = 8;
	
	private static final int STUDYROOM_ARGS_LENGTH = 5;
	private static final int MEDIAROOM_ARGS_LENGTH = 4;
	
	private static final int COMPUTER_ARGS_LENGTH = 4;
	
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
					br.close();
					throw new UserLoaderException(
						dataPath, lineNumber, "LINE FORMAT ARGS ("+idenAndArgs.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+LINE_ARGS_LENGTH+")" 
					);
				}
				
				String identifier = idenAndArgs[0];
				String[] args = idenAndArgs[1].split(";");
				switch(identifier) {
				
				case STUDENT_CLASS_IDENTIFIER:
					if(args.length != STUDENT_ARGS_LENGTH) { // If the info doesn't match Student required info
						br.close();
						throw new UserLoaderException(
							dataPath, lineNumber, "STUDENT FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+STUDENT_ARGS_LENGTH+")" 
						);
					}
					toAdd = new Student(args);
					break;
					
				case PROFESSOR_CLASS_IDENTIFIER:
					if(args.length != PROFESSOR_ARGS_LENGTH) { // If the info doesn't match Professor required info
						br.close();
						throw new UserLoaderException(
								dataPath, lineNumber, "PROFESSOR FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+PROFESSOR_ARGS_LENGTH+")" 
						);
					}
					toAdd = new Professor(args);
					break;
					
				default: // If the identifier is not supported
					br.close();
					throw new UserLoaderException(dataPath, lineNumber, "UNKNOWN IDENTIFIER ("+identifier+")");
					
				}
				users.add(toAdd);
			}
			
			line = br.readLine();
			lineNumber++;
		}
		
		br.close();
		return users;
	}

	public static Book createBook(String classIdentifier, String[] args) throws UnknownClassIdentifierException, InvalidArgsLengthException {
		Book book = null;
		
		switch(classIdentifier) {
		case Library.BOOK_IDENTIFIER_LITERARY:
			if(args.length != LITERARYBOOK_ARGS_LENGTH) throw new InvalidArgsLengthException("LiteraryBook", args.length, LITERARYBOOK_ARGS_LENGTH);
			book = new LiteraryBook(args);
			break;
		case Library.BOOK_IDENTIFIER_ACADEMIC:
			if(args.length != ACADEMICBOOK_ARGS_LENGTH)	throw new InvalidArgsLengthException("AcademicBook", args.length, ACADEMICBOOK_ARGS_LENGTH);
			book = new AcademicBook(args);
			break;
		default:
				throw new UnknownClassIdentifierException(classIdentifier, "Book");
		}
		
		return book;
	}
	
	public static Room createRoom(String classIdentifier, String[] args) throws UnknownClassIdentifierException, InvalidArgsLengthException {
		Room room = null;
		
		switch(classIdentifier) {
		case Library.ROOM_IDENTIFIER_STUDY:
			if(args.length != STUDYROOM_ARGS_LENGTH) throw new InvalidArgsLengthException("StudyRoom", args.length, STUDYROOM_ARGS_LENGTH);
			room = new StudyRoom(args);
			break;
		case Library.ROOM_IDENTIFIER_MEDIA:
			if(args.length != MEDIAROOM_ARGS_LENGTH) throw new InvalidArgsLengthException("MediaRoom", args.length, MEDIAROOM_ARGS_LENGTH);
			room = new MediaRoom(args);
			break;
			default:
				throw new UnknownClassIdentifierException(classIdentifier, "Room");
		}
		
		return room;
	}
	
	public static Computer createComputer(String[] args) throws InvalidArgsLengthException {
		Computer computer = null;
		
		if(args.length != COMPUTER_ARGS_LENGTH) throw new InvalidArgsLengthException("Computer", args.length, COMPUTER_ARGS_LENGTH);
		
		return computer;
	}
	public static String advanceCode(String actualCode) {
		/*
		 * The tag must be formatted like: X-y*
		 * Being x a letter and y* ONE OR MORE numbers.
		 */
		String nextCode = "x-y";
		String[] parts = actualCode.split("-");
		// TODO throw an exception here in case the formatting is wrong but meh
		int numbers = Integer.parseInt(parts[1]);
		numbers++;
		
		nextCode.replace("x", parts[0]);
		nextCode.replace("y", String.valueOf(numbers));
		
		return nextCode;
	}
}
