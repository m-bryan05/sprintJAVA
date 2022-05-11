/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author user
 */
public class CategorieProduit {
    private int idc;
    private String nomc;
 private String descriptionc;

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public void setDescriptionc(String descriptionc) {
        this.descriptionc = descriptionc;
    }

    public int getIdc() {
        return idc;
    }

    public String getNomc() {
        return nomc;
    }

    public String getDescriptionc() {
        return descriptionc;
    }

    public CategorieProduit(String nomc, String descriptionc) {
        this.nomc = nomc;
        this.descriptionc = descriptionc;
    }

    public CategorieProduit(int idc, String nomc, String descriptionc) {
        this.idc = idc;
        this.nomc = nomc;
        this.descriptionc = descriptionc;
    }

    public CategorieProduit() {
    }

    
    @Override
    public String toString() {
        return "CategorieProduit{" + "ID=" + idc + ", Nom=" + nomc + ",Description="+descriptionc+'}';
    }
}
