/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Publication;
import Utils.MaConnexion;
import java.io.FileInputStream;
import java.io.InputStream;
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
public class PublicationServices implements IService<Entities.Publication> {
       MaConnexion instance = MaConnexion.getInstance();
       Connection cnx = instance.getCnx();
       Entities.Publication p = new Entities.Publication();
    
       

    @Override
    public void ajouter(Entities.Publication p) {
        String req = "INSERT INTO `publication2`(`title`, `description`,`image`,`type` ) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getTitle());
            st.setString(2, p.getDescription());
          
            st.setString(4, p.getType());
            st.executeUpdate();
            System.out.println("publication ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }

    @Override
    public List<Entities.Publication> afficher() {
         ArrayList<Entities.Publication> publications = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication2 order by date_creation asc ";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                publications.add(new Entities.Publication(rs.getInt(1), rs.getString("title"), rs.getString("description"),rs.getBlob("image"),rs.getString("type"),rs.getDate("date_creation"),rs.getInt("topic_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return publications;
  
    }
     public List<Entities.Publication> afficherByTopic(int id) {
         ArrayList<Entities.Publication> publications = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication2 where topic_id=" + id;
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                publications.add(new Entities.Publication(rs.getInt(1), rs.getString("title"), rs.getString("description"),rs.getBlob("image"),rs.getString("type"),rs.getDate("date_creation"),rs.getInt("topic_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return publications;
  
    }

    @Override
    public void supprimer(Entities.Publication p) {
       String query = "DELETE FROM publication2 WHERE id= " + p.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Publication Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
     public void delete(int id) {
       String query = "DELETE FROM publication2 WHERE id= " + id;
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Publication Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Entities.Publication p) {
       String query = "UPDATE publication2 SET title = '" + p.getTitle() +
               "' WHERE id = " + p.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Publication Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public int GetPubById(Publication p) {
      int myList =0;
       

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication2 WHERE id= " + p.getId(); 
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setDescription(rs.getString(3));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    @Override
    public List<Entities.Publication> Recherche() {
        ArrayList<Entities.Publication> myList = new ArrayList<Entities.Publication>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication2 where title='"+p.getTitle()+"'";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                myList.add(new Entities.Publication(rs.getInt(1), rs.getString("title"), rs.getString("description"),rs.getBlob("image"),rs.getString("type"),rs.getDate("date_creation"),rs.getInt("topic_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        return myList;
    }
     public List<Publication> recherchePublications(String type, String valeur) {
        List<Publication> myList;
           myList = new ArrayList<>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            switch (type) {
                case "title":
                    requete = "SELECT * from publication2 where title like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "type":
                    requete = "SELECT * from publication2 where type like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "description":
                    requete = "SELECT * from publication2 where description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "Tout":
                    requete = "SELECT * from publication2 where title like '%" + valeur + "%' or type like '%" + valeur + "%' or description like '%"+ valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                default:
                    break;
            }

            Statement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Publication R = new Publication();
                R.setId(rs.getInt(1));
                R.setTitle(rs.getString(2));
                R.setDescription(rs.getString(3));
                R.setImage(rs.getBlob(4));
                R.setType(rs.getString(5));
                R.setDate_creation(rs.getDate(6));
                R.setTopic_id(rs.getInt(7));
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

        
    }
      public void UpdatePublication() {
       String query = "UPDATE publication2 p SET nbrComments =(select count(*) from commentaire2 c where p.id=c.publication_id)"; 
             
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Publication Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
      
       public List<Entities.Publication> afficherPopularNews() {
         ArrayList<Entities.Publication> publications = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication2 ORDER BY nbrComments DESC LIMIT 3;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                publications.add(new Entities.Publication(rs.getInt(1), rs.getString("title"), rs.getString("description"),rs.getBlob("image"),rs.getString("type"),rs.getDate("date_creation"),rs.getInt("topic_id")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return publications;
  
    }


    private FileInputStream FileInputStream() {
           FileInputStream image = null;
      return image ;
    }

 
    
    
}
