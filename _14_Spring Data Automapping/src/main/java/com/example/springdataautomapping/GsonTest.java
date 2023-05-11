package com.example.springdataautomapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class GsonTest {

    private static String json= """
            {
              "username": "mnogogqk",
              "password": "1234"
            }
            """;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
//
//        LoginData loginData = new LoginData("mnogogqk", "1234");
//        System.out.println(gson.toJson(loginData));
        LoginData loginData = gson.fromJson(json, LoginData.class);
        System.out.println( loginData.getUsername()+" "+loginData.getPassword());
    }
    static class LoginData{

        @Expose
        private String username;

        @Expose
        private String password;


        public LoginData(String username, String password) {
            this.username = username;
            this.password = password;
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
}
