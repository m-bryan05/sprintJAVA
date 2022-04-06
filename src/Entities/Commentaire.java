/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Commentaire {
    private int id;
    private String nom;
    private String email;
    private String commentaire;
    private int publication_id;

    public Commentaire() {
    }

    public Commentaire(int id, String nom, String email, String commentaire, int publication_id) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.commentaire = commentaire;
        this.publication_id = publication_id;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", commentaire=" + commentaire + ", publication_id=" + publication_id + '}';
    }
    
    
    
     
 
  
   
    
    
}
