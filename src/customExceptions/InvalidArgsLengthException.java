package customExceptions;

public class InvalidArgsLengthException extends Exception {
	public InvalidArgsLengthException(String Class, int actualLength, int properLength) {
		super("Invalid args length for class " + Class + ". Is :" + actualLength + ", should be: " + properLength);
	}
}
