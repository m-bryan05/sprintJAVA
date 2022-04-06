/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Interface.InterfaceProduit;

import Entities.Produit;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ProduitService implements InterfaceProduit{
    Connection cnx = MaConnexion.getInstance().getCnx();
    

    /**
     *
     * @param p
     */
    @Override
    public void createProduit(Produit p) {
        
        //request
        String req = "INSERT INTO `produit`(`id`,`ref`, `marque`, `description`, `prix`, `image`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, p.getId());
            st.setInt(2, p.getRef());
            st.setString(3, p.getMarque());
            st.setString(4, p.getDescription());
            st.setInt(5, p.getPrix());
            st.setString(6, p.getImage());
            st.executeUpdate();
            System.out.println("Produit ajouté avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public List<Produit> readProduit() {
        ArrayList<Produit> produits = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM produit";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                produits.add(new Produit(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return produits;
    }
    @Override
    public void deleteProduit(Produit p) {
        String query = "DELETE FROM produit WHERE id= " + p.getId() + "";
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
