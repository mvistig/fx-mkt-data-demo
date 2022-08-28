package mvi.demo.fx.exception;


/**
 * Used in case of rest client exception
 */
public class PriceDataRestClientException extends Exception{

    public PriceDataRestClientException(String message) {
        super(message);
    }

    public PriceDataRestClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
