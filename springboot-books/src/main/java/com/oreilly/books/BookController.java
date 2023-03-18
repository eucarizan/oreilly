package com.oreilly.books;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        System.out.println("BookController() called...");
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @PostMapping
    public void create(@RequestBody Book book) {
        bookService.create(book);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Book book, @PathVariable int id) {
        bookService.update(book, id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
