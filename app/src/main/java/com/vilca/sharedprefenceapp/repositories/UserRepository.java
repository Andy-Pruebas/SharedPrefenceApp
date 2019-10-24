package com.vilca.sharedprefenceapp.repositories;

import com.vilca.sharedprefenceapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User>users=new ArrayList<>();

    static{
        users.add(new User(100, "ebenites", "tecsup", "Erick Benites"));
        users.add(new User(200, "jfarfan", "tecsup", "Jaime Farfán"));
        users.add(new User(300, "drodriguez", "tecsup", "David Rodriguez"));
        users.add(new User(400,"jperez","tecsup","Juan Perez"));
    }
    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static User findByUsername(String username){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
}
