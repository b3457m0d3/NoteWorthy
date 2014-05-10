package android.lib.sqlite.exceptions;

@SuppressWarnings("serial")
public class NoTypeSerializerFoundException extends RuntimeException {

    public NoTypeSerializerFoundException(Class<?> type) {
        super(String.format("Could not serialize field with class %s, no TypeSerializer found.", type.getName()));
    }

}