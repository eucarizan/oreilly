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
    private List<String> books;

    public BookController() {
        books = new ArrayList<>();
        books.add("Hacking with Spring Boot 2.3");
        books.add("97 Things Every Java Programmer Should Know");
        books.add("Spring Boot: Up and Running");
    }

    // list
    @GetMapping
    public List<String> list() {
        return books;
    }

    // create
    @PostMapping
    public void create(@RequestBody Map<String, String> payload) {
        books.add(payload.get("title"));
    }

    // update
    @PutMapping
    public void update(@RequestBody Map<String, String> payload) {
        String oldTitle = payload.get("oldtitle");
        String newTitle = payload.get("newtitle");

        if (books.contains(oldTitle)) {
            books.set(books.indexOf(oldTitle), newTitle);
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
