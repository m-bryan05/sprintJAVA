/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

/**
 *
 * @author wassi
 */
import Utils.MaConnexion;
import Entities.Categories;
import Entities.Exercices;
import Services.CategoriesService;
import Services.ExerciceService;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
        Categories c = new Categories(16, "Wassim");
        CategoriesService cs = new CategoriesService();
        // cs.ajouter(c);
        System.out.println(cs.afficher());
       // cs.supprimer(c);
      // cs.modifier(c);
      //  System.out.println(cs.afficher());
      int idCatFk = c.getIdCategorie();

      Exercices e = new Exercices(3, "text", "text", "text", idCatFk);
      ExerciceService es = new ExerciceService();
     // es.ajouter(e);
    // es.supprimer(e);
    //es.modifier(e);
    //System.out.println(es.afficher());
    }
    
}
