/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interface.ICPServices;
import Models.CategorieProduit;
import Utils.MaConnexion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class CPServices implements ICPServices {

   Connection cnx = MaConnexion.getInstance().getCnx();
     CategorieProduit c = new CategorieProduit();
    
    @Override
    public void createCategorieP(CategorieProduit c) {
    String req = "INSERT INTO `catproduit` ( `nomc`, `descriptionc`) VALUES (?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(req);
           
            ste.setString(1, c.getNomc());
             ste.setString(2, c.getDescriptionc());
            ste.executeUpdate();
            System.out.println("Categorie Added Successfully");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    

    @Override
    public void modifyCategorieP(CategorieProduit c) {
         String query = "UPDATE catproduit SET nomc = '" + c.getNomc() +
               "' WHERE nomc = " + c.getNomc() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Categorie Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public void deleteCategorieP(CategorieProduit c) {
        String query = "DELETE FROM catproduit WHERE idc= " + c.getIdc() + "";
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
    public ObservableList readCategorieP() {
      ObservableList<CategorieProduit> categories = FXCollections.observableArrayList();
        String query = "SELECT * FROM catproduit";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
               CategorieProduit c = new CategorieProduit();
               c.setIdc(rs.getInt("idc"));
               c.setNomc(rs.getString("nomc"));
                              c.setDescriptionc(rs.getString("descriptionc"));
               categories.add(c);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return categories;
    }
    public ObservableList getNomCategories(){
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        String query = "SELECT nomc, descriptionc FROM catproduit";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                CategorieProduit c = new CategorieProduit();
                nomCategories.add(rs.getString("nomc"));
                nomCategories.add(rs.getString("descriptionc"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return nomCategories;
    }
    public Integer retreiveIdCp(String s){
        int idCat = 0;
        String query = "SELECT idc FROM catproduit WHERE nomc = '" + s + "'";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next())
                idCat = (rs.getInt("idc"));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idCat;
    }
    
}
