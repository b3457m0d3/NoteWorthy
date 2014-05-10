package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class AutoIncrementMustBeIntegerException extends RuntimeException {

	public AutoIncrementMustBeIntegerException(String column) {
		super(String.format("The column %s was marked as a @AutoIncrement field but is not a int or a long", column));
	}
	
}
