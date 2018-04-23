package com.acws.acws.Model;

/**
 * Created by divyankvijayvergiya on 22/02/18.
 */

public class User {
    private String date;
    private String email;
    private int id;
    private String password;
    private String name;
    private String phone;

    public User(){

    }
    public User( String date, String email, int id,  String password, String name, String phone){
        this.date = date;
        this.email = email;
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
