/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.Publication;
import Entities.Topic;
import Controllers.TaskCellFactory4;

import Controllers.TaskCellFactory5;

import static Controllers.AfficherPublicationController.selectionedPub;
import static Controllers.AfficherPublicationController.stageAffichageUnique;
import static Controllers.AjouterPublicationController.stage;
import Services.CommentaireServices;
import Services.PublicationServices;
import Services.TopicServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SettingsController implements Initializable {

    @FXML
    private ListView<Topic> topicReclameListView;
     static Topic selectionedTopic;
    static Stage stageAffichageUnique;
    @FXML
    private ListView<Topic> topicsView;
    @FXML
    private ImageView logoView;
    @FXML
    private ImageView addView;
    @FXML
    private ImageView statiqueView;
    @FXML
    private ImageView logoView1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
       
    }  
    
    private void showData(){
         stageAffichageUnique = new Stage();
         TopicServices tps = new TopicServices();  
        ArrayList TopicList1 = (ArrayList) tps.afficher();
        ObservableList observableTopicList1 = FXCollections.observableArrayList(TopicList1);
        topicsView.setItems(observableTopicList1);
         topicsView.setCellFactory(new TaskCellFactory4());
        
        ArrayList TopicList = (ArrayList) tps.ReclameTopicList();
        ObservableList observableTopicList = FXCollections.observableArrayList(TopicList);
        topicReclameListView.setItems(observableTopicList);
        topicReclameListView.setCellFactory(new TaskCellFactory5());
         logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
         logoView1.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
         addView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-plus-+.gif"));
         statiqueView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\bar-chart.png"));
         
         
         topicReclameListView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (topicReclameListView.getSelectionModel().getSelectedItem() != null) {
                    selectionedTopic = topicReclameListView.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/RepondreReclameTopic1.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
         
         topicsView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (topicsView.getSelectionModel().getSelectedItem() != null) {
                    selectionedTopic = topicsView.getSelectionModel().getSelectedItem();
                    
                    Topic p = new Topic();
                    TopicServices ps = new TopicServices();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Do you really want to delete this topic?");
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        ps.delete(selectionedTopic.getId());
                        
                        
                    }
                   
                    showData();
                    
                    

                    

                  

                }
            }
        });
         
          statiqueView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
               
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/Chart.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            }
        });
          
          addView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
               
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/ReclameTopic.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            }
        });
        
    }
    
}
