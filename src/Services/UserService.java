/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Utils.BcryptHasher;
import Utils.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAB
 */
public class UserService implements UService<User> {

    Connection con;
    Statement stm;
    BcryptHasher hasher;

    public UserService() {
        con = MaConnexion.getInstance().getCnx();
        hasher = new BcryptHasher();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO `user` (`username`, `email`, `password`, `roles`, `is_enabled`, `coins`, `is_verified`, `oauth`, `name`, `second_name`, `birth_date`, `created_at`, `last_updated`) VALUES (?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getEmail());
        pstm.setString(3, hasher.hash(user.getPassword()));
        pstm.setString(4, "[\"ROLE_USER\"]");
        pstm.setInt(5, 1);
        pstm.setInt(6, 0);
        pstm.setInt(7, 0);
        pstm.setInt(8, 0);
        pstm.setString(9, user.getName());
        pstm.setString(10, user.getSecondName());
        pstm.setDate(11, user.getBirthDate());
        pstm.executeUpdate();

    }

    @Override
    public void ajouterr(User user) throws SQLException {
        String req = "INSERT INTO `user` (`username`, `email`, `password`, `roles`, `is_enabled`, `coins`, `is_verified`, `oauth`, `name`, `second_name`, `birth_date`, `created_at`, `last_updated`) VALUES (?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getEmail());
        pstm.setString(3, hasher.hash(user.getPassword()));
        pstm.setString(4, user.getRole());
        pstm.setInt(5, user.getIsEnabled());
        pstm.setInt(6, user.getCoins());
        pstm.setInt(7, user.getIsVerified());
        pstm.setInt(8, user.getOauth());
        pstm.setString(9, user.getName());
        pstm.setString(10, user.getSecondName());
        pstm.setDate(11, user.getBirthDate());
        pstm.executeUpdate();

    }

    @Override
    public List<User> afficher() throws SQLException {
        String req = "Select * from `user`";
        stm = con.createStatement();
        ResultSet result = stm.executeQuery(req);
        List<User> users = new ArrayList<User>();
        while (result.next()) {
            User p = new User(result.getInt(1), result.getString("username"), result.getString("password"), result.getString("roles"), result.getInt("is_enabled"), result.getInt("coins"), result.getInt("is_verified"), result.getInt("oauth"), result.getString("email"), result.getString("name"), result.getString("second_name"), result.getDate("created_at"), result.getDate("last_updated"), result.getDate("birth_date"));
            users.add(p);
        }
        return users;

    }

