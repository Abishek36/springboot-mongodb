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
            String saved="";
            boolean exists = repository.existsById(book.getId());

            if(!exists) {
                repository.save(book);
                saved = "New value added with Book Id : "+ book.getId();
            }
            else {
                saved = "This value is already added ";
            }
             return saved;
    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {

            return repository.findAll();
    }

    @GetMapping("/getBooks/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id) {
           Optional<Book> book = repository.findById(id);

           if(book.isPresent()) {
                  Book book1 =book.get();
                  return new ResponseEntity<>(book1, HttpStatus.OK);
          }
                  return new ResponseEntity<>("No Books Available in this Id", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
           Optional<Book> isRemoved= repository.findById(id);

           if (isRemoved.isPresent()) {
               repository.deleteById(id);
                 return new ResponseEntity<>("Record is deleted with Book Id :" +id,HttpStatus.OK);
           }
           else {
                 return new ResponseEntity<>("No Record is available in this Id ", HttpStatus.NOT_FOUND);
           }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editBook(@PathVariable int id ) {
           Optional<Book> isSelected = repository.findById(id);

           if (!isSelected.isPresent()) {
               Book book2 = isSelected.get();
                                    // repository.save(book2);
                return new ResponseEntity<>(book2, HttpStatus.OK);
           }
           else{
                 return new ResponseEntity<>("No Record is available in this Id ", HttpStatus.NOT_FOUND);
           }
    }
}
