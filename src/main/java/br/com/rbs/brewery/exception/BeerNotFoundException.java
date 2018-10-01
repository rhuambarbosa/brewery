package br.com.rbs.brewery.exception;

public class BeerNotFoundException extends Exception {

    public BeerNotFoundException() {
        super();
    }

    public BeerNotFoundException(String message) {
        super(message);
    }
}
