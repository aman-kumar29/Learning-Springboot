package com.amankr.firstspringproject.controllers;

import com.amankr.firstspringproject.entity.JournalEntry;
import com.amankr.firstspringproject.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?>  getAll(){
        try {
            List<JournalEntry> all = journalEntryService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createJounal(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(true, HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJounalById(@PathVariable ObjectId myId){
        try {
            Optional<JournalEntry> entry = journalEntryService.getById(myId);
            if(entry.isPresent())
                return new ResponseEntity<>(entry, HttpStatus.OK);
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJounalById(@PathVariable ObjectId myId){
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);    
        } catch (Exception e) {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJounalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        try {
            JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
            if(oldEntry != null){
                if(newEntry.getTitle() != null) oldEntry.setTitle(newEntry.getTitle());
                if(newEntry.getContent() != null) oldEntry.setContent(newEntry.getContent());
            }
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
