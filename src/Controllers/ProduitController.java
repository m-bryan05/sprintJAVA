/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CategorieProduit;
import Models.Produit;
import Services.CPServices;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import projectjava.ProjectJava;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProduitController implements Initializable {
ProduitService ps=new ProduitService();
  @FXML
    private Label LbLogUser;
  @FXML
    private Button homebtn;
    @FXML
    private Button btnAjouterProduit;

    @FXML
    private Button btnModifierProduit;

    @FXML
    private Button btnSupprimerProduit;

  

    @FXML
    private Label idImg;

    @FXML
    private Circle idimg;
    
    @FXML
    private TextField tfRef;

    @FXML
    private TextField tfMarque;
     @FXML
    private TextField tfPrix;
      @FXML
    private TextField tfImage;
      

    @FXML
    private TextField tfDescription;

    @FXML
    private ListView<Produit> listp;
    
    @FXML
    private TextField tfCategorie;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    

    CPServices cs = new CPServices();
    ProduitService ps2 = new ProduitService();
    String nomc;    
    int IdCat;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterProduit)
            ajouterProduit();
        if (event.getSource() == btnSupprimerProduit)
               supprimerProduit();
        if (event.getSource() == btnModifierProduit)
                modifierProduit();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
         try {
         Produit p = listp.getSelectionModel().getSelectedItem();
           Integer.parseInt(tfRef.getText());
            tfMarque.setText(p.getMarque());  
            tfDescription.setText(p.getDescription());
            Integer.parseInt(tfPrix.getText());
            tfImage.setText(p.getImage());
           
             
          //  tfNomExercice.setText(exc.getNomExercice());  
        } catch (Exception e) {
            System.out.println("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showProduit();
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

    private void ajouterProduit() {
        if (notEmpty()) {
        try {
            Produit p  = new Produit();
            p.setRef(Integer.parseInt(tfRef.getText()));
            p.setMarque(tfMarque.getText());
            p.setDescription(tfDescription.getText());
            p.setPrix(Integer.parseInt(tfPrix.getText()));
            p.setImage(tfImage.getText());
            p.setNomCat(tfCategorie.getText());
            ps.createProduit(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Exercice Ajoutee Avec Succees!");
            alert.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Remplissez Vos Champs SVP!!");
            alert.show();
        }
        cleanInputs();
       
    }

    public void showProduit() {
        try {
          ObservableList<Produit> listProduit =  ps.afficher();
            listp.setItems(listProduit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  Produit p= new Produit();
    public void supprimerProduit() {  
        
        p = listp.getSelectionModel().getSelectedItem();
        ps.deleteProduit(p);
           showProduit();
           cleanInputs();
    }

    public void modifierProduit() {
       p = listp.getSelectionModel().getSelectedItem();
       p.setRef(Integer.parseInt(tfRef.getText()));
       p.setMarque(tfMarque.getText());
       p.setDescription(tfDescription.getText());
        p.setPrix(Integer.parseInt(tfPrix.getText()));
         p.setImage(tfImage.getText());
          p.setNomCat(tfCategorie.getText());
       //exs.setIdCategorieFk();
       ps.modifyProduit(p);
        showProduit();
        cleanInputs();
    }
    
    public boolean notEmpty(){
        if(tfRef.getText().isEmpty() || 
                tfMarque.getText().isEmpty() || 
                tfDescription.getText().isEmpty() || 
                tfCategorie.getText().isEmpty()||
               tfPrix.getText().isEmpty())
            return false;
        return true;
    }

   public void switchToCategories(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/CategorieProduit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 994, 547);
        stage.setTitle("Categorie View");
        stage.setScene(scene);
        stage.show();
    }
    
   public void cleanInputs(){
       tfRef.setText("");
       tfMarque.setText("");
       tfDescription.setText("");
      tfPrix.setText("");
      tfImage.setText("");
      tfCategorie.setText("");
   }
     public void switchToCategorieV(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/CategorieViews.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Consultez Nos produits");
        stage.setScene(scene);
        stage.show();
    }
}

