/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import pidev.Main;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MAB
 */
public class LoginFormController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signUpClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/SignupForm.fxml"));
            Parent root = loader.load();
            usernameField.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginClick(ActionEvent event) {
        UserService us = new UserService();
        if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
            errorMsg.setText("All fields are required!");
        } else if (us.login(usernameField.getText(), passwordField.getText())) {
            if (Main.loggedUser.getIsEnabled() == 0) {
                errorMsg.setText("This account is banned!");
                Main.loggedUser = new User();
            } else {
                errorMsg.setText("");
                if (Main.loggedUser.getRole().equals("[\"ROLE_ADMIN\"]")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
                        Parent root = loader.load();
                        usernameField.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomeFront.fxml"));
                        Parent root = loader.load();
                        usernameField.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            errorMsg.setText("Invalid credentials.");
        }
    }

    @FXML
    private void resetPassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ResetPasswordForm.fxml"));
            Parent root = loader.load();
            usernameField.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
