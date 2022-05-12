/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Blob;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ASUS 
 */
public class Publication {
     private static final AtomicInteger count = new AtomicInteger(0); 
    private int id ;
    private String title;
    private String description;
    private Blob image;
    private String type;
    private Date date_creation ;
    private int topic_id ;
    
   
    
    public Publication() {
    }


    public Publication(int id, String title, String description, Blob image,String type, Date date_creation,int topic_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
         this.type = type;
         this.date_creation= date_creation;
         this.topic_id = topic_id ; 
    }

   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", title=" + title + ", description=" + description + ", image=" + image + ", type=" + type + ", date_creation=" + date_creation + ", topic_id=" + topic_id + '}';
    }
    

   
    

   


    


    
    
}
