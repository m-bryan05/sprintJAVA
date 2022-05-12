/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.Mailing;
import Entities.Topic;
import static Controllers.AjouterPublicationController.stage;
import static Controllers.SettingsController.selectionedTopic;
import Services.CommentaireServices;
import Services.PublicationServices;
import Services.TopicServices;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RepondreReclameTopicController implements Initializable {

    @FXML
    private Label NameTopic;
    @FXML
    private TextArea messageText;
    private Label sentBoolValue;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label message;
    @FXML
    private ImageView logoView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
         NameTopic.setText(SettingsController.selectionedTopic.getNom());
         email.setText(SettingsController.selectionedTopic.getEmail());
         role.setText(SettingsController.selectionedTopic.getRole());
         message.setText(SettingsController.selectionedTopic.getMessage());
          messageText.setText("Hello Mr/Mrs your topic suggestion was acceptted succesfully thnank your for your participation  ");
        // TODO
    }    

   // private void Authorize(ActionEvent event) {
       
                 
        
   // }
    
      public void sendEmail(){
       String to;   
        to =  email.getText();
        String from = "samar.mejri@esprit.tn";
        String host = "smtp.gmail.com";
        final String username = "samar.mejri@esprit.tn";
        final String password = "211JFT4238";

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(messageText.getText());
            m.setText(messageText.getText());

            //send mail

            Transport.send(m);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autorized");
            alert.setContentText("Topic autorized with success and email sent with success ");
            alert.show();
           
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }


    @FXML
    private void authorize(ActionEvent event) {
              stage = new Stage();
        try {
            Topic t = new Topic();
            TopicServices tps = new TopicServices();
            
            
            tps.Authorize(SettingsController.selectionedTopic.getId());
            sendEmail();       
           
            Parent root ;
            root = FXMLLoader.load(getClass().getResource("/GUI/settings.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RepondreReclameTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
            stage = new Stage();
        try {
            Topic p = new Topic();
            TopicServices ps = new TopicServices();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Do you really want to delete this topic?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ps.delete(SettingsController.selectionedTopic.getId());
            }
            Parent root ;
            root = FXMLLoader.load(getClass().getResource("/GUI/settings.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RepondreReclameTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    

    
}

}
