/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.Publication;
import Controllers.ListCellCommande;
import Controllers.TaskCellFactory2;
import static Controllers.AfficherPublicationController.selectionedPub;
import static Controllers.AfficherPublicationController.stageAffichageUnique;
import static Controllers.AjouterPublicationController.stage;
import Services.CommentaireServices;
import Services.PublicationServices;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;  
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherPubUniqueController implements Initializable {
     static Commentaire selectionedCom;

    @FXML
    private Label title;
    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private ListView<Commentaire> listCommentaire;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField commentaire;
    @FXML
    private ImageView imageView;
     byte byteImg[] ;
   
    @FXML
    private ImageView logoView;
    @FXML
    private Label idP;
    @FXML
    private Label idPub;
    @FXML
    private ImageView logoView2;
    @FXML
    private ImageView homeView;
    @FXML
    private ImageView addView;
    @FXML
    private ImageView view;
    @FXML
    private Label NbComents;
    @FXML
    private ImageView nameChekMark;
    @FXML
    private ImageView emailChekMark;
    String erreur ;
 
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
          logoView2.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
          homeView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-home-50.png"));
          addView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-add-64.png"));
           view.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-settings-50.png"));
                  
        showData();
    }    

    @FXML
    private void addComment(ActionEvent event) {
          Commentaire t = new Commentaire();
          CommentaireServices ts = new CommentaireServices();
          if(testSaisie()){
               t.setNom(nom.getText());
           t.setEmail(email.getText());
           t.setCommentaire(commentaire.getText());
           t.setPublication_id(AfficherPublicationController.selectionedPub.getId());
           ts.ajouter(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("comment Ajoutee Avec Succees!");
            alert.show();
            showData();
            clear ();
             PublicationServices ps = new PublicationServices();
              ps.UpdatePublication();
          }
          
           
    }
    
    
     private void showData() {
          Commentaire t = new Commentaire();
          CommentaireServices ts = new CommentaireServices();
     stageAffichageUnique = new Stage();
     title.setText(AfficherPublicationController.selectionedPub.getTitle());
        type.setText(AfficherPublicationController.selectionedPub.getType());
        description.setText(AfficherPublicationController.selectionedPub.getDescription());
     

                
          try {
                byteImg=AfficherPublicationController.selectionedPub.getImage().getBytes(1, (int) AfficherPublicationController.selectionedPub.getImage().length());
                Image img = new Image(new ByteArrayInputStream(byteImg),imageView.getFitHeight(),imageView.getFitHeight(),true,true) ;
                imageView.setImage(img);
                
                //imageView.setImage(new Image(publication.getImage())); 
            } catch (SQLException ex) {
                Logger.getLogger(ListCellCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
              
        
        

        ArrayList arrayList = (ArrayList) ts.ListeCommentaires(AfficherPublicationController.selectionedPub.getId());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listCommentaire.setItems(observableList);
        listCommentaire.setCellFactory(new TaskCellFactory2());
        
         listCommentaire.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (listCommentaire.getSelectionModel().getSelectedItem() != null) {
                    selectionedCom = listCommentaire.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/GestionCommentaire.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        
           
    }
  
     private void clear () {
         nom.setText("");
         email.setText("");
         commentaire.setText("");
         
     }

    @FXML
    private void update(ActionEvent event) {
         try {
             stage = new Stage();
             Parent root ;
             root = FXMLLoader.load(getClass().getResource("/GUI/ModifierPublication.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AfficherPubUniqueController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void delete(ActionEvent event) {
         stage = new Stage();
         Parent root ;
         try {
           
             Publication p = new Publication();
             PublicationServices ps = new PublicationServices();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                 ps.delete(AfficherPublicationController.selectionedPub.getId());
                  root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPublication.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
                 
                
           }             
            
         } catch (IOException ex) {
             Logger.getLogger(AfficherPubUniqueController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email.getText() == null) {
            return false;
        }

        if (pat.matcher(email.getText()).matches() == false) {
            emailChekMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailChekMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }
      
      private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            nameChekMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            nameChekMark.setImage(new Image("Images/erreurcheckMark.png"));
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
        
          
        

        if (!testMail() ||  !testNom() ) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(erreur);
            alert.show();
        }

        return testMail()  && testNom() ;
    }

  


    
}
