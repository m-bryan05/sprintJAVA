/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author wassi
 */
import Entities.Categories;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class CategoriesService implements IService<Categories>{
    Connection cnx = MaConnexion.getInstance().getCnx();
   
    
    @Override
    public void ajouter(Categories c) {
      String query = "INSERT INTO categorie_exercice(nom) VALUES(?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,c.getNomCatgeorie());
            ste.executeUpdate();
            System.out.println("Categorie Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
        ObservableList<Categories> categories = FXCollections.observableArrayList();
        String query = "SELECT * FROM categorie_exercice";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
               Categories c = new Categories();
               c.setIdCategorie(rs.getInt("id"));
               c.setNomCatgeorie(rs.getString("nom"));
               categories.add(c);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return categories;
    }

    @Override
    public void supprimer(Categories c) {
        String query = "DELETE FROM categorie_exercice WHERE id= " + c.getIdCategorie() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Categorie Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Categories c) {
       String query = "UPDATE categorie_exercice SET nom = '" + c.getNomCatgeorie() +
               "' WHERE id = " + c.getIdCategorie() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Categorie Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
    
     public ObservableList getNomCategories(){
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        String query = "SELECT nom FROM categorie_exercice";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                Categories c = new Categories();
                nomCategories.add(rs.getString("nom"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return nomCategories;
    }
     
     
    public Integer retreiveIdCp(String s){
        int idCat = 0;
        String query = "SELECT id FROM categorie_exercice WHERE nom = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
                idCat = (rs.getInt("id"));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idCat;
    }
    
    
}
