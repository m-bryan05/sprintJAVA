
package pidev;

import Utils.MaConnexion;
import Entities.Categories;
import Entities.Exercices;
import Services.CategoriesService;
import Services.ExerciceService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../Controllers/GestionCategorie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 994, 547);
        stage.setTitle("Hello !");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
     public static void main(String[] args) {        
         launch();
    }

}