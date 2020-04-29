package customExceptions;

public class InfoLoaderException extends Exception {

	public InfoLoaderException(String file, int lineNumber, String error) {
		super("In file: " + file + ", line number " + lineNumber + ". ERROR: " + error);
	}
}
