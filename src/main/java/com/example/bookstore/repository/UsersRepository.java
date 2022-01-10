package com.example.bookstore.repository;

import com.example.bookstore.dao.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<Users, ObjectId> {
    Users findByUsername(String username);
}
