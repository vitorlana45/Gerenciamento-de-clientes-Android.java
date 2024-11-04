package com.example.myapplication.service.authService;


public class userAuthService  {

    public boolean verifyUserCredentials(String name, String password) {

        final  String passwordAuthenticated = "1971";
        final String  nameAuthenticated = "geraldo";

        return name.equals(nameAuthenticated) && password.equals(passwordAuthenticated);
    }
}
