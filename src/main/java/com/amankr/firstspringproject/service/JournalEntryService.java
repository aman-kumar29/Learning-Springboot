package com.amankr.firstspringproject.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amankr.firstspringproject.entity.JournalEntry;
import com.amankr.firstspringproject.entity.User;
import com.amankr.firstspringproject.repository.JournalEntryRepository;
import com.amankr.firstspringproject.repository.UserRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.getUserByUsername(username);
        JournalEntry entry = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(entry);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    
    public void deleteById(ObjectId id, String username){
        User user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
