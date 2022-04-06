/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import Entities.Commentaire;
import Entities.Publication;
import Services.CommentaireServices;

import Services.PublicationServices;
import Utils.MaConnexion;

/**
 *
 * @author ASUS
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
        Publication p = new Publication(16, "test","test");
        PublicationServices ps = new PublicationServices();
        ps.ajouter(p);
        System.out.println(ps.afficher());
        ps.supprimer(p);
        ps.modifier(p);
  
      int idPublication = p.getId();

      Commentaire t = new Commentaire(1, "text", "text", "text", idPublication);
      CommentaireServices ts = new CommentaireServices();
      System.out.println(ts.afficher());
      ts.ajouter(t);
      ts.modifier(t);
    
    }
    
}
