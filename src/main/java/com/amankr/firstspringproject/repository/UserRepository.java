package com.amankr.firstspringproject.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.amankr.firstspringproject.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUsername(String username);
}
