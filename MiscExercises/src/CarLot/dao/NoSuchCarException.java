package CarLot.dao;

public class NoSuchCarException extends Exception{
    public NoSuchCarException(String message){
        super(message);
    }

    public NoSuchCarException(String message, Throwable e){
        super(message, e);
    }
}
