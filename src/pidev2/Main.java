/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import Entities.Commentaire;
import Entities.Publication;
import Entities.Topic;
import Services.CommentaireServices;

import Services.PublicationServices;
import Services.TopicServices;
import Utils.MaConnexion;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        int id_topic = 0;
        int id =0 ;
        
     
   
       TopicServices tps = new TopicServices();
       //tps.ajouter(tp);
       System.out.println(tps.afficher());
       
       // tps.GetPubById(tp);

       Publication p = new Publication();
        PublicationServices ps = new PublicationServices();
        p.setId(33);
        ps.supprimer(p);
        
        System.out.println(ps.afficherByTopic(3));
     
     
    
        
      
        // p.setTitle("test");
         //System.out.println(ps.afficherPopularNews());
       //  System.out.println(p.getType());
        
  
      Commentaire t = new Commentaire();
      CommentaireServices ts = new CommentaireServices();
      t.setNom("200");
       t.setEmail("200");
        t.setCommentaire("200");
      
      ts.update(t, 10);

      
    //  System.out.println(ts.afficher());
    //  System.out.println(ts.ListeCommentaires(13));
      // System.out.println(ts.get_Number_Comment(12));
       String s1="javatpoint is a very bad website";  
       String bad ="bad";
        String very ="very";
         String test ="is";
       String replaceString=s1.replaceAll(bad,"****");//replaces all occurrences of "a" to "e"  
         String replaceString2=s1.replaceAll(very,"****");//replaces all occurrences of "a" to "e"  
           String replaceString3=s1.replaceAll(test,"****");//replaces all occurrences of "a" to "e"  
       

      
      
      
     //ts.modifier(t);
      
    
    }
    
}
