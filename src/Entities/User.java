/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author MAB
 */
public class User {
    
    private int id, isEnabled, coins, isVerified, oauth;
    private String username, password, role, email, name, secondName;
    private Date createdAt,lastUpdated,birthDate;

    public User(int id, String username, String password, String role, int isEnabled, int coins, int isVerified, int oauth, String email, String name, String secondName, Date createdAt, Date lastUpdated, Date birthDate) {
        this.id = id;
        this.isEnabled = isEnabled;
        this.coins = coins;
        this.isVerified = isVerified;
        this.oauth = oauth;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.birthDate = birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(int isEnabled) {
        this.isEnabled = isEnabled;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getOauth() {
        return oauth;
    }

    public void setOauth(int oauth) {
        this.oauth = oauth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
   

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", isEnabled=" + isEnabled + ", coins=" + coins + ", isVerified=" + isVerified + ", oauth=" + oauth + ", username=" + username + ", password=" + password + ", role=" + role + ", email=" + email + ", name=" + name + ", secondName=" + secondName + ", createdAt=" + createdAt + ", lastUpdated=" + lastUpdated + ", birthDate=" + birthDate + '}';
    }

    public User(int isEnabled, int coins, int isVerified, String username, String password, String role, String email, String name, String secondName, Date birthDate) {
        this.isEnabled = isEnabled;
        this.coins = coins;
        this.isVerified = isVerified;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }
    
    public User(int isEnabled, int coins, int isVerified, String username, String role, String email, String name, String secondName, Date birthDate) {
        this.isEnabled = isEnabled;
        this.coins = coins;
        this.isVerified = isVerified;
        this.username = username;
        this.role = role;
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    
    
    
    
}
