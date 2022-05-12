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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
        String query = "INSERT INTO COMMENTAIRE2(nom,email,commentaire,publication_id) VALUES(?,?,?,?)";
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
            String req = "SELECT * FROM commentaire2";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString("nom"), rs.getString("email"),rs.getString("commentaire"), rs.getInt("publication_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return commentaires;
    }
    
    public List<Commentaire> ListeCommentaires(int id) {
       ArrayList<Commentaire> commentaires = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * from commentaire2 where publication_id=" + id;
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                commentaires.add(new Commentaire(rs.getInt(1), rs.getString("nom"), rs.getString("email"),rs.getString("commentaire"), rs.getInt("publication_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return commentaires;
    }
    
   
      public List<Commentaire> ListeCommentaireByPubId(int id) {
        String requete = null;
        Commentaire R = null;
        List<Commentaire> myList = new ArrayList<Commentaire>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            requete = "SELECT * from commentaire2 where publication_id=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                R = new Commentaire();
                R.setId(rs.getInt(1));
                R.setNom(rs.getString("nom"));
                R.setEmail(rs.getString("email"));
                 R.setCommentaire(rs.getString("commentaire"));
                 R.setPublication_id(rs.getInt("publication_id"));
                myList.add(R);

            }
            return myList;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
   

    @Override
    public void supprimer(Commentaire t) {
        String query = "DELETE FROM commentaire2 WHERE id= " + t.getId();
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(int id) {
        String query = "DELETE FROM commentaire2 WHERE id= " + id;
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
          String query = "UPDATE commentaire2 SET nom = '" + t.getNom() + "',email = '" + t.getEmail() +
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
    public void update(Commentaire R,int id) {
          String query ="UPDATE commentaire2 set nom=?,email=?,commentaire=? WHERE id=?";
        try{
             PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(query);
       
                pst.setString(1, R.getNom());
                pst.setString(2, R.getEmail());
                pst.setString(3, R.getCommentaire());
              
                pst.setInt(4, id);
                pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
    

    public void getByID(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetPubById(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> Recherche() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int get_Number_Comment(int id) {
        int pub = 0 ;
        ResultSet rs = null;
        int Number_BonPlanUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM commentaire2 where publication_id=" + id;

            PreparedStatement preparedStatement = MaConnexion.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, pub);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_BonPlanUser = rs.getInt(1);
            }
            return Number_BonPlanUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_BonPlanUser;
    }

 
    
}
