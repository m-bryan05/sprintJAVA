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
public class CategoriesService implements IService<Categories>{
    Connection cnx = MaConnexion.getInstance().getCnx();
    Categories c = new Categories();
    
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
    public List afficher() {
        ArrayList categories = new ArrayList();
        String query = "SELECT * FROM categorie_exercice";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
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
    
    
}
