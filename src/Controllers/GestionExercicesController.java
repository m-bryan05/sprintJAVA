
package Controllers;


import Entities.Categories;
import Entities.Exercices;
import Entities.RatingEx;
import Services.CategoriesService;
import Services.ExerciceService;
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
import pidev.Main;
import org.controlsfx.control.Rating;


import Services.RatingService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;


public class GestionExercicesController implements Initializable
{
    @FXML
    private ListView<Exercices> exerciceLv; 
     
     @FXML
    private Label LbLogUser;

    @FXML
    private Button btnAjouterExercice;

    @FXML
    private Button btnModifierExercice;

    @FXML
    private Button btnSupprimerExercice;
    
    @FXML
    private Button btnSendMail;

    @FXML
    private TableColumn<Exercices, String> colDescriptionExercice;

    @FXML
    private TableColumn<Exercices, String> colDifficulteExercice;

    @FXML
    private TableColumn<Exercices, String> colNomExercice;

    @FXML
    private Label idImg;

    @FXML
    private Circle idimg;
    
    @FXML
    private TextField tfDescriptionExercice;

    @FXML
    private TextField tfDifficulteExercice;

    @FXML
    private TextField tfNomExercice;

    @FXML
    private TableView<Exercices> tvExercice;
    
    @FXML
    private ComboBox<String> comboNomCategories;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    
    @FXML
    private Rating rating;
    
   
    
    

    CategoriesService cs = new CategoriesService();
    ExerciceService es = new ExerciceService();
    RatingService rs = new RatingService();
    Integer idCat,exRate;    

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterExercice)
            ajouterExercice();
        if (event.getSource() == btnSupprimerExercice)
               supprimerExercice();
        if (event.getSource() == btnModifierExercice)
                modifierExercice();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
         try {
            Exercices exc = exerciceLv.getSelectionModel().getSelectedItem();
            tfNomExercice.setText(exc.getNomExercice());  
            tfDifficulteExercice.setText(exc.getDifficulteExercice());  
            tfDescriptionExercice.setText(exc.getDescExercice());
             
          //  tfNomExercice.setText(exc.getNomExercice());  
        } catch (Exception e) {
            System.out.println("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAjouterExercice.setTextFill(Color.WHITE);
        btnModifierExercice.setTextFill(Color.WHITE);
        btnSupprimerExercice.setTextFill(Color.WHITE);
        btnSendMail.setTextFill(Color.WHITE);
        showExercices();
        ObservableList nomsCategories = FXCollections.observableArrayList(cs.getNomCategories());
        comboNomCategories.setItems(nomsCategories);
        comboNomCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             idCat = cs.retreiveIdCp(newValue);
        });
        rating.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
        public void changed(ObservableValue<? extends Number> arg0, Number t, Number t1) {
                exRate = t1.intValue();  
            }
        });
       
    }


    private void ajouterExercice() {
        if (notEmpty()) {
        try {
            Exercices ex  = new Exercices();
            ex.setNomExercice(tfNomExercice.getText());
            ex.setDifficulteExercice(tfDifficulteExercice.getText());
            ex.setDescExercice(tfDescriptionExercice.getText());
            ex.setIdCategorieFk(idCat);
            es.ajouter(ex);
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
        showExercices();
    }

    public void showExercices() {
        try {
            ObservableList<Exercices> listExerciceses =  es.afficher();
           /* colNomExercice.setCellValueFactory(new PropertyValueFactory<Exercices, String>("nomExercice"));
            colDifficulteExercice.setCellValueFactory(new PropertyValueFactory<Exercices, String>("difficulteExercice"));
            colDescriptionExercice.setCellValueFactory(new PropertyValueFactory<Exercices, String>("descExercice"));
            tvExercice.setItems(listExerciceses);  */
            exerciceLv.getItems().clear();
            exerciceLv.getItems().addAll(listExerciceses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    Exercices exs = new Exercices();
    public void supprimerExercice() {  
        exs = exerciceLv.getSelectionModel().getSelectedItem();
        es.supprimer(exs);
           showExercices();
           cleanInputs();
    }

    public void modifierExercice() {
       exs = exerciceLv.getSelectionModel().getSelectedItem();
       exs.setNomExercice(tfNomExercice.getText());
       exs.setDifficulteExercice(tfDifficulteExercice.getText());
       exs.setDescExercice(tfDescriptionExercice.getText());
       //exs.setIdCategorieFk();
       es.modifier(exs);
        showExercices();
        cleanInputs();
    }
    
    public boolean notEmpty(){
        if(tfNomExercice.getText().isEmpty() || 
                tfDescriptionExercice.getText().isEmpty() || 
                tfDescriptionExercice.getText().isEmpty() || 
                comboNomCategories.getValue().isEmpty() )
            return false;
        return true;
    }

   public void switchToCategories(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("../Controllers/GestionCategorie.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 994, 547);
        stage.setTitle("Categorie Page");
        stage.setScene(scene);
        stage.show();
    }
    
   public void cleanInputs(){
       tfNomExercice.setText("");
       tfDescriptionExercice.setText("");
       tfDifficulteExercice.setText("");
       comboNomCategories.setValue("");
   }
   
   
   public void rateExercice(){
       Exercices excr = exerciceLv.getSelectionModel().getSelectedItem();
       RatingEx r = new RatingEx();
       r.setIdExercice(excr.getIdExercice());
       r.setRate(exRate);
       rs.ajouter(r);
       
   }
   
   public void sendEmail(){
       es.sendMail("hileliwassim1@gmail.com");
   }
   
}