    @Override
    public void delete(User u) throws SQLException {
        String req = "DELETE FROM `user` WHERE `username` = \"" + u.getUsername() + "\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void update(User user) throws SQLException {
        String req = "UPDATE `user` SET "
                + " `email` = '" + user.getEmail() + "', "
                + " `name` = '" + user.getName() + "', "
                + " `second_name` = '" + user.getSecondName() + "', "
                + " `birth_date` = '" + user.getBirthDate() + "', "
                + " `last_updated` = CURRENT_TIMESTAMP "
                + "WHERE `username` = '" + user.getUsername() + "'";
        if (user.getBirthDate() == null) {
            req = "UPDATE `user` SET "
                    + " `email` = '" + user.getEmail() + "', "
                    + " `name` = '" + user.getName() + "', "
                    + " `second_name` = '" + user.getSecondName() + "', "
                    + " `last_updated` = CURRENT_TIMESTAMP "
                    + "WHERE `username` = '" + user.getUsername() + "'";
        }
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    public boolean login(String username, String password) {
        try {
            String req = "SELECT * FROM `user` WHERE `username` = \"" + username + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            User user = new User();
            while (result.next()) {
                user = new User(result.getInt(1), result.getString("username"), result.getString("password"), result.getString("roles"), result.getInt("is_enabled"), result.getInt("coins"), result.getInt("is_verified"), result.getInt("oauth"), result.getString("email"), result.getString("name"), result.getString("second_name"), result.getDate("created_at"), result.getDate("last_updated"), result.getDate("birth_date"));
            }
            if (hasher.checkPassword(user.getPassword(), password)) {
                Main.loggedUser = user;
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public User getUser(String username) throws SQLException {
        String req = "Select * from `user` where username = '" + username + "';";
        stm = con.createStatement();
        ResultSet result = stm.executeQuery(req);
        User u = new User();
        while (result.next()) {
            u = new User(result.getInt(1), result.getString("username"), result.getString("password"), result.getString("roles"), result.getInt("is_enabled"), result.getInt("coins"), result.getInt("is_verified"), result.getInt("oauth"), result.getString("email"), result.getString("name"), result.getString("second_name"), result.getDate("created_at"), result.getDate("last_updated"), result.getDate("birth_date"));
        }
        return u;
    }

    public void updateNoPass(User user) throws SQLException {
        String req = "UPDATE `user` SET "
                + " `roles` = '" + user.getRole() + "', "
                + " `email` = '" + user.getEmail() + "', "
                + " `name` = '" + user.getName() + "', "
                + " `second_name` = '" + user.getSecondName() + "', "
                + " `birth_date` = '" + user.getBirthDate() + "', "
                + " `last_updated` = CURRENT_TIMESTAMP, "
                + " `is_enabled` = '" + user.getIsEnabled() + "', "
                + " `coins` = '" + user.getCoins() + "', "
                + " `is_verified` = '" + user.getIsVerified() + "' "
                + "WHERE `username` = '" + user.getUsername() + "'";
        if (user.getBirthDate() == null) {
            req = "UPDATE `user` SET "
                    + " `roles` = '" + user.getRole() + "', "
                    + " `email` = '" + user.getEmail() + "', "
                    + " `name` = '" + user.getName() + "', "
                    + " `second_name` = '" + user.getSecondName() + "', "
                    + " `last_updated` = CURRENT_TIMESTAMP, "
                    + " `is_enabled` = '" + user.getIsEnabled() + "', "
                    + " `coins` = '" + user.getCoins() + "', "
                    + " `is_verified` = '" + user.getIsVerified() + "' "
                    + "WHERE `username` = '" + user.getUsername() + "'";
        }
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    public void deleteByUsername(String username) throws SQLException {
        String req = "DELETE FROM `user` WHERE `username` = \"" + username + "\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    public boolean isUsernameTaken(String username) {
        try {
            String req = "select count(*) from `user` WHERE `username` = \"" + username + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            result.next();
            return result.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isEmailTaken(String email) {
        try {
            String req = "select count(*) from `user` WHERE `email` = \"" + email + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            result.next();
            return result.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isEmailTakenn(String email) {
        try {
            String req = "select count(*) from `user` WHERE `email` = \"" + email + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            result.next();
            return result.getInt(1) > 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void updatePass(String username, String password) throws SQLException {
        String req = "UPDATE `user` SET "
                + " `password` = '" + hasher.hash(password) + "' "
                + "WHERE `username` = '" + username + "'";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    
    public void updatePassByEmail(String email, String password) throws SQLException {
        String req = "UPDATE `user` SET "
                + " `password` = '" + hasher.hash(password) + "' "
                + "WHERE `email` = '" + email + "'";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    //

    public String getCode(String email) throws SQLException {
        String req = "SELECT selector FROM `reset_password_request` WHERE `expires_at` >= now() and user_id = (SELECT id FROM `user` WHERE email = '" + email + "')";
        stm = con.createStatement();
        ResultSet result = stm.executeQuery(req);
        String code = "";
        while (result.next()) {
            code = result.getString("selector");
        }
        return code;
    }

    public void setCode(String code, String email) {
        try {
            String req = "INSERT INTO `reset_password_request`(`user_id`, `selector`, `hashed_token`, `requested_at`, `expires_at`) VALUES ((SELECT id FROM `user` WHERE email = '" + email + "'),'" + code + "','" + code + "',now(),(DATE_ADD(now() , INTERVAL 1 HOUR)))";
            stm = con.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {

        }
    }
}
