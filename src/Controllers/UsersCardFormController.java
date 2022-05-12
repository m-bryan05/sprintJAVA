/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class UsersCardFormController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label username;

    public void setUserImage(String url) {
        this.userImage.setImage(new Image(url));
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setTextFill(Color.web("#000000"));
    }    

    @FXML
    private void viewClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfileEditForm.fxml"));
        try {
            User user = new UserService().getUser(username.getText());
            System.out.println(user);
            Parent root = loader.load();
            ProfileEditFormController cont = loader.getController();
            cont.setAdmin(user.getRole().equals("[\"ROLE_ADMIN\"]"));
            if (user.getBirthDate()!=null)
                cont.setBirthdate(user.getBirthDate());
            cont.setCoins(user.getCoins());
            cont.setEmail(user.getEmail());
            cont.setEnabled(user.getIsEnabled() == 1);
            cont.setVerified(user.getIsVerified() == 1);
            cont.setName(user.getName());
            cont.setSecondName(user.getSecondName());
            cont.setUsername(user.getUsername());
            cont.setUserImage("https://avatars.dicebear.com/api/bottts/" + user.getUsername() + ".png");
            
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
            Parent homeRoot = homeLoader.load();
            HomeController homeCtrl = homeLoader.getController();
            homeCtrl.changePage(user.getUsername()+" - Profile",root);
            username.getScene().setRoot(homeRoot);
            
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
