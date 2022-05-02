/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Models.Events;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Endezoumou Bryan
 */
public interface IEvents {
    //Add
    public void ajouterEvents(Events e);
    
    //Read
    public List<Events> afficherEvents();
    
    //Read2
    public List<Events> afficherEvents2();
    
    //Read3
    public List<Events> afficherEvents3(String titre);
    
    //Update
    public void modifier(Events e);
    
    //Update without id
    public void modifier2(int idEvent, String titre, String categorie, Date datedebut, Date datefin);
    
    //Delete
    public void supprimer(int idEvent);
    
    //Delete without id
    public void supprimer2(String idEvent);
   
    public ObservableList<Events> getAll() throws SQLException;
    //Search
    public List<Events> recherche_titre(String titre);
    
    
}
