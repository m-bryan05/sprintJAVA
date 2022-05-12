/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import GymConnect.GymConnect;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class HomeFrontController implements Initializable {

    @FXML
    private Button games;
    @FXML
    private Button logout;
    @FXML
    private Button profile;
    @FXML
    private Label title;
    @FXML
    private Pane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void changePage(String title, Parent node) {
        this.title.setText(title);
        content.getChildren().removeAll(content.getChildren());
        content.getChildren().add(node);
    }
    public void changePage(String state) {
        if(state == "profile"){
            title.setText("MY PROFILE");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("../Views/ProfilePage.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(state == "logout"){
            GymConnect.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginForm.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        content.getChildren().removeAll(content.getChildren());
        if(event.getSource() == profile){
            title.setText("MY PROFILE");
            try {
                FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("../Views/ProfilePage.fxml"));
                Parent profileNode = profileLoader.load();
                content.getChildren().add(profileNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        if(event.getSource() == logout){
            GymConnect.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginForm.fxml"));
                Parent root = loader.load();
                content.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
