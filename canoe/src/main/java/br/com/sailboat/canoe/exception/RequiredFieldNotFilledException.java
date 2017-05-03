package br.com.sailboat.canoe.exception;

public class RequiredFieldNotFilledException extends Exception {

    public RequiredFieldNotFilledException() {
        super();
    }

    public RequiredFieldNotFilledException(String message) {
        super(message);
    }

    public RequiredFieldNotFilledException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredFieldNotFilledException(Throwable cause) {
        super(cause);
    }

}