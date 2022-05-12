/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author wassi
 */
import Entities.Exercices;
import Utils.MaConnexion;
import com.sun.mail.smtp.SMTPTransport;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ExerciceService implements IService<Exercices>{
Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Exercices ex) {
         String query = "INSERT INTO EXERCICE(id_categorie_id,nom,difficulte,description) VALUES(?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,ex.getIdCategorieFk());
            ste.setString(2,ex.getNomExercice());
            ste.setString(3,ex.getDifficulteExercice());
            ste.setString(4,ex.getDescExercice());
            ste.executeUpdate();
            System.out.println("Exercice Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
   
     public ObservableList afficher() {
         ObservableList<Exercices> exercices = FXCollections.observableArrayList();
         String query = "SELECT * FROM exercice";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
               Exercices ex = new Exercices();
               ex.setIdExercice(rs.getInt("id"));
               ex.setIdCategorieFk(rs.getInt("id_categorie_id"));
               ex.setNomExercice(rs.getString("nom"));
               ex.setDifficulteExercice(rs.getString("difficulte"));
               ex.setDescExercice(rs.getString("description"));
               exercices.add(ex); 
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return exercices;
    }

    @Override
    public void supprimer(Exercices ex) {
        String query = "DELETE FROM exercice WHERE id= " + ex.getIdExercice() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Exercice Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Exercices ex) {
         String query = "UPDATE exercice SET nom = '" + ex.getNomExercice() + "',difficulte = '" + ex.getDifficulteExercice() +
                 "',description = '" + ex.getDescExercice() + "' WHERE id = " + ex.getIdExercice() + "";       
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Exercice Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
    
   public void sendMail(String email) {
        try {    
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");        
                Session session = Session.getInstance(props,null);
                MimeMessage msg = new MimeMessage(session);        
                msg.setFrom(new InternetAddress("<gym.connect2022@gmail.com>"));
                msg.setRecipients(Message.RecipientType.TO, email);
                msg.setSubject("Keep Fit !");
                msg.setSentDate(new Date(System.currentTimeMillis()));
               String txt = "You must try this exercice ! ";
               msg.setText(txt);  
              SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
              st.connect("smtp.gmail.com",465,"gym.connect2022@gmail.com","Catalyzer1998");
              st.sendMessage(msg, msg.getAllRecipients());

        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
    
}
