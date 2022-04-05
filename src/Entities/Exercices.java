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
public class Exercices {
    private int idExercice;
    private String nomExercice;
    private String difficulteExercice;
    private String descExercice;
    private int idCategorieFk;

    public Exercices() {
    }

    public Exercices(int idExercice, String nomExercice, String difficulteExercice, String descExercice, int idCategorieFk) {
        this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.difficulteExercice = difficulteExercice;
        this.descExercice = descExercice;
        this.idCategorieFk = idCategorieFk;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getDifficulteExercice() {
        return difficulteExercice;
    }

    public void setDifficulteExercice(String difficulteExercice) {
        this.difficulteExercice = difficulteExercice;
    }

    public String getDescExercice() {
        return descExercice;
    }

    public void setDescExercice(String descExercice) {
        this.descExercice = descExercice;
    }

    public int getIdCategorieFk() {
        return idCategorieFk;
    }

    public void setIdCategorieFk(int idCategorieFk) {
        this.idCategorieFk = idCategorieFk;
    }

    @Override
    public String toString() {
        return "Exercices{" + "idExercice=" + idExercice + ", nomExercice=" + nomExercice + ", difficulteExercice=" + difficulteExercice + ", descExercice=" + descExercice + ", idCategorieFk=" + idCategorieFk + '}';
    }
  
}
