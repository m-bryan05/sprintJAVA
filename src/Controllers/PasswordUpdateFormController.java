/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import GymConnect.GymConnect;
import Services.UserService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class PasswordUpdateFormController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button cancel;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private PasswordField oldPass;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMsg.setText("");
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == update) {
            UserService us = new UserService();
            if (newPass.getText().length() < 6) {
                errorMsg.setText("Password is too weak!");
            } else if (!newPass.getText().equals(confirmPass.getText())) {
                errorMsg.setText("Passwords does not match!");
            } else if (!us.login(GymConnect.loggedUser.getUsername(), oldPass.getText())) {
                errorMsg.setText("Wrong password!");
            } else {
                try {
                    us.updatePass(GymConnect.loggedUser.getUsername(), newPass.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/HomeFront.fxml"));
                    Parent homeNode = homeLoader.load();
                    ((HomeFrontController) homeLoader.getController()).changePage("profile");
                    cancel.getScene().setRoot(homeNode);
                } catch (Exception ex) {
                    Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (event.getSource() == cancel) {
            try {
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../Views/HomeFront.fxml"));
                Parent homeNode = homeLoader.load();
                ((HomeFrontController) homeLoader.getController()).changePage("profile");
                cancel.getScene().setRoot(homeNode);
            } catch (Exception ex) {
                Logger.getLogger(ProfileEditFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
