package exception;

public class IntegrityException extends RuntimeException{

    public static final long seriaVersionlUID = 1L;

    public IntegrityException(String msg) {
        super (msg);
    }
}
