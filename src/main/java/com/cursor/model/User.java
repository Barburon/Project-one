package com.cursor.model;

public class User {
    private int id;

    private String password;
    private String username;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
     ;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\t\tUser" +
                "\t|\tid: " + id +
                "\t|\tname: \"" + username + "\"";
    }
}
