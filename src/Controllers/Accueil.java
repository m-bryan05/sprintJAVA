/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CategorieProduit;
import Models.Produit;
import Services.CPServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import projectjava.ProjectJava;
/**
 * FXML Controller class
 *
 * @author user
 */
public class Accueil implements Initializable {
 @FXML
    private Button produitadd;
  @FXML
    private Button produitview;
   @FXML
    private Button categorieadd;
    @FXML
    private Button categorieview;
    
     private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void switchToCategories(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/CategorieProduit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 994, 547);
        stage.setTitle("Categorie View");
        stage.setScene(scene);
        stage.show();
    }
        public void switchToProduit(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/Produit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Consultez Produit");
        stage.setScene(scene);
        stage.show();
    }
         public void switchToHome(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/AccueilP.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
          public void switchToProduitV(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/ProduitView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Consultez Nos produits");
        stage.setScene(scene);
        stage.show();
    }
           public void switchToCategorieV(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/CategorieViews.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Consultez Categories");
        stage.setScene(scene);
        stage.show();
    }
}
