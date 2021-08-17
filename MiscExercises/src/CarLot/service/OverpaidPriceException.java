package CarLot.service;

public class OverpaidPriceException extends Exception {
    public OverpaidPriceException(String message){
        super(message);
    }

    public OverpaidPriceException(String message, Throwable e){
        super(message, e);
    }
}
