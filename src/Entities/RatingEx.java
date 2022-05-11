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
public class RatingEx {
    
    private int idRating;
    private int idExercice;
    private int rate;

    public RatingEx() {
    }

    public RatingEx(int idRating, int idExercice, int rate) {
        this.idRating = idRating;
        this.idExercice = idExercice;
        this.rate = rate;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rating{" + "idRating=" + idRating + ", idExercice=" + idExercice + ", rate=" + rate + '}';
    }
    
    
    
}
