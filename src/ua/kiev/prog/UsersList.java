package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    private static UsersList usersList = new UsersList();
    private List<User> list = new ArrayList<User>();
    public static UsersList getInstance(){
        return usersList;
    }

    public UsersList() {
    }

    public synchronized void add(User user){
        list.add(user);
    }

    public boolean contains (User newUser){
        for(User user : list){
            if(user.equals(newUser))
                return true;
        }
        return false;
    }

    public synchronized String toJSON(){
        if(list.size()>0){
            Gson gson = new GsonBuilder().create();
            return gson.toJson(list.toArray());
        } else {
            return null;
        }
    }

    public User getLogin(String login){
        for (User user : list){
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersList(){
        return list;
    }

}
