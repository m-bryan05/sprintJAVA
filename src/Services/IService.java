/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author wassi
 */
public interface IService<T> {
    void ajouter(T t);
    List afficher();
    void supprimer(T t);
    void modifier(T t);
}
