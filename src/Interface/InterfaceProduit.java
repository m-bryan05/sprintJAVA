/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.util.List;
import Entities.Produit;
/**
 *
 * @author user
 */
public interface InterfaceProduit {
    //CRUD
    public void createProduit(Produit p);
    public List<Produit> readProduit();
    public void deleteProduit(Produit p);
    public void modifyProduit(Produit p);
}
