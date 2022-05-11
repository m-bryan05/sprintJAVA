/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Models.Produit;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface IProduitService {
    public void createProduit(Produit p);
    public List<Produit> readProduit();
    public void deleteProduit(Produit p);
    public void modifyProduit(Produit p);
    public ObservableList<Produit> afficher();
}
