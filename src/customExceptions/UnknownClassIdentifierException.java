package customExceptions;

public class UnknownClassIdentifierException extends Exception {

	public UnknownClassIdentifierException(String classIdentifier, String Class) {
		super("Uknown class identifier: '"+ classIdentifier +"' for class " + Class);
	}
}
