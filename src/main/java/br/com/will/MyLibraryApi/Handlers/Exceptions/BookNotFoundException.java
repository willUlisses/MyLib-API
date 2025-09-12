package br.com.will.MyLibraryApi.Handlers.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
