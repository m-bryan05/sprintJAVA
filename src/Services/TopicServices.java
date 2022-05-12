/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Topic;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class TopicServices implements IService<Topic>{
       MaConnexion instance = MaConnexion.getInstance();
       Connection cnx = instance.getCnx();
       Entities.Topic t = new Entities.Topic();
  

    @Override
    public void ajouter(Topic t) {
        String req = "INSERT INTO `topic2`(`nom`,`senderName`,`email`,`role`,`message`,`sending_date`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, t.getNom());
            st.setString(2, t.getSenderName());
            st.setString(3, t.getEmail());
             st.setString(4, t.getRole());
             st.setString(5, t.getMessage());
             java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
             st.setObject(6, date_sql);
             st.executeUpdate();
            System.out.println("Topic ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }

    @Override
    public List<Topic> afficher() {
       ArrayList<Topic> topics = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM topic2 where authorized = 1";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
             topics.add(new Topic(rs.getInt(1), rs.getString("nom"),rs.getString("senderName"),rs.getString("email"),rs.getString("role"),rs.getString("message"),rs.getDate("sending_date")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return topics;
    }
     public List<Topic> ReclameTopicList() {
       ArrayList<Topic> topics = new ArrayList();
        
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM topic2 where authorized = 0";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                   topics.add(new Topic(rs.getInt(1), rs.getString("nom"),rs.getString("email"),rs.getString("senderName"),rs.getString("role"),rs.getString("message"),rs.getDate("sending_date")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return topics;
    }

    @Override
    public void supprimer(Topic t) {
       String query = "DELETE FROM topic2 WHERE id= " + t.getId() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Topic Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(int id) {
       String query = "DELETE FROM topic2 WHERE id= "+ id ;
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Topic Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Topic t) {
        String query = "UPDATE topic SET nom = '" + t.getNom()+
               "' WHERE id = " + t.getId()+ "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Topic Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
     public void Authorize(int id) {
        String query = "UPDATE topic2 SET authorized = 1 where id= "+ id ;
             
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Topic Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public int GetPubById(Topic t) {
        int myList =0;
       

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM publication WHERE id_topic= " + t.getId() + ""; 
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                myList= rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }
     public ObservableList getNomTopics(){
        ObservableList<String> nomTpoics = FXCollections.observableArrayList();
        String query = "SELECT nom FROM topic2";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                Topic t = new Topic();
                nomTpoics.add(rs.getString("nom"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return nomTpoics;
    }
      public Integer retreiveIdCp(String s){
        int idCat = 0;
        String query = "SELECT id FROM topic2 WHERE nom = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
                idCat = (rs.getInt("id"));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idCat;
    }

    @Override
    public List<Topic> Recherche() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
