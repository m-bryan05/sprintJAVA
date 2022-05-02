/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Endezoumou Bryan
 */
public class Events {
    int idEvent;
    String titre;
    String categorie;
    Date dateDebut;
    Date dateFin;
    String description;

    public Events(int idEvent, String titre, String categorie, Date dateDebut, Date dateFin, String description) {
        this.idEvent = idEvent;
        this.titre = titre;
        this.categorie = categorie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

    public Events() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Events(String titre, String categorie, Date dateDebut, Date dateFin, String description) {
        this.titre = titre;
        this.categorie = categorie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
    }

   

    public int getIdEvent() {
        return idEvent;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
