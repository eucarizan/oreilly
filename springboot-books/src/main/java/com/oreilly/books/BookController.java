package com.oreilly.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books;

    public BookController() {
        books = new ArrayList<>();
        books.add(new Book("Hacking with Spring Boot 2.3","Greg L. Turnquist"));
        books.add(new Book("97 Things Every Java Programmer Should Know", "Kevlin Henney and Trisha Gee"));
        books.add(new Book("Spring Boot: Up and Running","Greg L. Turnquist "));
    }

    // list
    @GetMapping
    public List<Book> list() {
        return books;
    }

    // create
    @PostMapping
    public void create(@RequestBody Book book) {
        books.add(book);
    }

    // update
    @PutMapping
    public void update(@RequestBody Map<String, String> payload) {
        String oldTitle = payload.get("oldtitle");
        String newTitle = payload.get("newtitle");

        if (books.contains(oldTitle)) {
            // books.set(books.indexOf(oldTitle), newTitle);
        }
    }
    
    // delete
    @DeleteMapping
    public void delete(@RequestParam String title) {
        if (books.contains(title)) {
            books.remove(books.indexOf(title));
        }
    }
}
