/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interface.IProduitService;
import Models.Produit;
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

/**
 *
 * @author user
 */
public class ProduitService implements IProduitService {
Connection cnx = MaConnexion.getInstance().getCnx();
Produit p=new Produit();
    @Override
    public void createProduit(Produit p) {
      //request
        String req = "INSERT INTO `produit`(`ref`, `marque`, `description`, `prix`, `image`,`NomCat`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
           
            st.setInt(1, p.getRef());
            st.setString(2, p.getMarque());
            st.setString(3, p.getDescription());
            st.setInt(4, p.getPrix());
            st.setString(5, p.getImage());
            st.setString(6, p.getNomCat());
            st.executeUpdate();
            System.out.println("Produit ajouté avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
    }
    public ObservableList afficher() {
         ObservableList<Produit> produits = FXCollections.observableArrayList();
         String query = "SELECT * FROM produit";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
               Produit p = new Produit();
               p.setRef(rs.getInt("ref"));
               p.setMarque(rs.getString("marque"));
               p.setDescription(rs.getString("description"));
               p.setPrix(rs.getInt("prix"));
                p.setImage(rs.getString("image"));
                 p.setNomCat(rs.getString("NomCat"));
              
               produits.add(p); 
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return produits;
    }

    @Override
    public List<Produit> readProduit() {
       
        ArrayList<Produit> produits = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM produit";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
 p.setId(rs.getInt("id"));
               p.setRef(rs.getInt("ref"));
               p.setMarque(rs.getString("marque"));
               p.setDescription(rs.getString("description"));
               p.setPrix(rs.getInt("prix"));
               p.setImage(rs.getString("image"));
               p.setNomCat(rs.getString("NomCat"));
               produits.add(p);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return produits;
    }

    @Override
    public void deleteProduit(Produit p) {
        String query = "DELETE FROM produit WHERE ref= " + p.getRef() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Product Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifyProduit(Produit p) {
       String query = "UPDATE produit SET ref = '" + p.getRef() + "',marque = '" + p.getMarque() +
                 "',description = '" + p.getDescription() +  "',prix = '" + p.getPrix() +"' WHERE id = " + p.getId() + "";       
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Produit Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } 
    }
    
    }
    

