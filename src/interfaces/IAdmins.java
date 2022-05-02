/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Date;
import java.util.List;
import Models.Admins;
import java.sql.SQLException;
import javafx.collections.ObservableList;


/**
 *
 * @author Endezoumou Bryan
 */
public interface IAdmins {
    
    //Add
    public void ajouterAdmins(Admins a);
    
    //Read
    public List<Admins> afficherAdmins();
    
    //Read2
    public List<Admins> afficherAdmins2();
    
    //Read3
    public List<Admins> afficherAdmins3(String nom);
    
    //Update
    public void modifier(Admins a);
    
    //Update without id
    public void modifier2(int id, String name, String surname, String mail, int tel, String adresse);
    
    //Delete
    public void supprimer(int id);
    
    //Delete without id
    public void supprimer2(String id);
   
    public ObservableList<Admins> getAll() throws SQLException;
    //Search
    public List<Admins> recherche_nom(String nom_rechercher);
}
