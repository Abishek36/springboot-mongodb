package com.towards.learn.springboot.mongodb.resource;


import com.towards.learn.springboot.mongodb.model.Book;
import com.towards.learn.springboot.mongodb.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    @Autowired
    private BookRepo repository;


    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {

            if(!repository.existsById(book.getId())) {
                repository.save(book);
                return "New value added with Book Id : "+ book.getId();
            }
            return  "This Book was already added ";
    }

    @GetMapping("/findAllBooks")
    public ResponseEntity <?> getBooks() {

        return ResponseEntity .ok( repository.findAll());

    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id) {

            if(repository.findById(id).isPresent()){
                 Optional<Book> book= repository.findById(id);
                 return new ResponseEntity<>(book, HttpStatus.OK);
             }
             return new ResponseEntity<>("No Books Available in this Id", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {

            if (repository.findById(id).isPresent()) {
                 repository.deleteById(id);
                 return new ResponseEntity<>("Record is deleted with Book Id :" +id,HttpStatus.OK);
            }
            return new ResponseEntity<>("No Record is available in this Id ", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editBook(@RequestBody Book book) {

           if (repository.findById(book.getId()).isPresent()) {
                 repository.save(book);
                 return new ResponseEntity<>(book, HttpStatus.OK);
           }
           return new ResponseEntity<>("No Record is available in this Id ", HttpStatus.NOT_FOUND);

    }
}
