package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class NoKeysException extends RuntimeException {

	public NoKeysException() {
		super("Every model must have at least one key!");
	}

}
