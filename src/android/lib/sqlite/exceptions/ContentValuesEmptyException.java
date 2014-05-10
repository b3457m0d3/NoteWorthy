package android.lib.sqlite.exceptions;

/**
 * Created by emilsjolander on 05/02/14.
 */
@SuppressWarnings("serial")
public class ContentValuesEmptyException extends RuntimeException {

    public ContentValuesEmptyException() {
        super("Cannot insert an empty row into the database.");
    }

}