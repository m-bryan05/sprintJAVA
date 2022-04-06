/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class PublicationServices implements IService<Entities.Publication> {
     MaConnexion instance = MaConnexion.getInstance();
    Connection cnx = instance.getCnx();
    Entities.Publication p = new Entities.Publication();

    @Override
    public void ajouter(Entities.Publication p) {
        String req = "INSERT INTO `publication`(`title`, `description`) VALUES (?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getTitle());
            st.setString(2, p.getDescription());      
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
            String req = "SELECT * FROM publication";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                publications.add(new Entities.Publication(rs.getInt(1), rs.getString("title"), rs.getString("description")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return publications;
  
    }

    @Override
    public void supprimer(Entities.Publication p) {
       String query = "DELETE FROM publication WHERE id= " + p.getId() + "";
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
       String query = "UPDATE publication SET title = '" + p.getTitle() +
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
    
}
