package br.ufms.facom.mariocarvalho.p_12_sqlite_login;

import java.io.Serializable;

public class Contact implements Serializable{
    private String name;
    private String email;
    private String user;
    private String password;

    public Contact() {
    }

    public Contact(String name, String email, String user, String password) {
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
