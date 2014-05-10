package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class EmptyTableException extends RuntimeException {

	public EmptyTableException(String clazz) {
		super(String.format("Class %s does not declare any fields", clazz));
	}

}
