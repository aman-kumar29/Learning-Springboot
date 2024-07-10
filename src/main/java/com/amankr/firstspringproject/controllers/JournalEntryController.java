package com.amankr.firstspringproject.controllers;

import com.amankr.firstspringproject.entity.JournalEntry;
import com.amankr.firstspringproject.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry>  getAll(){
        return journalEntryService.getAll();
    }
    
    @PostMapping
    public boolean createJounal(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }


    @GetMapping("/id/{myId}")
    public JournalEntry getJounalById(@PathVariable ObjectId myId){
        return journalEntryService.getById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public Boolean deleteJounalById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJounalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if(oldEntry != null){
            if(newEntry.getTitle() != null) oldEntry.setTitle(newEntry.getTitle());
            if(newEntry.getContent() != null) oldEntry.setContent(newEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }
}
