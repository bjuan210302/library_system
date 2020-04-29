package customExceptions;

public class UserLoaderException extends Exception {

	public UserLoaderException(String file, int lineNumber, String error) {
		super("In file: " + file + ", line number " + lineNumber + ". ERROR: " + error);
	}
}
