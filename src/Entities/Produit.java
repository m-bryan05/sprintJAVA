/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author user
 */
public class Produit {
    //Constructors
      private int id;
    private int ref;
    private String marque;
    private String description;
    private int prix;
    private String image;

     public Produit() {
    }
    public Produit(int id, int ref, String marque, String description, int prix, String image) {
        this.id = id;
        this.ref = ref;
        this.marque = marque;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    public Produit(int ref, String marque, String description, int prix, String image) {
        this.ref = ref;
        this.marque = marque;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }
//Getters ans setters 

    public int getId() {
        return id;
    }

    public int getRef() {
        return ref;
    }

    public String getMarque() {
        return marque;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
   @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", ref=" + ref + ", marque=" + marque + ", description=" + description + ", prix=" + prix + ", image=" + image + '}';
    }
    
    
}
