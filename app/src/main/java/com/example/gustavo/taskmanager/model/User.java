package com.example.gustavo.taskmanager.model;

/**
 * Created by Gustavo on 17/02/2017.
 */

public class User {

    private int id;
    private String username;
    private String password;

    public User (){
        this.id = 0;
        this.username = "none";
        this.password = "none";
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
