package todo.common.exception;

public class PageableException extends RuntimeException{

    public PageableException(String message, Exception cause) {
        super(message, cause);
    }

    public PageableException(String message){
        super(message);
    }
}
