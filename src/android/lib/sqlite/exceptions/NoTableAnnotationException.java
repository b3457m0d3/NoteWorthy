package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class NoTableAnnotationException extends RuntimeException {

	public NoTableAnnotationException() {
		super("Your model must be annotated with an @Table annotation");
	}

}
