package com.towards.learn.springboot.mongodb.repository;

import com.towards.learn.springboot.mongodb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book,Integer> {
}
