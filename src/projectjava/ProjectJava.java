/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava;
import Models.Produit;
import Services.ProduitService;
import Utils.MaConnexion;
import com.sun.javaws.Main;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public  class ProjectJava extends Application {
 
 
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectJava.class.getResource("/Views/AccueilP.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 994, 547);
        stage.setTitle("Hello !");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
     public static void main(String[] args) {
          MaConnexion mc = MaConnexion.getInstance();
         launch();
    }
    
}
