package com.example.user.troyecomputersystems;

public class Users {
    private String Name;
    private String Username;
    private String Password;
    private static String ID;



    public Users(){

    }
    public Users(String name, String username,String password){
        this.Name = name;
        this.Username = username;
        this.Password = password;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        Users.ID = ID;
    }
}
