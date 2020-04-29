package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Person.*;
public class InfoLoader {

	private static final int LINE_FORMAT = 2;
	private static final int STUDENT_ARGS_FORMAT = 8;
	private static final int PROFESSOR_ARGS_FORMAT = 4;
	
	public ArrayList<Person> loadUsers(String dataPath) throws IOException {
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
			
			if(!line.startsWith("#")) {
				String[] idenAndArgs = line.split("-"); // If unknown formatting
				
				if(idenAndArgs.length != LINE_FORMAT) {
					// Throw exception
				}
				
				String identifier = idenAndArgs[0];
				String[] args = idenAndArgs[1].split(";");
				if(!identifier.equals("s") && !identifier.equals("p")) { // If the identifier is not 's' nor 'p'
					// Throw exception
				}
				
				if(identifier.equals("s")) {
					if(args.length != STUDENT_ARGS_FORMAT) { // If the info doesn't match Student required info
						//Throw exception
					}
					toAdd = new Student(args);

				}else {
					if(args.length != PROFESSOR_ARGS_FORMAT) { // If the info doesn't match Professor required info
						//Throw exception
					}
					toAdd = new Professor(args);
				}
				
				users.add(toAdd);
			}
			
			line = br.readLine();
		}
		
		return users;
	}
	
}
