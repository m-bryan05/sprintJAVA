/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entities.Categories;
import Services.CategoriesService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pidev.Main;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;



/**
 *
 * @author wassi
 */
public class GestionCategoriesController implements Initializable{
    
    WebView webview;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private ListView<Categories> categoriesLv;
    
    @FXML
    private Label LbLogUser;

    @FXML
    private Button btnAjouterCategorie;

    @FXML
    private Button btnModifierCategorie;

    @FXML
    private Button btnSupprimerCategorie;

    @FXML
    private TableColumn<Categories, String> colNomCategorie;

    @FXML
    private Circle idimg;

    @FXML
    private TextField tfNomCategorie;

    @FXML
    private TableView<Categories> tvCategorie;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    
    Categories c = new Categories();
    CategoriesService cs = new CategoriesService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showCategories();
    }
    
    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterCategorie)
            ajouterCategorie();
        if (event.getSource() == btnSupprimerCategorie)
            supprimerCategorie();
        if (event.getSource() == btnModifierCategorie)
            modifierCategorie();
   
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        try {
            //Categories ca = tvCategorie.getSelectionModel().getSelectedItem(); 
            Categories ca = categoriesLv.getSelectionModel().getSelectedItem();
            tfNomCategorie.setText(ca.getNomCatgeorie());  
            
        } catch (Exception e) {
            System.out.println("");
        }
    }

    private void ajouterCategorie() {
       if(notEmpty()){
           try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie Ajoutee Avec Succees!");
            c.setNomCatgeorie(tfNomCategorie.getText());
            cs.ajouter(c);
            alert.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
       }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Remplissez Vos Champs SVP!");
            alert.show();
       }
          showCategories();
          cleanInputs();
    }

    public void showCategories() {
        try {
            ObservableList<Categories> listCategories =  cs.afficher();
          //  colNomCategorie.setCellValueFactory(new PropertyValueFactory<Categories, String>("nomCatgeorie"));
          categoriesLv.getItems().clear();
              categoriesLv.getItems().addAll(listCategories);
            //tvCategorie.setItems(listCategories);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void supprimerCategorie() {
            c = categoriesLv.getSelectionModel().getSelectedItem();
            cs.supprimer(c);
            
            showCategories();
             cleanInputs();
    }

    public void modifierCategorie() {
       c = categoriesLv.getSelectionModel().getSelectedItem();
       c.setNomCatgeorie(tfNomCategorie.getText());
       cs.modifier(c);
       showCategories();
       cleanInputs();
    }
    
    public boolean notEmpty(){
        if(tfNomCategorie.getText().isEmpty())
            return false;
        return true;
    }

   public void switchToExercice(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("../Controllers/GestionExercice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1000, 547);
        stage.setResizable(false);
        stage.setTitle("Exerice Page");
        stage.setScene(scene);
        stage.show();
    }

   public void cleanInputs(){
       tfNomCategorie.setText("");
   }
   
   /*public void abdoCat(){
    webview = new WebView();
    webview.getEngine().load("https://www.youtube.com/embed/DmHwGCNkc_w");
    VBox vbox = new VBox(webview);
    Scene mscene = new Scene(vbox, 960, 600);
    stage.setScene(mscene);
    stage.show();
   }
    */
}
