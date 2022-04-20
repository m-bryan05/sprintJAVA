/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author wassi
 */
public class Categories {
    private int idCategorie;
    private String nomCatgeorie;

    public Categories() {
    }

    public Categories(String nomCatgeorie) {
        this.nomCatgeorie = nomCatgeorie;
    }
    
    
   

    public Categories(int idCategorie, String nomCatgeorie) {
        this.idCategorie = idCategorie;
        this.nomCatgeorie = nomCatgeorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCatgeorie() {
        return nomCatgeorie;
    }

    public void setNomCatgeorie(String nomCatgeorie) {
        this.nomCatgeorie = nomCatgeorie;
    }

    @Override
    public String toString() {
        return "Categories{" + "idCategorie=" + idCategorie + ", nomCatgeorie=" + nomCatgeorie + '}';
    }
    
}
