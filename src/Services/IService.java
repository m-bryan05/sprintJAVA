/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;


/**
 *
 * @author ASUS
 */
public interface IService<T> {
    public void ajouter(T t);
    List<T> afficher();
    List<T> Recherche(); 
    void supprimer(T t);
    void modifier(T t);
    int  GetPubById(T t) ;
 
    
    
}
