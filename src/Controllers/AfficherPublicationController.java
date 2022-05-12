/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Publication;
import Entities.Topic;
import Controllers.TaskCellFactory;


import Controllers.TaskCellFactory3;

import Controllers.TaskCellFactory6;
import Services.PublicationServices;
import Services.TopicServices;
import Utils.MaConnexion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherPublicationController implements Initializable {
    Connection cnx ; 
    public PreparedStatement st ;
    public ResultSet result ;         

 
    @FXML
    private ListView<Publication> listView;
    static Publication selectionedPub;
     static Topic selectionedTopic;
    static Stage stageAffichageUnique;
    private TextField RechercheTextField;
    private ComboBox<String> typeRecherche;
    private static ComboBox<String> typeRechercheStatus;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "title", "type", "description");
    private HBox hbox;
    @FXML
    private ListView<Publication> popularList;
    @FXML
    private ImageView logoView;
    @FXML
    private ImageView homzView;
    @FXML
    private ImageView addView;
    @FXML
    private ImageView bgView;
    @FXML
    private ImageView logo2View;
    @FXML
    private ListView<Topic> topicList;
    
   
    @FXML
    private ImageView settingIconView;
    @FXML
    private ImageView addTopicView;
    @FXML
    private ImageView chartView;
    
    
    

    /**
     * 
     * Initializes the controller class.
     */
   /* public void show () {
      
      String sql ="select id, title, description, type ,image from publication2" ;
        byte byteImg[] ;
        Blob blob ; 
      try {
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            if(result.next()){
                title.setText(result.getString("title"));
                 description.setText(result.getString("description"));
                  type.setText(result.getString("type"));
                  blob=result.getBlob("image");
                  byteImg=blob.getBytes(1,(int) blob.length() );
                  Image img = new Image(new ByteArrayInputStream(byteImg),imageLog.getFitWidth(),imageLog.getFitHeight(),true,true);
                  imageLog.setImage(img);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
       stageAffichageUnique = new Stage();
       logoView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
       homzView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-home-50.png"));
       addView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-add-64.png"));
       bgView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\bg1.png"));
       logo2View.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\logo FitHUb.png"));
       addTopicView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-add.gif"));
       settingIconView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\icons8-settings-50.png"));
       chartView.setImage(new Image("file:C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Pidev2\\src\\Images\\bar-chart.png"));

          
          
        TopicServices tps = new TopicServices();  
        ArrayList TopicList = (ArrayList) tps.afficher();
        ObservableList observableTopicList = FXCollections.observableArrayList(TopicList);
        topicList.setItems(observableTopicList);
         topicList.setCellFactory(new TaskCellFactory6());
       
       PublicationServices ps = new PublicationServices();
        ArrayList arrayList = (ArrayList) ps.afficher();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listView.setItems(observableList);
         listView.setCellFactory(new TaskCellFactory());
        
        ArrayList PopularList = (ArrayList) ps.afficherPopularNews();
        ObservableList observablePopularList = FXCollections.observableArrayList(PopularList);
        popularList.setItems(observablePopularList);
        popularList.setCellFactory(new TaskCellFactory3());
      
        
        
    
     
      
     // list() ;
      
      listView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (listView.getSelectionModel().getSelectedItem() != null) {
                    selectionedPub = listView.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPubUnique.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
      popularList.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (popularList.getSelectionModel().getSelectedItem() != null) {
                    selectionedPub = popularList.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/AfficherPubUnique.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
//       list() ;
        addView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
               
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/AjouterPublication.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            }
        });
        
        topicList.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (topicList.getSelectionModel().getSelectedItem() != null) {
                    selectionedTopic = topicList.getSelectionModel().getSelectedItem();
                     ArrayList arrayList2 = (ArrayList) ps.afficherByTopic(selectionedTopic.getId());
                     ObservableList observableList2 = FXCollections.observableArrayList(arrayList2);
                    
                    listView.setItems(observableList2);

                   

                }
            }
        });
        
         addTopicView.setOnMouseClicked((MouseEvent event2)
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
         settingIconView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
               
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/GUI/settings.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            }
        });
         
          chartView.setOnMouseClicked((MouseEvent event2)
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
       
     
  
       }


    private void list() {
         ArrayList arrayList = null;
        PublicationServices produitService = new PublicationServices();
        if (typeRechercheStatus == null) {
            typeRechercheStatus = new ComboBox<String>();
        }
        typeRechercheStatus.setOnAction(e -> list());

        if (typeRecherche.getValue().equals("type") && hbox.getChildren().contains(RechercheTextField)) {
            typeRechercheStatus.getItems().setAll("cardio", "fitness", "yoga");
            typeRechercheStatus.setValue("cardio");
            hbox.getChildren().remove(RechercheTextField);
            hbox.getChildren().add(typeRechercheStatus);
        }

        if ((typeRecherche.getValue().equals("type"))) {
            arrayList = (ArrayList) produitService.recherchePublications(typeRecherche.getValue(), typeRechercheStatus.getValue());
        } 
        else if ((RechercheTextField.getText().equals(""))) {
            arrayList = (ArrayList) produitService.afficher();
        } 
         

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listView.setItems(observableList);
    }

  
    

}    
    

