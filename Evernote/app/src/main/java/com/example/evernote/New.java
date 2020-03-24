package com.example.evernote;

public class New {
    String email,pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public New(String email, String pass){
        this.email=email;
        this.pass=pass;
    }

}
