/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import static Controllers.AjouterPublicationController.stage;
import Services.CommentaireServices;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionCommentaireController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField comment;
    @FXML
    private ImageView logoView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
         name.setText(AfficherPubUniqueController.selectionedCom.getNom());
          email.setText(AfficherPubUniqueController.selectionedCom.getEmail());
          comment.setText(AfficherPubUniqueController.selectionedCom.getCommentaire());
         
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) {
        try {
            stage = new Stage();
            Parent root ;
            
            Commentaire t = new Commentaire();
            CommentaireServices ts = new CommentaireServices();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ts.delete(AfficherPubUniqueController.selectionedCom.getId());
                
                
            }
            root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPublication.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
          
        
        
    }

    @FXML
    private void update(ActionEvent event) {
          Commentaire t = new Commentaire();
          CommentaireServices ts = new CommentaireServices();
          t.setNom(name.getText());
          t.setEmail(email.getText());
          t.setCommentaire(comment.getText());
            
          ts.update(t,AfficherPubUniqueController.selectionedCom.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commentaire Updated successfuly");
            alert.show();
    }
    private void showData(){
          name.setText(AfficherPubUniqueController.selectionedCom.getNom());
          email.setText(AfficherPubUniqueController.selectionedCom.getEmail());
          comment.setText(AfficherPubUniqueController.selectionedCom.getCommentaire());
    }
    
}
