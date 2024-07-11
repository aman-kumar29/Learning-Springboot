package com.amankr.firstspringproject.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "journal_entry")
@Setter
@Getter
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

    // public ObjectId getId(){
    //     return id;
    // }
    // public void setId(ObjectId id){
    //     this.id = id;
    // }

    // public String getTitle(){
    //     return this.title;
    // }
    // public void setTitle(String newTitle){
    //     this.title = newTitle;
    // }
    // public String getContent(){
    //     return this.content;
    // }
    // public void setContent(String newContent){
    //     this.content = newContent;
    // }
    // public LocalDateTime getDate(){
    //     return date;
    // }
    // public void setDate(LocalDateTime date){
    //     this.date = date;
    // }
}
