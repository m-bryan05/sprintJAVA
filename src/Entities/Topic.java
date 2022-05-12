/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ASUS
 */
public class Topic {
     private static final AtomicInteger count = new AtomicInteger(0);  
     private int id;
     private String nom;
     private String senderName ;
       private String email ;
     private String role ;
     private String message ;
     private Date sending_date;
   

    public Topic() {
    }

    public Topic(int id, String nom , String email, String senderNam, String role,String message,Date sending_date ) {
        this.id = id;
        this.nom = nom;
        this.senderName =senderName ;
        this.email =email ;
        this.role =role ;
        this.message =message ;
        this.sending_date =sending_date ;
         
         
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSending_date() {
        return sending_date;
    }

    public void setSending_date(Date sending_date) {
        this.sending_date = sending_date;
    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id + ", nom=" + nom + ", senderName=" + senderName + ", email=" + email + ", role=" + role + ", message=" + message + ", sending_date=" + sending_date + '}';
    }
    
    
    

 
    



  
    
    
    
}
