package com.amankr.firstspringproject.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amankr.firstspringproject.entity.User;
import com.amankr.firstspringproject.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getUserByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }
    
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
