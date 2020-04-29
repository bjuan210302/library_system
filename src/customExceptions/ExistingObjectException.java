package customExceptions;

public class ExistingObjectException extends Exception{
	public ExistingObjectException(String alreadyPlacedObjectID) {
		super("This object is exactly the same as the object with code: " + alreadyPlacedObjectID);
	}
}
