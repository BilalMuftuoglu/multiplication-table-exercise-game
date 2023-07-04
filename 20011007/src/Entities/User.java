package Entities;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String role;
    private String username;
    private String password;

    public User() {}

    public User(String role, String username, String password, int id) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static String usernamePasswordCompatibility(String username,String password){
        if(username.length() < 3){
            return "Username must be at least 3 characters";
        }else if(password.length() < 6){
            return "Password must be at least 6 characters";
        }else{
            return null;
        }
    }
}
