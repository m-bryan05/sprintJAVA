/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;
import Entities.Produit;

import Services.ProduitService;


import Utils.MaConnexion;
/**
 *
 * @author user
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
       Produit p = new Produit(1,16, "test","test",100,"test");
        ProduitService ps = new ProduitService();
        ps.createProduit(p);
        System.out.println(ps.readProduit());
        //ps.deleteProduit(p);
       // ps.modifyProduit(p);
    }
    
}
