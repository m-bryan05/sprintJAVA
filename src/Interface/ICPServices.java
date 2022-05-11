/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Models.CategorieProduit;
import java.util.List;
/**
 *
 * @author user
 */
public interface ICPServices {
      public void createCategorieP(CategorieProduit C);
     public void modifyCategorieP(CategorieProduit C);
         public void deleteCategorieP(CategorieProduit C);
         public List<CategorieProduit> readCategorieP();
}
