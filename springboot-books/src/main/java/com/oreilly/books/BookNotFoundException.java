package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    
    public BookNotFoundException(int id) {
        super("Book not found with id: " + id);
    }
}
