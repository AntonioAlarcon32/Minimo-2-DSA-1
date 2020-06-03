package com.example.minimo2prueba;

public class userInstance {
    private static userInstance instance;
    private User user;

    private userInstance() {
        this.user = null;
    }

    public static userInstance getInstance() {
        if (instance == null)
            instance = new userInstance();
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
