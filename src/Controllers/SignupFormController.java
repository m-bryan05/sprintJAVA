/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.User;
import Services.UserService;
import Utils.Validations;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class SignupFormController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
//    @FXML
//    private DatePicker birthdatePicker;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
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
        UserService us = new UserService();
        if (usernameField.getText().equals("") || passwordField.getText().equals("") || confirmPasswordField.getText().equals("") || emailField.getText().equals("")) {
            errorMsg.setText("All fields are required!");
        }else if (!Validations.isEmailValid(emailField.getText())) {
            errorMsg.setText("Email adress is not valid!");
        } 
        else if (passwordField.getText().length() < 6) {
            errorMsg.setText("Password is too weak!");
        }
        else if (!confirmPasswordField.getText().equals(passwordField.getText())) {
            errorMsg.setText("Passwords does not match!");
        }
        else if (us.isUsernameTaken(usernameField.getText())){
            errorMsg.setText("Username is already taken!");
        } else if (us.isEmailTaken(emailField.getText())){
            errorMsg.setText("Email is already taken!");
        }
        else {
            User u = new User();
            u.setEmail(emailField.getText());
            u.setUsername(usernameField.getText());
            u.setPassword(passwordField.getText());
            
            try {
                us.ajouter(u);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginForm.fxml"));
                    Parent root = loader.load();
                    usernameField.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void signInClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginForm.fxml"));
            Parent root = loader.load();
            usernameField.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
