package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import customExceptions.UserLoaderException;
import model.Person.*;

class LibraryTest {

	private String path;
	private Library library;
	
	private void setup_correctFile() {
		path = "data/test/users_normal_test.txt";
		library = new Library();
	}
	private void setup_onlyCommentsFile() {
		path = "data/test/users_olny_comment_test.txt";
		library = new Library();
	}
	private void setup_incorrectFormatFile() {
		path = "data/test/users_unknow_format_test.txt";
		library = new Library();
	}
	private void setup_unknownClassIdentifierFile() {
		path = "data/test/users_unknow_identifier_test.txt";
		library = new Library();
	}
	private void setup_incorrectProffesorFormatFile() {
		path = "data/test/users_unknow_professor_format_test.txt";
		library = new Library();
	}
	private void setup_incorrectStudentFormatFile() {
		path = "data/test/users_unknow_student_format_test.txt";
		library = new Library();
	}
	
	@Test
	void loadUsers_correctFile() {
		setup_correctFile();
		
		Student testStudent = new Student("Juan Bustamante", "01", false, "A00362288", "IngSys", true, false);
		Professor testProfessor = new Professor("Seyerman", "02", false, "apoII,sysStructures");
		
		try {
			library.loadUsers(path);
		} catch (IOException | UserLoaderException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		}
		
		assertTrue(testStudent.equals((Student) library.getUsers().get(0)), "Unexpected first user");
		assertTrue(testProfessor.equals((Professor) library.getUsers().get(1)), "Unexpected second user");
		assertTrue(library.getUsers().size() == 2, "Unexpected list size should be 2, is " + library.getUsers().size());
	}

	@Test
	void loadUsers_onlyCommentsFile() {
		setup_onlyCommentsFile();
		
		try {
			library.loadUsers(path);
		} catch (IOException | UserLoaderException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		}
		
		assertTrue(library.getUsers().size() == 0, "Unexpected list size should be 0, is " + library.getUsers().size());
	}
	
	@Test
	void loadUsers_incorrectFormatFile() {
		setup_incorrectFormatFile();
		
		String exceptionMessage = null;
		
		try {
			library.loadUsers(path);
		} catch (IOException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		} catch (UserLoaderException e) {
			exceptionMessage = e.getMessage();
		}
		
		assertTrue(exceptionMessage.contains("LINE FORMAT ARGS"), "Unexpected exception message: " + exceptionMessage);
	}
	
	@Test
	void loadUsers_unknownClassIdentifierFile() {
		setup_unknownClassIdentifierFile();
		
		String exceptionMessage = null;
		
		try {
			library.loadUsers(path);
		} catch (IOException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		} catch (UserLoaderException e) {
			exceptionMessage = e.getMessage();
		}
		
		assertTrue(exceptionMessage.contains("UNKNOWN IDENTIFIER"), "Unexpected exception message: " + exceptionMessage);
	}
	
	@Test
	void loadUsers_incorrectProffesorFormatFile() {
		setup_incorrectProffesorFormatFile();
		
		String exceptionMessage = null;
		
		try {
			library.loadUsers(path);
		} catch (IOException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		} catch (UserLoaderException e) {
			exceptionMessage = e.getMessage();
		}
		
		assertTrue(exceptionMessage.contains("PROFESSOR FORMAT ARGS"), "Unexpected exception message: " + exceptionMessage);
	}

	@Test
	void loadUsers_incorrectStudentFormatFile() {
		setup_incorrectStudentFormatFile();
		
		String exceptionMessage = null;
		
		try {
			library.loadUsers(path);
		} catch (IOException e) {
			fail("Unexpected exception: " + e.getClass() + " Message: " + e.getMessage());
		} catch (UserLoaderException e) {
			exceptionMessage = e.getMessage();
		}
		
		assertTrue(exceptionMessage.contains("STUDENT FORMAT ARGS"), "Unexpected exception message: " + exceptionMessage);
	}
}
