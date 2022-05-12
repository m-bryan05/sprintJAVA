/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class UsersFormContentController implements Initializable {

    @FXML
    private FlowPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
        try {
            List<User> usersList = us.afficher();

            for (User u : usersList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UsersCardForm.fxml"));
                Parent root = loader.load();
                UsersCardFormController cont = loader.getController();
                cont.setUserImage("https://avatars.dicebear.com/api/bottts/" + u.getUsername() + ".png");
                cont.setUsername(u.getUsername());
                content.getChildren().add(root);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
