package com.towards.learn.springboot.mongodb.repository;

import com.towards.learn.springboot.mongodb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends MongoRepository<Book,Integer> {

    //@query("select * from table")
    //fucntion getData(int id){
    //}

   // Book findById(Integer id);

}
