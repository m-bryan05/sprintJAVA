/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Topic;
import static Controllers.AjouterPublicationController.stage;
import Services.TopicServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclameTopicController implements Initializable {

    @FXML
    private TextField topicName;
    @FXML
    private TextField Email;
    @FXML
    private ComboBox<String> comobox;
     ObservableList<String> listRoles = FXCollections.observableArrayList("Admin", "Coach","Follower","Visitor");
    @FXML
    private TextField senderName;
    @FXML
    private TextField textMessage;
    @FXML
    private ImageView logoView;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView senderCheckMark;
    @FXML
    private ImageView msgCheckMark;
   String erreur;
    @FXML
    private ImageView logoView1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          comobox.setItems(listRoles);
           logoView1.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
         Topic tp = new Topic();
            TopicServices tps = new TopicServices();
         stage = new Stage();
         if (testSaisie()){
             try {
           
            
            tp.setNom(topicName.getText());
            tp.setEmail(Email.getText());
            tp.setSenderName(senderName.getText());
            tp.setRole(comobox.getValue());
            tp.setMessage(textMessage.getText());
            
            tps.ajouter(tp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("suggetion send with success");
            alert.show();
            Parent root ;
            root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPublication.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println("topic envoyer avec succes.");
        } catch (IOException ex) {
            Logger.getLogger(ReclameTopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
          
    }
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (Email.getText() == null) {
            return false;
        }

        if (pat.matcher(Email.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < topicName.getText().trim().length(); i++) {
            char ch = topicName.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && topicName.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
     private Boolean testSenderName() {
        int nbNonChar = 0;
        for (int i = 1; i < senderName.getText().trim().length(); i++) {
            char ch = senderName.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && senderName.getText().trim().length() >= 3) {
            senderCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            senderCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
     
     private Boolean testMsg() {
        int nbNonChar = 0;
        for (int i = 1; i < textMessage.getText().trim().length(); i++) {
            char ch = textMessage.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && textMessage.getText().trim().length() >= 3) {
            msgCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            msgCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
     
      private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Please verify that your email address is of the form: ***@***.** ");
        }
       
        if (!testNom()) {
            erreur = erreur + ("Please check your Topic Name: only characters and number >= 3 ");
        }
        if (!testSenderName()) {
            erreur = erreur + ("Please check your Name: only characters and number >= 3");
        }
          
        

        if (!testMail() ||  !testNom() || !testSenderName()|| !testMsg()) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(erreur);
            alert.setContentText(erreur);
            alert.show();
        }

        return testMail()  && testNom() && testSenderName();
    }
    
}
