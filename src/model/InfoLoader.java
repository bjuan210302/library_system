package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import customExceptions.InfoLoaderException;
import model.Person.*;
public class InfoLoader {

	private static final int LINE_FORMAT = 2;
	private static final int STUDENT_ARGS_FORMAT = 7;
	private static final int PROFESSOR_ARGS_FORMAT = 4;
	
	public ArrayList<Person> loadUsers(String dataPath) throws IOException, InfoLoaderException {
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
				
				if(idenAndArgs.length != LINE_FORMAT) { // If unknown formatting
					throw new InfoLoaderException(
						dataPath, lineNumber, "LINE FORMAT ARGS ("+idenAndArgs.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+LINE_FORMAT+")" 
					);
				}
				
				String identifier = idenAndArgs[0];
				String[] args = idenAndArgs[1].split(";");
				if(!identifier.equals("s") && !identifier.equals("p")) { // If the identifier is not 's' nor 'p'
					throw new InfoLoaderException(dataPath, lineNumber, "UNKNOWN IDENTIFIER ("+identifier+")");
				}
				
				if(identifier.equals("s")) {
					if(args.length != STUDENT_ARGS_FORMAT) { // If the info doesn't match Student required info
						throw new InfoLoaderException(
							dataPath, lineNumber, "STUDENT FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+STUDENT_ARGS_FORMAT+")" 
						);
					}
					toAdd = new Student(args);

				}else {
					if(args.length != PROFESSOR_ARGS_FORMAT) { // If the info doesn't match Professor required info
						throw new InfoLoaderException(
								dataPath, lineNumber, "PROFESSOR FORMAT ARGS ("+args.length+") DOES NOT MATCH REQUIRED NUMBER OF ARGS("+PROFESSOR_ARGS_FORMAT+")" 
						);
					}
					toAdd = new Professor(args);
				}
				
				users.add(toAdd);
			}
			
			line = br.readLine();
			lineNumber++;
		}
		
		return users;
	}
	
}
