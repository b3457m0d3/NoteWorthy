package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class DuplicateColumnException extends RuntimeException {

	public DuplicateColumnException(String columnName) {
		super(String.format("Column %s is declared multiple times", columnName));
	}

}
