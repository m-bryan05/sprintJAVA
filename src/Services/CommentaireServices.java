/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Publication;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CommentaireServices implements IService<Commentaire> {
    Connection cnx = MaConnexion.getInstance().getCnx();
    Commentaire t = new Commentaire() ;

    @Override
    public void ajouter(Commentaire t) {
        String query = "INSERT INTO COMMENTAIRE(nom,email,commentaire,publication_id) VALUES(?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            
            ste.setString(1,t.getNom());
            ste.setString(2,t.getEmail());
            ste.setString(3,t.getCommentaire());
            ste.setInt(4,t.getPublication_id());
            ste.executeUpdate();
            System.out.println("Commentaire Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Commentaire> afficher() {
       ArrayList<Commentaire> commentaires = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM commentaire";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString("nom"), rs.getString("email"),rs.getString("commentaire"), rs.getInt("publication_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return commentaires;
    }

    @Override
    public void supprimer(Commentaire t) {
        String query = "DELETE FROM commentaire WHERE id= " + t.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Commentaire t) {
          String query = "UPDATE commentaire SET nom = '" + t.getNom() + "',email = '" + t.getEmail() +
                 "',commentaire = '" + t.getCommentaire() + "' WHERE id = " + t.getId() + "";       
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
   
    
    
}
