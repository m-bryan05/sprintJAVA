/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CategorieProduit;
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
import javafx.scene.control.ListView;
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
public class CategorieProduitController implements Initializable {

    @FXML
    private Button homebtn;
   @FXML
    private Label LbLogUser;

    @FXML
    private Button btnAjouterCategoriep;

    @FXML
    private Button btnModifierCategoriep;

    @FXML
    private Button btnSupprimerCategoriep;
@FXML
       private ListView<CategorieProduit> listc;
 

    @FXML
    private Circle idimg;
  @FXML
    private TextField DescriptionCategoriep;
    @FXML
    private TextField NomCategoriep;

    @FXML
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    
    CategorieProduit c = new CategorieProduit();
    CPServices cs = new CPServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showCategories();
    }
    
    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterCategoriep)
            ajouterCategorie();
        if (event.getSource() == btnSupprimerCategoriep)
            supprimerCategorie();
        if (event.getSource() == btnModifierCategoriep)
            modifierCategorie();
   
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        try {
            CategorieProduit ca = listc.getSelectionModel().getSelectedItem();
           NomCategoriep.setText(ca.getNomc());  
           DescriptionCategoriep.setText(ca.getDescriptionc());
        } catch (Exception e) {
            System.out.println("");
        }
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

    private void ajouterCategorie() {
       if(notEmpty()){
           try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie Ajoutee Avec Succees!");
            c.setNomc(NomCategoriep.getText());
            c.setDescriptionc(DescriptionCategoriep.getText());
            cs.createCategorieP(c);
            alert.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
       }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Veuillez remplir les champs nécessaire");
            alert.show();
       }
          showCategories();
          cleanInputs();
    }

    public void showCategories() {
        try {
            ObservableList<CategorieProduit> listCategories =  cs.readCategorieP();
            listc.setItems(listCategories);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void supprimerCategorie() {
                       c = listc.getSelectionModel().getSelectedItem();

            cs.deleteCategorieP(c);
            showCategories();
             cleanInputs();
    }

    public void modifierCategorie() {
       c = listc.getSelectionModel().getSelectedItem();
       c.setNomc(NomCategoriep.getText());
       cs.modifyCategorieP(c);
       showCategories();
       cleanInputs();
    }
    
    public boolean notEmpty(){
        if(NomCategoriep.getText().isEmpty())
            return false;
        return true;
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

   public void cleanInputs(){
      NomCategoriep.setText("");
      DescriptionCategoriep.setText("");
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
    
}
